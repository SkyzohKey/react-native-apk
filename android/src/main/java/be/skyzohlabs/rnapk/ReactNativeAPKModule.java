package be.skyzohlabs.rnapk;

import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.Intent;
import android.net.Uri;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Nullable;

public class ReactNativeAPKModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public ReactNativeAPKModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "ReactNativeAPK";
  }

  @ReactMethod
  public Boolean isAppInstalled(String packageName, Callback errorCallback, Callback successCallback) {
    try {
      PackageInfo pInfo = this.reactContext.getPackageManager().getPackageInfo(packageName,
          PackageManager.GET_ACTIVITIES);

      successCallback(true);
    } catch (PackageManager.NameNotFoundException e) {
      successCallback(false);
    }
  }

  @ReactMethod
  public void installApp(String packagePath) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.parse(packagePath), "application/vnd.android.package-archive");
    this.reactContext.startActivity(intent);
  }

  @ReactMethod
  public Boolean uninstallApp(String packageName, Callback errorCallback, Callback successCallback) {
    if (!this.isAppInstalled(packageName)) {
      successCallback(false);
    }

    Intent intent = new Intent(Intent.ACTION_DELETE);
    intent.setData(Uri.parse("package:" + packageName));
    this.reactContext.startActivity(intent);
    successCallback(true);
  }

  @ReactMethod
  public String getAppVersion(String packageName, Callback errorCallback, Callback successCallback) {
    if (!this.isAppInstalled(packageName)) {
      successCallback(null);
    }

    try {
      PackageInfo pInfo = this.reactContext.getPackageManager().getPackageInfo(packageName, 0);

      successCallback(pInfo.versionName);
    } catch (PackageManager.NameNotFoundException e) {
      successCallback(null);
    }
  }

  @ReactMethod
  public List<String> getApps(Callback errorCallback, Callback successCallback) {
    List<PackageInfo> packages = this.reactContext.getPackageManager().getInstalledPackages(0);

    List<String> ret = new ArrayList<>();
    for (final PackageInfo p : packages) {
      ret.add(p.packageName);
    }
    successCallback(ret);
  }

  @ReactMethod
  public List<String> getNonSystemApps(Callback errorCallback, Callback successCallback) {
    List<PackageInfo> packages = this.reactContext.getPackageManager().getInstalledPackages(0);

    List<String> ret = new ArrayList<>();
    for (final PackageInfo p : packages) {
      if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
        ret.add(p.packageName);
      }
    }
    successCallback(ret);
  }

  @ReactMethod
  public void runApp(String packageName) {
    // TODO: Allow to pass Extra's from react.
    Intent launchIntent = this.reactContext.getPackageManager().getLaunchIntentForPackage(packageName);
    //launchIntent.putExtra("test", "12331");
    this.reactContext.startActivity(launchIntent);
  }

  /*@Override
  public @Nullable Map<String, Object> getConstants() {
      Map<String, Object> constants = new HashMap<>();
  
      constants.put("getApps", getApps());
      constants.put("getNonSystemApps", getNonSystemApps());
      return constants;
  }*/
}