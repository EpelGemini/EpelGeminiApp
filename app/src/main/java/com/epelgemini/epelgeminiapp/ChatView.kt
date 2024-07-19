package com.epelgemini.epelgeminiapp

import android.util.Base64
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

fun getGeminiKey(): String {
    val encodedKey = "QUl6YVN5Q2lFYzJNTmw3ZUE1ZVRTNVlqSzd6aEhVaTE2YVRJY3RV"
    val decodedBytes = Base64.decode(encodedKey, Base64.DEFAULT)
    return String(decodedBytes, Charsets.UTF_8)
}

data class Message(val content: String, val isFromUser: Boolean)

val PrimaryBlue = Color(0xFF1E88E5)  // A slightly darker blue
val LightBlue = Color(0xFFE3F2FD)    // A very light blue for contrast
val DarkBlue = Color(0xFF1565C0)     // A deeper blue for accents
val BackgroundGray = Color(0xFFF5F5F5)  // Light gray for background
val TextPrimary = Color(0xFF212121)  // Dark gray for primary text
val TextSecondary = Color(0xFF757575)  // Medium gray for secondary text

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow(listOf<Message>())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = getGeminiKey(),
        systemInstruction = content { text("You are a mental health support system. Don't use markdown. Respond in user's input language. Use a casual style like talking with a friend.") },
    )
    private val chat = generativeModel.startChat()

    init {
        sendMessage("Hi! How can I help you today?", false)
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
fun ChatView(viewModel: ChatViewModel = viewModel()) {
    val messages by viewModel.messages.collectAsState()
    val isGenerating by viewModel.isGenerating.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size - 1)
    }

    Column(modifier = Modifier.fillMaxSize()) {
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
}

@Composable
fun ChatTopAppBar() {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = "Chat icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Curhat AI", color = Color.White, fontWeight = FontWeight.Bold)
            }
        },
        backgroundColor = DarkBlue,
        elevation = 4.dp
    )
}

@Composable
fun MessageBubble(message: Message) {
    val bubbleColor = if (message.isFromUser) PrimaryBlue else LightBlue
    val textColor = if (message.isFromUser) Color.White else TextPrimary
    val alignment = if (message.isFromUser) Alignment.CenterEnd else Alignment.CenterStart
    val bubbleShape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = if (message.isFromUser) 16.dp else 0.dp,
        bottomEnd = if (message.isFromUser) 0.dp else 16.dp
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = alignment
    ) {
        Column(horizontalAlignment = if (message.isFromUser) Alignment.End else Alignment.Start) {
            Surface(
                color = bubbleColor,
                shape = bubbleShape,
                elevation = 1.dp
            ) {
                Text(
                    text = message.content,
                    modifier = Modifier.padding(12.dp),
                    color = textColor,
                    fontSize = 16.sp
                )
            }
            Text(
                text = "Just now", // Replace with actual timestamp
                color = TextSecondary,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)
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
        color = LightBlue,
        shape = RoundedCornerShape(16.dp),
        elevation = 1.dp,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "Curhat AI is typing" + ".".repeat(dotsCount),
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
        color = Color.White,
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
                placeholder = { Text("Type a message...", color = TextSecondary) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BackgroundGray,
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
                    .clip(CircleShape),
                enabled = !isGenerating && text.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send message",
                    tint = if (!isGenerating && text.isNotBlank()) PrimaryBlue else TextSecondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatViewPreview() {
    ChatView()
}