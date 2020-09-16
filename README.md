# flutter_crispchat

![Crisp](https://raw.githubusercontent.com/crisp-im/crisp-sdk-android/master/docs/img/logo_blue.png)


A flutter wrapper around the Crisp mobile SDK for iOS and Android.

https://crisp.chat/en/

Uses the following native libraries:

| Platform | Library        | Version |
|----------|----------------|---------|
| Android  | im.crisp:crisp-sdk | 0.1.15  |
| iOS      | Crisp | 1.0.3   |

## Getting Started
Add the plugin to your `pubspec.yaml`:
```yaml
flutter_crispchat: ^0.0.1
```

Next, call `configure('WEBSITE_ID')` with your account's website ID.

After this you can call `showChat()` to open the chat window.

## More information
Please see the Crisp SDK for each platform below for issue requests on each respective SDK.

### Android
https://github.com/crisp-im/crisp-sdk-android

### iOS
https://github.com/crisp-im/crisp-sdk-ios

