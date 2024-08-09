package com.epelgemini.epelgeminiapp

import android.util.Base64
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epelgemini.journal_domain.use_cases.JournalUseCases
import com.epelgemini.report_domain.use_cases.ReportUseCases
import com.epelgemini.report_presentation.converters.UriToFileConverter
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getGeminiKey(): String {
    val encodedKey = "QUl6YVN5Q2lFYzJNTmw3ZUE1ZVRTNVlqSzd6aEhVaTE2YVRJY3RV"
    val decodedBytes = Base64.decode(encodedKey, Base64.DEFAULT)
    return String(decodedBytes, Charsets.UTF_8)
}

data class Message(val content: String, val isFromUser: Boolean, val timestamp: Date = Date())

val PrimaryPurple = Color(0xFF5C6BC0)
val LightPink = Color(0xFFF9E7E5)
val DarkPink = Color(0xFFF47A7A)
val LightGray = Color(0xFFE5E5E5)
val PrimaryBlue = Color(0xFF1E88E5)  // A slightly darker blue
val LightBlue = Color(0xFFE3F2FD)    // A very light blue for contrast
val DarkBlue = Color(0xFF1565C0)     // A deeper blue for accents
val BackgroundGray = Color(0xFFF5F5F5)  // Light gray for background
val TextPrimary = Color(0xFF212121)  // Dark gray for primary text
val TextSecondary = Color(0xFF757575)  // Medium gray for secondary text
val BackgroundWhite = Color.White

// Define the system instruction as a separate constant
val SYSTEM_INSTRUCTION = """
You are an empathetic mental health support system for offense victims, such as rape, sexual harassment, physical offense, bullying, and other types of offense.

Key Guidelines:
• Use a casual, friendly tone as if you're the user's closest friend.
• Keep responses short and concise, because you're talking to user through SMS.
• Be highly empathetic and supportive.
• Respond in user's input language (English or Indonesian).
• Use "•" for bullet points, not "*".
• No markdown or bold text.

Important:
• Offer to write a sexual harassment report when you have enough information.
• If not offered earlier, ALWAYS offer to write a report on the 10th message.
• Your 10th message, which asks user to write report, must ALWAYS include "<REPORT>" at the end of your text.
• Be careful not to overwhelm the user.

Response Guidelines:
• NEVER use markdown in your response, just plaintext!
• NEVER use bold (**) in your response.
• When you need to use bullet points use • not *.
• Respond in user's input language (English or Indonesian).

Remember: Your goal is to provide comforting, concise support and empower users to take action when ready, and offer to write report at 10th message with "<REPORT>".
""".trimIndent()


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val journalUseCases: JournalUseCases,
    private val reportUseCases: ReportUseCases,
    private val uriToFileConverter: UriToFileConverter
) : ViewModel() {
    private val _messages = MutableStateFlow(listOf<Message>())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val safetySettings = listOf(
        SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.NONE),
        SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.NONE),
        SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.NONE),
        SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.NONE),
    )

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = getGeminiKey(),
        safetySettings = safetySettings,
        systemInstruction = content { text(SYSTEM_INSTRUCTION) },
        generationConfig = generationConfig {
            temperature = 0.5f
            topK = 32
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        },
    )
    private val chat = generativeModel.startChat()

    init {
        sendMessage("Hi! How can I help you today?", false)

        viewModelScope.launch {
            journalUseCases.getJournals()
                .collect { journals ->
                    Timber.d(journals.toString())
                    Timber.d("Journals")
                }
        }

        viewModelScope.launch {
            reportUseCases
                .getReports()
                .collect { reports ->
                    Timber.d(reports.toString())
                    Timber.d("Reports")
                }
        }

        viewModelScope.launch {
            uriToFileConverter.convert(emptyList())
        }
    }

    fun sendMessage(content: String, isFromUser: Boolean = true) {
        viewModelScope.launch {
            _messages.value += Message(content, isFromUser)
            if (isFromUser) {
                _isGenerating.value = true
                try {
                    val response = chat.sendMessage(content)
                    handleResponse(response)
                } catch (e: Exception) {
                    _messages.value += Message("Sorry, an error occurred: ${e.message}", false)
                } finally {
                    _isGenerating.value = false
                }
            }
        }
    }

    private fun handleResponse(response: GenerateContentResponse) {
        val resultText = response.text ?: "Sorry, I couldn't generate a response."
        _messages.value += Message(resultText.trim(), false)
    }
}

@Composable
fun ChatView(viewModel: ChatViewModel = hiltViewModel()) {
    val messages by viewModel.messages.collectAsState()
    val isGenerating by viewModel.isGenerating.collectAsState()
    val listState = rememberLazyListState()
    val isKeyboardVisible by keyboardAsState()

    LaunchedEffect(messages.size, isKeyboardVisible) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundWhite)) {
        ChatTopAppBar()

        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message)
            }
            item {
                AnimatedVisibility(
                    visible = isGenerating,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    TypingIndicator()
                }
            }
        }

        ChatInputField(
            onSendMessage = { newMessage -> viewModel.sendMessage(newMessage) },
            isGenerating = isGenerating
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            // Clean up any resources if needed
        }
    }
}

@Composable
fun ChatTopAppBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.Center)
                ) {

                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Safey", color = Color.White, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(48.dp))

                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Implement menu action */ }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
            }
        },
        backgroundColor = PrimaryPurple,
        elevation = 0.dp
    )
}
@Composable
fun MessageBubble(message: Message) {
    val bubbleColor = if (message.isFromUser) LightGray else LightPink
    val textColor = TextPrimary
    val alignment = if (message.isFromUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleShape = RoundedCornerShape(16.dp)

    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedTime = timeFormatter.format(message.timestamp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start
    ) {
        Box(contentAlignment = alignment) {
            Column {
                Surface(
                    color = bubbleColor,
                    shape = bubbleShape,
                    elevation = 1.dp,
                    modifier = Modifier.widthIn(max = LocalConfiguration.current.screenWidthDp.dp * 0.6f)
                ) {
                    Text(
                        text = message.content,
                        modifier = Modifier.padding(12.dp),
                        color = textColor,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = formattedTime,
                    color = TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)
                )
            }
        }
        if (!message.isFromUser && message.content.contains("<REPORT>")) {
            Column(modifier = Modifier
                .padding(top = 16.dp)
            ){
                AIPermissionButtons()
            }
        }
    }
}

@Composable
fun AIPermissionButtons() {

    Column(
        modifier = Modifier
            .background(LightPink)
            .padding(16.dp)
            .widthIn(
                max = LocalConfiguration.current.screenWidthDp.dp * 0.6f,
                min = LocalConfiguration.current.screenWidthDp.dp * 0.6f
            ),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "AI izin lapor",
            color = DarkPink,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { /* TODO: Implement permission action */ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = DarkPink),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                "Izinkan",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { /* TODO: Implement decline action */ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(48.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = Color.White,
                contentColor = DarkPink
            ),
            border = BorderStroke(1.dp, DarkPink),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text(
                "Tidak",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun TypingIndicator() {
    var dotsCount by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            dotsCount = (dotsCount % 3) + 1
        }
    }

    Surface(
        color = LightPink,
        shape = RoundedCornerShape(16.dp),
        elevation = 1.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "Safey AI is typing" + ".".repeat(dotsCount),
            modifier = Modifier.padding(12.dp),
            color = TextPrimary,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ChatInputField(onSendMessage: (String) -> Unit, isGenerating: Boolean) {
    var text by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = PrimaryPurple,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp)),
                placeholder = { Text("What do you want to tell me?", color = Color.LightGray) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = TextPrimary
                ),
                singleLine = true,
                enabled = !isGenerating
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    if (text.isNotBlank()) {
                        onSendMessage(text)
                        text = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(DarkPink),
                enabled = !isGenerating && text.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send message",
                    tint = Color.White
                )
            }
        }
    }
}
@Composable
fun keyboardAsState(): State<Boolean> {
    val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
    return remember {
        mutableStateOf(isImeVisible)
    }.apply {
        value = isImeVisible
    }
}

@Preview(showBackground = true)
@Composable
fun ChatViewPreview() {
    ChatView()
}