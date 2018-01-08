import { NativeModules } from "react-native";
const RNAPK = NativeModules.ReactNativeAPK;

export default class ReactNativeAPK {
  /**
   * Get a list of all the apps that are installed on the device.
   *
   * @return {Array} Returns an array containing the apps.
   */
  static getApps = () => {
    return RNAPK.getApps();
  };

  /**
   * Get a list of the apps the user has installed on the device.
   * This method only returns the apps that the user has EXPLICITLY
   * installed, ie. via F-Droid/MDroid, Play Store, or by using an APK file.
   *
   * @return {Array} Returns an array containing the apps.
   */
  static getNonSystemApps = () => {
    return RNAPK.getNonSystemApps();
  };

  /**
   * Install an application to the device.
   *
   * @param {String} packagePath - Path to the APK to install.
   * @return {void}
   */
  static installApp = packagePath => {
    return RNAPK.installApp(packagePath);
  };

  /**
   * Uninstall an application from the device.
   * This will send an intent to the system which will show a modal
   * asking the user if it is willing to do so.
   *
   * @param {String} packageName - Package's name of the application.
   * @return {Boolean} Returns true if the app has been uninstalled.
   */
  static uninstallApp = packageName => {
    return RNAPK.uninstallApp(packageName);
  };

  /**
   * A method to check whether an app is installed on the device
   * by checking for a package name.
   *
   * @param {String} packageName - Package's name of the application.
   * @return {Boolean} Returns true if app is installed, false if not.
   */
  static isAppInstalled = packageName => {
    return RNAPK.isAppInstalled(packageName);
  };

  /**
   * A method to get an application version based on it's package name.
   *
   * @param {String} packageName - Package's name of the application.
   * @return {String} Returns the version of the package.
   */
  static getAppVersion = packageName => {
    return RNAPK.getAppVersion(packageName);
  };

  /**
   * A method to run an application based on package's name.
   *
   * @param {String} packageName - Package's name of the application.
   * @return {void}
   */
  static runApp = packageName => {
    return RNAPK.runApp(packageName);
  };
}
