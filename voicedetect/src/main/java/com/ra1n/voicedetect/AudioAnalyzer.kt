package com.ra1n.voicedetect

import android.content.Context
import android.media.AudioRecord
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.ra1n.voicedetect.ModelConstants.MODEL_FILE
import com.ra1n.voicedetect.ModelConstants.sens
import org.tensorflow.lite.task.audio.classifier.AudioClassifier
import java.io.IOException

interface CallBackListener {
    fun onResultDetected(index: Int)
}

class AudioAnalyzer(private val context: Context, private val callBackListener: CallBackListener) {

    private var audioRecord: AudioRecord? = null
    private var handler: Handler? = null

    fun startAnalysis() {
        val handlerThread = HandlerThread("backgroundThread")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        var classifier: AudioClassifier? = null

        try {
            classifier = AudioClassifier.createFromFile(context, MODEL_FILE)
            val audioTensor = classifier.createInputTensorAudio()
            audioRecord = classifier.createAudioRecord()

            val run: Runnable = object : Runnable {
                override fun run() {
                    try {
                        audioRecord!!.startRecording()
                        audioTensor.load(audioRecord)
                        val output = classifier!!.classify(audioTensor)
                        for (classifications in output) {
                            for (category in classifications.categories) {
                                if (category.score > sens(90f)) {
                                    callBackListener.onResultDetected(category.index)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("AudioAnalyzer ", e.message!!)
                    }
                    handler!!.postDelayed(this, 500)
                }
            }
            handler!!.post(run)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun stopAnalysis() {
        handler?.removeCallbacksAndMessages(null)
        audioRecord?.stop()
        audioRecord?.release()
    }
}
