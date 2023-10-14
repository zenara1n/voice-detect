package com.ra1n.voicedetect;

public class ModelConstants {
    public static final String MODEL_FILE = "yamnet.tflite";
    public static float sens (float sens) {
        sens = 100 - sens;
        sens = sens / 100;
        return sens;
    }
}
