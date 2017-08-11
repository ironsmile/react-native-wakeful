package com.ironsmile.RNWakeful;

import android.os.PowerManager;
import android.net.wifi.WifiManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class RNWakefulModule extends ReactContextBaseJavaModule {
  PowerManager.WakeLock wakeLock = null;
  WifiManager.WifiLock wifiLock = null;
  boolean locksHeld = false;
  ReactApplicationContext context;
  final static Object NULL = null;

  public RNWakefulModule(ReactApplicationContext context) {
    super(context);
    this.context = context;

    PowerManager pm = (PowerManager)this.context.getSystemService(ReactApplicationContext.POWER_SERVICE);
    this.wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "RNWakeful");

    WifiManager wm = (WifiManager)this.context.getSystemService(ReactApplicationContext.WIFI_SERVICE);
    this.wifiLock = wm.createWifiLock(WifiManager.WIFI_MODE_FULL, "RNWakefulWifi");
  }

  @Override
  public String getName() {
    return "RNWakeful";
  }

  @ReactMethod
  public boolean isHeld() {
    return this.locksHeld;
  }

  @ReactMethod
  public void acquire() {
    if (this.isHeld()) {
      return;
    }
    this.wakeLock.acquire();
    this.wifiLock.acquire();
    this.locksHeld = true;
  }

  @ReactMethod
  public void release() {
    if (!this.isHeld()) {
      return;
    }
    this.wakeLock.release();
    this.wifiLock.release();
    this.locksHeld = false;
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put("IsAndroid", true);
    return constants;
  }
}
