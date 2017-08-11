# react-native-wakeful

React Native module for holding the android's `PowerManager.WakeLock` and `WifiManager.WifiLock`.


## Installation

First install the npm package from your app directory:

```javascript
npm install react-native-wakeful --save
```

Then link it automatically using:

```javascript
react-native link react-native-wakeful
```

### Manual Installation on Android

This is not necessary if you have used `react-native link`

Edit `android/settings.gradle` to declare the project directory:
```
include ':react-native-wakeful'
project(':react-native-wakeful').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-wakeful/android')
```

Edit `android/app/build.gradle` to declare the project dependency:
```
dependencies {
  ...
  compile project(':react-native-wakeful')
}
```

Edit `android/app/src/main/java/.../MainApplication.java` to register the native module:

```java
...
import com.ironsmile.RNWakeful.RNWakefulPackage; // <-- New
...

public class MainApplication extends Application implements ReactApplication {
  ...
  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
        new MainReactPackage(),
        new RNWakefulPackage() // <-- New
    );
  }
```

For older versions of React Native you need to edit `MainActivity.java` instead:

```java
...
import com.ironsmile.RNWakeful.RNWakefulPackage; // <-- New
...

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ...
    @Override
  protected void onCreate(Bundle savedInstanceState){
    ...
    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      ...
      .addPackage(new MainReactPackage())
      .addPackage(new RNWakefulPackage()) // <-- New
      ...
  }
```


## Basic usage

Make sure you request the android.permission.WAKE_LOCK permission in an <uses-permission> element of the application's manifest.

!TODO