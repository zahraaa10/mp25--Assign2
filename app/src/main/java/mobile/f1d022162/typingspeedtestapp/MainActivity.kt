package mobile.f1d022162.typingspeedtestapp

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textTarget: TextView
    private lateinit var inputText: EditText
    private lateinit var startButton: Button
    private lateinit var resultText: TextView
    private var startTime: Long = 0
    private val totalWords = 39
    private val targetSentence = "Mata kuliah pemrograman bergerak mempelajari tentang konsep dasar pemrograman bergerak, sistem operasi perangkat bergerak, dan lingkungan pengembangannya. Dalam perkuliahan akan diajarkan bagaimana membangun perangkat bergerak yang terdiri dari antarmuka, pemrosesan data, sampai dengan pengaksesan perangkat keras dan library external"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textTarget = findViewById(R.id.textView)
        inputText = findViewById(R.id.editTextText)
        startButton = findViewById(R.id.button)
        resultText = findViewById(R.id.textView2)
        textTarget.text = targetSentence

        startButton.setOnClickListener {
            if (startButton.text == "Mulai") {
                inputText.text.clear()
                inputText.isEnabled = true
                startTime = SystemClock.elapsedRealtime()
                startButton.text = "Selesai"
            } else {
                val endTime = SystemClock.elapsedRealtime()
                val duration = (endTime - startTime) / 1000.0
                val durationM = duration / 60.0
                val wpm = if (durationM > 0) totalWords / durationM else 0.0
                val timeUsed = String.format("%.2f", duration)
                val wpmFormatted = String.format("%.2f", wpm)

                val typedText = inputText.text.toString().trim()

                if (typedText == targetSentence) {
                    resultText.text = "Total Waktu: $timeUsed detik\nWPM: $wpmFormatted kata per menit"
                } else {
                    resultText.text = "Ada kesalahan ketik!\nSilakan coba lagi"
                }

                resultText.visibility = View.VISIBLE
                startButton.text = "Mulai"
                inputText.isEnabled = false
            }
        }
    }
}