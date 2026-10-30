# Build JARVIS APK without Android Studio on Windows 7

This package is intended to be uploaded to a GitHub repository and built on
GitHub's cloud runners. Windows 7 is only used to upload the source in a web
browser; the Android build itself runs on Ubuntu in the cloud.

## What this builds

A debug APK of the current JARVIS starter project. It includes the full-screen
Compose interface and microphone foreground-service scaffold. It does NOT yet
include a working "Hey JARVIS" detector or AI assistant brain.

## Steps

1. Sign in to GitHub or create an account.
2. Create a new repository, for example `jarvis-android`.
3. Extract this ZIP on your Windows 7 PC.
4. Upload all extracted files and folders to the repository. Make sure the
   `.github/workflows/build-apk.yml` file is included. GitHub's web upload
   page may hide dot-folders; if so, create `.github`, then `workflows`, then
   upload `build-apk.yml` using GitHub's Add file interface.
5. Commit the files to the `main` branch.
6. Open the repository's **Actions** tab.
7. Select **Build JARVIS APK** and click **Run workflow** (or wait for the
   automatic build after committing).
8. When the run completes successfully, open the run and download the
   `JARVIS-debug-APK` artifact.
9. Extract the downloaded artifact ZIP. It contains `app-debug.apk`.
10. Transfer `app-debug.apk` to your iQOO and install it. You may need to allow
    installation from your file manager/browser in Android settings.

## Notes

- GitHub's interface and account verification requirements can change.
- A debug APK is suitable for testing, not Play Store publishing.
- If the build fails, share the red error text or screenshot so it can be fixed.
- Do not publish API keys, passwords, or private signing keys in the repository.
- Android may block background microphone use unless the app is started while
  visible, permissions are granted, and battery restrictions are adjusted.
