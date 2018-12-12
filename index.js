/**
 * @providesModule react-native-apk
 */

import { NativeModules } from 'react-native';
var RNAPK = NativeModules.ReactNativeAPK;

var rnApk = {
  /**
   * Get a list of all the apps that are installed on the device.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * const apps = ReactNativeAPK.getApps();
   * console.log(apps); // Array [...]
   *
   * @return {Array} Returns an array containing the apps.
   */
  getApps: function() {
    return RNAPK.getApps();
  },

  /**
   * Get a list of the apps the user has installed on the device.
   * This method only returns the apps that the user has EXPLICITLY
   * installed, ie. via F-Droid/MDroid, Play Store, or by using an APK file.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * const apps = ReactNativeAPK.getNonSystemApps();
   * console.log(apps); // Array [...]
   *
   * @return {Array} Returns an array containing the apps.
   */
  getNonSystemApps: function() {
    return RNAPK.getNonSystemApps();
  },

  /**
   * Install an application to the device.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * ReactNativeAPK.installApp(RNFetchBlob.fs.dirs.DocumentsDir + '/org.mozilla.klar-0.1.0.apk');
   *
   * @param {String} packagePath - Path to the APK to install.
   * @return {void}
   */
  installApp: function(packagePath) {
    return RNAPK.installApp(packagePath);
  },

  /**
   * Uninstall an application from the device.
   * This will send an intent to the system which will show a modal
   * asking the user if it is willing to do so.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * const uninstalled = ReactNativeAPK.uninstallApp('org.mozilla.klar');
   * console.log(uninstalled); // true
   *
   * @param {String} packageName - Package's name of the application.
   * @return {Boolean} Returns true if the app has been uninstalled.
   */
  uninstallApp: function(packageName) {
    return RNAPK.uninstallApp(packageName);
  },

  /**
   * A method to check whether an app is installed on the device
   * by checking for a package name.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * ReactNativeAPK.installApp(RNFetchBlob.fs.dirs.DocumentsDir + '/org.mozilla.klar-0.1.0.apk');
   * let isInstalled = ReactNativeAPK.isAppInstalled();
   * console.log(isInstalled); // true
   * ReactNativeAPK.uninstallApp('org.mozilla.klar');
   * let isInstalled = ReactNativeAPK.isAppInstalled();
   * console.log(isInstalled); // false
   *
   * @param {String} packageName - Package's name of the application.
   * @return {Boolean} Returns true if app is installed, false if not.
   */
  isAppInstalled: function(packageName) {
    return RNAPK.isAppInstalled(packageName);
  },

  /**
   * A method to get an application version based on it's package name.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * const klarVersion = ReactNativeAPK.getAppVersion('org.mozilla.klar');
   * console.log(klarVersion); // 0.1.0
   *
   * @param {String} packageName - Package's name of the application.
   * @return {String} Returns the version of the package.
   */
  getAppVersion: function(packageName) {
    return RNAPK.getAppVersion(packageName);
  },

  /**
   * A method to run an application based on package's name.
   *
   * @example
   * import ReactNativeAPK from "react-native-apk";
   * ReactNativeAPK.runApp('org.mozilla.klar');
   *
   * @param {String} packageName - Package's name of the application.
   * @return {void}
   */
  runApp: function(packageName) {
    return RNAPK.runApp(packageName);
  }
};

module.exports = rnApk
