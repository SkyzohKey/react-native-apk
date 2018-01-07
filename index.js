import { NativeModules } from "react-native";
const RNAPK = NativeModules.ReactNativeAPK;

export default class ReactNativeAPK {
  /**
   * Get a list of all the apps that are installed on the device.
   *
   * @return {Array} returns an array containing the apps.
   */
  static getApps = () => {
    return RNAPK.getApps();
  };

  /**
   * Get a list of the apps the user has installed on the device.
   * This method only returns the apps that the user has EXPLICITLY
   * installed, ie. via F-Droid/MDroid, Play Store, or by using an APK file.
   *
   * @return {Array} returns an array containing the apps.
   */
  static getNonSystemApps = () => {
    return RNAPK.getNonSystemApps();
  };

  /**
   * Install an application to the device.
   *
   * @param {String} packagePath - The path to the APK to install.
   */
  static installApp = packagePath => {
    return RNAPK.installApp(packagePath);
  };

  /**
   * Uninstall an application from the device.
   * This will send an intent to the system which will show a modal
   * asking the user if it is willing to do so.
   *
   * @param {String} packageName - The package name of the application.
   */
  static uninstallApp = packageName => {
    return RNAPK.uninstallApp(packageName);
  };

  /**
   * A method to check whether an app is installed on the device
   * by checking for a package name.
   *
   * @param {String} packageName - The package name of the application.
   */
  static isAppInstalled = packageName => {
    return RNAPK.isAppInstalled(packageName);
  };

  /**
   * A method to get an application version based on it's package name.
   *
   * @param {String} packageName - The package name of the application.
   */
  static getAppVersion = packageName => {
    return RNAPK.getAppVersion(packageName);
  };
}
