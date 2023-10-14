**Voice Analyzer Library**

![Voice Analyzer Library](https://github.com/yourusername/voice-analyzer-library)

The **Voice Analyzer Library** is a versatile and user-friendly Android library that provides developers with a powerful tool for voice analysis and classification. With this library, you can easily detect and categorize various voice inputs, making it ideal for applications that require voice-controlled functionalities.

## Features

- **Voice Classification:** The library allows you to classify audio recordings into predefined categories, making it easy to identify different sounds in real-time.

- **Highly Configurable:** You can customize the sensitivity threshold and define your own classification categories to suit your specific application needs.

- **Real-time Analysis:** Analyze voice input in real-time, enabling quick responses and interactions.

- **Simple Integration:** Integrate this library into your Android app effortlessly with a few lines of code.

## Getting Started

### Installation

### Step 1: Add the JitPack Repository

Add the JitPack repository to your project's root `build.gradle` file under the `allprojects` section:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the Dependency

To get started, add the library to your Android project by including it in your `build.gradle` file.

```gradle
implementation 'com.github.zenara1n:voice-detect:1.0'
```

### Usage

#### Initialize the Voice Analyzer

Create an instance of the `AudioAnalyzer` and start the analysis process.

```java
AudioAnalyzer audioAnalyzer = new AudioAnalyzer(context, callBackListener);
audioAnalyzer.startAnalysis();
```

#### Implement Callbacks

Implement the `CallBackListener` interface to receive classification results.

```java
public class YourActivity implements CallBackListener {
    // ...

    @Override
    public void onResultDetected(int index) {
        switch (index) {
            case ModelClassification.LABEL_BABY_CRY_INFANT_CRY:
                // Handle Baby Cry
                break;
            case ModelClassification.LABEL_POLICE_CAR_SIREN:
                // Handle Police Car Siren
                break;
            // ... Add more cases for different classifications
        }
    }

    // ...
}
```

#### Stop Analysis

Don't forget to stop the analysis when your activity is destroyed or no longer needs voice classification.

```java
@Override
public void onDestroy() {
    super.onDestroy();
    audioAnalyzer.stopAnalysis();
}
```

### ModelClassification

The `ModelClassification` object is a part of the library, and it contains predefined labels for various sound classifications. You can use these labels to identify the type of sound detected by the library.

Here are some example labels from the `ModelClassification` object:

- `ModelClassification.LABEL_BABY_CRY_INFANT_CRY`: Identifies the sound of a baby crying.
- `ModelClassification.LABEL_POLICE_CAR_SIREN`: Detects the sound of a police car siren.
- `ModelClassification.LABEL_CAT`: Recognizes the sound of a cat.
- `ModelClassification.LABEL_BABY_LAUGHTER`: Identifies the sound of a baby's laughter.
- `ModelClassification.LABEL_DOG`: Detects the sound of a dog barking.
- `ModelClassification.LABEL_GUITAR`: Recognizes the sound of a guitar.
- `ModelClassification.LABEL_BELL`: Identifies the sound of a bell ringing.

These labels are used in your application code to specify the action to be taken when a specific sound is detected.

The **Voice Analyzer Library** empowers your Android application with real-time voice analysis and classification, making it a valuable addition for voice-controlled applications and voice-activated features. With its simplicity and configurability, you can create interactive and engaging experiences for your users.

Get started with the Voice Analyzer Library and unlock the potential of voice-controlled interactions in your Android app.

### Thanks & Support

Your support and contributions are greatly appreciated!

If you encounter any issues, have questions, or want to contribute to the IP Geography Kit project, please feel free to reach out. Here's how you can get in touch:

- **GitHub Repository**: [IP Geography Kit)](https://github.com/zenara1n/voice-detect)
- **Email**: zxzayn@gmail.com
- **linkedin**: [linkedin](https://www.linkedin.com/in/zaynal/)

Your feedback helps us improve the library and provide better support to our users.

---

## Reporting Issues

If you encounter any bugs, issues, or have feature requests, please open an issue on our GitHub repository. Your feedback and bug reports are essential for enhancing IP Geography Kit.

[Link to Issue Tracker](https://github.com/zenara1n/voice-detect/issues)

Thank you for using IP Geography Kit, and I look forward to helping you create amazing location-aware Android applications!
