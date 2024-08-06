package com.epelgemini.report_presentation.converters

import android.content.Context
import android.net.Uri
import com.epelgemini.core.domain.dispatchers.DispatchersProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UriToFileConverter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcher: DispatchersProvider
) {
    suspend fun convert(
        uris: List<Uri>
    ) {
        uris.forEach { uri ->
            uri.lastPathSegment?.let { fileName ->
                withContext(dispatcher.io) {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val fileDir = context.filesDir.absoluteFile
                    val output = FileOutputStream(
                        File("${fileDir}/${fileName}")
                    )
                    inputStream?.copyTo(output, 4 * 1024)
                }
            }
        }
    }
}