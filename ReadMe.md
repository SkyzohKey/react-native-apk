# react-native-apk

[![npm version](https://badge.fury.io/js/react-native-apk.svg)](https://badge.fury.io/js/react-native-apk)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FSkyzohKey%2Freact-native-apk.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FSkyzohKey%2Freact-native-apk?ref=badge_shield)

A react-native library to get various informations about an Android app.

## Getting started

```sh
$ yarn add react-native-apk
```

### Mostly automatic installation

```sh
$ react-native link react-native-apk
```

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

* Add `import be.skyzohlabs.rnapk.ReactNativeAPKPackage;` to the imports at the top of the file
* Add `new ReactNativeAPKPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:
   ```
   include ':react-native-apk'
   project(':react-native-apk').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-apk/android')
   ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
     compile project(':react-native-apk')
   ```

## Usage

```javascript
import ReactNativeAPK from "react-native-apk";

// Install an app:
ReactNativeAPK.installApp("path/to/apk-release.apk");

// Uninstall an app:
ReactNativeAPK.uninstallApp("org.package.name");

// Fetch if an app is installed on the device:
ReactNativeAPK.isAppInstalled("org.mozilla.klar", installed => {
  console.log("Is app installed?", installed);
});

// Get version of an installed app:
ReactNativeAPK.getAppVersion("org.mozilla.klar", version => {
  console.log("App version", version);
});

// Get ALL the apps installed on the device:
ReactNativeAPK.getApps(apps => {
  console.log(apps);
});

// Get apps the user has EXPLICITLY installed on the device:
ReactNativeAPK.getNonSystemApps(apps => {
  console.log(apps);
});

// Run an application:
ReactNativeAPK.runApp("org.mozilla.klar");
```


## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FSkyzohKey%2Freact-native-apk.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FSkyzohKey%2Freact-native-apk?ref=badge_large)