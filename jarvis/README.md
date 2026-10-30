# JARVIS — iQOO / Android 16 wake-word starter

This is an Android Studio starter for a full-screen JARVIS-style app and a
microphone foreground-service scaffold. **It does not yet recognize “Hey
JARVIS”.** The service displays a persistent notification and marks the exact
place where a wake-word engine must be initialized.

## Why the wake-word engine is not preconfigured

A reliable always-listening wake-word detector needs an audio engine and a
keyword model. One common option is Picovoice Porcupine. It requires:
- a Picovoice account/access key;
- the Android SDK dependency matching the current vendor documentation;
- a `.ppn` keyword model generated for Android and the chosen phrase;
- correct initialization, audio-frame handling, and cleanup code.

Do not paste your access key into a public repository. Follow the current
Porcupine Android quick-start documentation for the exact SDK version and
model format. The SDK API and licensing can change, so this project deliberately
does not guess a dependency version or ship a fake detector.

## Android / iQOO constraints

- Android requires microphone foreground services to be started while the app
  is visible and the user has granted microphone permission.
- Android 14+ requires the `microphone` foreground-service type and its
  permission; both are declared here.
- Android 13+ notification permission is requested by the app.
- Start listening by opening JARVIS and pressing **ENABLE HEY JARVIS**.
- A persistent notification is expected while listening.
- iQOO/Android battery optimization may stop background work. Set the app's
  battery usage to unrestricted / allow background activity if that option is
  available on your exact Funtouch OS build.
- Force-stopping the app, revoking microphone permission, or some system
  power-saving modes will stop listening. This is not a hidden or guaranteed
  lock-screen assistant.

## Build

1. Install Android Studio on a Windows, macOS, or Linux computer.
2. Open this folder as an existing Gradle project.
3. Let Gradle sync and install any requested Android SDK 36 components.
4. Connect the phone with USB debugging enabled.
5. Run the `app` configuration.
6. Grant microphone and notification permissions.
7. Open JARVIS and press **ENABLE HEY JARVIS**.

## Next implementation step

Integrate the current Porcupine Android SDK according to its official docs:
1. Add the vendor's current Gradle dependency.
2. Add the Android `.ppn` model to the app assets.
3. Initialize the engine inside `WakeWordService.onCreate()`.
4. Feed microphone frames to the engine.
5. On detection, play a short acknowledgement and start a separate
   command-capture flow (for example Android SpeechRecognizer).
6. Stop/release the engine in `onDestroy()` and handle audio focus/errors.

## Important

This is source code, not a ready-to-install APK. The foreground service scaffold
is functional as a lifecycle/notification starting point, but wake-word detection
and actual AI conversation are not implemented in this ZIP.
