package com.epelgemini.epelgeminiapp

import android.media.Image
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ThirdPhaseReportCases(){
    var text by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }


    val redColorUse = Color(0xFFAF4646)
    val redLightColorUse = Color(0xFFF2CBC6)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(16.dp)
    ){
        ProgressBar(3, redColorUse, redLightColorUse)

        Spacer(modifier = Modifier.height(16.dp))

        SectionHeader(text = "Bukti Informasi", color = redColorUse)

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Kronologi Kejadian", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = "Catatlah tanggal dan lokasi kejadian, urutan dan detail peristiwa, serta bentuk kekerasan yang dialami. \n" +
                            "\n" +
                            "Kronologi ini akan sangat membantu pihak berwenang dalam memahami situasi dan memastikan tindakan yang tepat dapat diambil. Harap tuliskan dengan jujur dan detail sesuai kemampuanmu, serta ingat bahwa dukungan selalu tersedia untuk kamu.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(252.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
            singleLine = false,
            maxLines = 15,
            colors = TextFieldDefaults.colors(

                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                disabledContainerColor = Color.LightGray,
                disabledTextColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Bukti Foto (Opsional)", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Kamu bisa menambahkan maksimal 3 foto bukti dari kejadian yang kamu lihat / alami.", fontSize = 13.sp, color = Color.Red)

        // Row function goes here to put image
        Spacer(modifier = Modifier.height(20.dp))

        ImagePickerRow()

        Spacer(modifier = Modifier.height(20.dp))


        Text(text = "Bukti Video (Opsional)", fontSize = 16.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "Durasi video maksimal 10 menit.", fontSize = 14.sp, color = Color.Red)

        // Row function goes here to put image

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            androidx.compose.material3.Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = androidx.compose.material3.CheckboxDefaults.colors(
                    checkedColor = redColorUse,
                    uncheckedColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Saya menyetujui bahwa semua tulisan yang ada di dalam laporan ini adalah nyata dan sesuai dengan kebenarannya",
                fontSize = 14.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        ActionButtonInThirdPhase(redColorUse, isChecked)


    }
}

@Composable
fun ImagePickerRow() {
    var selectedImageUri1 by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUri2 by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUri3 by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher1 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri1 = uri }
    )

    val imagePickerLauncher2 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri2 = uri }
    )

    val imagePickerLauncher3 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri -> selectedImageUri3 = uri }
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ImageBox(selectedImageUri1) { imagePickerLauncher1.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly)
        ) }
        ImageBox(selectedImageUri2) { imagePickerLauncher2.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly)
        ) }
        ImageBox(selectedImageUri3) { imagePickerLauncher3.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly)
        ) }
    }
}

@Composable
fun ImageBox(imageUri: Uri?, onClick: () -> Unit) {

    val image = painterResource(id = R.drawable.frame_170)


    Box(
        modifier = Modifier
            .size(112.dp)
            .background(Color.Transparent)
            .let {
                if (imageUri != null) it else it.background(Color.Transparent)
            },
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            AsyncImage(
                model = imageUri,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(112.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .clickable { onClick() }
            ){
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(112.dp)
                )
            }
        }
    }
}


@Composable
fun ActionButtonInThirdPhase(redColorUse: Color, isChecked: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = { /* Handle next action */ },
            enabled = isChecked, // Enable the button only if the checkbox is checked
            modifier = Modifier
                .width(380.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = redColorUse)
        ) {
            Text("Laporkan Kejadian", color = Color.White)
        }

        OutlinedButton(
            onClick = { /* Handle back action */ },
            modifier = Modifier
                .width(380.dp)
                .height(48.dp)
        ) {
            Text("Kembali ke halaman sebelumnya", color = redColorUse)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ThirdPhaseReportCasesPreview(){
    ThirdPhaseReportCases()

}