package com.ra1n.voicedetect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), CallBackListener {
    private lateinit var audioAnalyzer: AudioAnalyzer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        audioAnalyzer = AudioAnalyzer(this, this)
        audioAnalyzer.startAnalysis()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResultDetected(index: Int) {
        when(index) {
            ModelClassification.LABEL_BABY_CRY_INFANT_CRY -> {
                showToast("Baby Cry")
            }
            ModelClassification.LABEL_POLICE_CAR_SIREN -> {
                showToast("Police car Horn")
            }
            ModelClassification.LABEL_CAT -> {
                showToast("Cat")
            }
            ModelClassification.LABEL_BABY_LAUGHTER -> {
                showToast("Baby Laugh")
            }
            ModelClassification.LABEL_DOG -> {
                showToast("Dog")
            }
            ModelClassification.LABEL_GUNSHOT_GUNFIRE -> {
                showToast("Gunshot")
            }
            ModelClassification.LABEL_GUITAR -> {
                showToast("Guitar")
            }
            ModelClassification.LABEL_BELL -> {
                showToast("Bell")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        audioAnalyzer.stopAnalysis()
    }
}
