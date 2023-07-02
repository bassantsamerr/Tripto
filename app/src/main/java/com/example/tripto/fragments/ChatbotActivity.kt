package com.example.tripto.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.example.tripto.R
import com.example.tripto.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatbotActivity : Fragment() {

    private lateinit var chatContainer: LinearLayout
    private lateinit var userInputEditText: EditText
    private lateinit var sendButton: ImageButton
    private val service: ApiInterface = ApiInterface.create()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_chatbot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        chatContainer = view.findViewById(R.id.chatContainer)
        userInputEditText = view.findViewById(R.id.userInputEditText)
        sendButton = view.findViewById(R.id.sendButton)

        val botResponse = "Hello! How can I assist you?"
        val botMessageView = createBotMessageView(botResponse)
        chatContainer.addView(botMessageView)

        // Set a click listener for the send button
        sendButton.setOnClickListener {
            val userMessage = userInputEditText.text.toString().trim()
            // Process the user message, send it to the chatbot, and display the response
            processUserMessage(userMessage)
        }
    }

    private fun processUserMessage(message: String) {
        // Add the user's message to the chat container
        val userMessageView = createUserMessageView(message)
        chatContainer.addView(userMessageView)

        // TODO: Send the message to the chatbot and handle the response
        // Here you would send the user message to your chatbot backend and handle the response accordingly

        // Example response from the chatbot
        val call: Call<String> = service.get_chatbot_reponse(message)
        call.enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val botResponse = response.body().toString()
                val botMessageView = createBotMessageView(botResponse)
                chatContainer.addView(botMessageView)
                Log.d("message response",botResponse)
                // Clear the input field
                userInputEditText.text.clear()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun createUserMessageView(message: String): View {
        val messageLayout = LinearLayout(requireContext())
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(10, 15, 20, 15) // left, top, right, bottom
        layoutParams.gravity = Gravity.END // Align to the right
        messageLayout.layoutParams = layoutParams
        messageLayout.orientation = LinearLayout.VERTICAL
        messageLayout.setPadding(16, 8, 16, 8)
        messageLayout.setBackgroundResource(R.drawable.user_message_background)

        val textView = TextView(requireContext())
        textView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textView.text = message
        textView.textSize = 25f
        textView.setTextColor(Color .BLACK)
        messageLayout.addView(textView)
        return messageLayout
    }

    private fun createBotMessageView(message: String): LinearLayout {
        val messageLayout = LinearLayout(requireContext())
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(20, 15, 15, 15) // left, top, right, bottom
        layoutParams.gravity = Gravity.START
        messageLayout.layoutParams = layoutParams
        messageLayout.orientation = LinearLayout.VERTICAL
        messageLayout.setPadding(16, 8, 16, 8)
        messageLayout.setBackgroundResource(R.drawable.bot_message_background)

        val textView = TextView(requireContext())
        textView.text = message
        textView.textSize = 25f
        textView.setTextColor(Color.BLACK)
        messageLayout.addView(textView)

        return messageLayout
    }
}
