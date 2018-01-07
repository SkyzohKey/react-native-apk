var RNAPK = require("react-native").NativeModules.ReactNativeAPK;

export default class ReactNativeAPK {
  static getApps = () => {
    return RNAPK.getApps();
  };

  static getNonSystemApps = () => {
    return RNAPK.getNonSystemApps();
  };

  static installApp = packagePath => {
    return RNAPK.installApp(packagePath);
  };

  static uninstallApp = packageName => {
    return RNAPK.uninstallApp(packageName);
  };

  static isAppInstalled = packageName => {
    return RNAPK.isAppInstalled(packageName);
  };

  static getAppVersion = packageName => {
    return RNAPK.getAppVersion(packageName);
  };
}
