package be.skyzohlabs.rnapk;

import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Binder;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

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
  public void isAppInstalled(String packageName, Callback cb) {
    try {
      PackageInfo pInfo = this.reactContext.getPackageManager().getPackageInfo(packageName,
          PackageManager.GET_ACTIVITIES);

      cb.invoke(true);
    } catch (PackageManager.NameNotFoundException e) {
      cb.invoke(false);
    }
  }

  @ReactMethod
  public void installApp(String packagePath) {
    File toInstall = new File(packagePath);
    if (Build.VERSION.SDK_INT >= 24) {
      String callingPackageName = this.reactContext.getPackageManager().getNameForUid(Binder.getCallingUid());
      Uri apkUri = FileProvider.getUriForFile(this.reactContext, callingPackageName+".fileprovider", toInstall);
      Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
      intent.setData(apkUri);
      intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      this.reactContext.startActivity(intent);
    } else {
      Uri apkUri = Uri.fromFile(toInstall);
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      this.reactContext.startActivity(intent);
    }
  }

  @ReactMethod
  public void uninstallApp(String packageName, Callback cb) {
    Intent intent = new Intent(Intent.ACTION_DELETE);
    intent.setData(Uri.parse("package:" + packageName));
    this.reactContext.startActivity(intent);
    cb.invoke(true);
  }

  @ReactMethod
  public void getAppVersion(String packageName, Callback cb) {
    try {
      PackageInfo pInfo = this.reactContext.getPackageManager().getPackageInfo(packageName, 0);

      cb.invoke(pInfo.versionName);
    } catch (PackageManager.NameNotFoundException e) {
      cb.invoke(false);
    }
  }

  @ReactMethod
  public void getApps(Callback cb) {
    List<PackageInfo> packages = this.reactContext.getPackageManager().getInstalledPackages(0);

    List<String> ret = new ArrayList<>();
    for (final PackageInfo p : packages) {
      ret.add(p.packageName);
    }
    cb.invoke(ret);
  }

  @ReactMethod
  public void getNonSystemApps(Callback cb) {
    List<PackageInfo> packages = this.reactContext.getPackageManager().getInstalledPackages(0);

    List<String> ret = new ArrayList<>();
    for (final PackageInfo p : packages) {
      if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
        ret.add(p.packageName);
      }
    }
    cb.invoke(ret);
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