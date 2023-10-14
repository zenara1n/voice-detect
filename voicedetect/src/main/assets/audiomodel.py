import pyaudio
import numpy as np
import tensorflow as tf

MODEL_FILE = "/model.tflite"
SENSITIVITY = 90

class CallBackListener:
    def onResultDetected(self, index):
        print(f"Result detected: {index}")

class AudioAnalyzer:
    def __init__(self, callBackListener):
        self.callBackListener = callBackListener
        self.chunk_size = 1024
        self.sample_format = pyaudio.paInt16
        self.channels = 1
        self.sample_rate = 44100
        self.audio = pyaudio.PyAudio()
        self.stream = self.audio.open(format=self.sample_format,
                                      channels=self.channels,
                                      rate=self.sample_rate,
                                      input=True,
                                      frames_per_buffer=self.chunk_size)
        self.interpreter = tf.lite.Interpreter(model_path=MODEL_FILE)
        self.interpreter.allocate_tensors()

    def start_analysis(self):
        try:
            print("Start recording...")
            while True:
                data = np.frombuffer(self.stream.read(self.chunk_size), dtype=np.int16)
                input_tensor_index = self.interpreter.get_input_details()[0]['index']
                self.interpreter.set_tensor(input_tensor_index, data)
                self.interpreter.invoke()
                output = self.interpreter.tensor(self.interpreter.get_output_details()[0]['index'])()
                index = np.argmax(output)
                score = output[0][index]
                if score > SENSITIVITY:
                    self.callBackListener.onResultDetected(index)
        except KeyboardInterrupt:
            print("Stopping analysis...")
            self.stop_analysis()

    def stop_analysis(self):
        self.stream.stop_stream()
        self.stream.close()
        self.audio.terminate()

if __name__ == "__main__":
    class Listener(CallBackListener):
        def onResultDetected(self, index):
            print(f"Detected index: {index}")

    listener = Listener()
    analyzer = AudioAnalyzer(listener)
    analyzer.start_analysis()
