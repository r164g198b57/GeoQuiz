package com.github.r164g198b57.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private const val EXTRA_ANSWER_IS_TRUE = "kjhdzfptnkhloigherSL"
const val EXTRA_ANSWER_SHOWN = "kjhptnkhloigherSL"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var apiV: TextView
    private lateinit var showAnswerButton: Button
    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        apiV = findViewById(R.id.apiTxt)
        apiV.text = getString(R.string.api_level, Build.VERSION.SDK_INT)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue ->
                    R.string.true_button

                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)

        }

    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply { putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown) }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(
                    EXTRA_ANSWER_IS_TRUE,
                    answerIsTrue
                )
            }
        }
    }
}