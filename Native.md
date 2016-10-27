#Native封装那些事


###类型转化

> 使用 @ReactMethod 注解的方法 使 Java 与 JavaScript 中类型 一一对应。


#####Android

+  Bool -> Boolean
+  Number -> Integer、Double、Float
+  String -> String
+  function -> Callback
+  Object -> ReadableMap
+  Array -> ReadableArray

#####IOS

+  Bool -> BOOL,NSNumber
+  Number -> NSInteger，float,double ,CGFloat,NSNumber
+  String -> NSString
+  function -> RCTResponseSenderBlock
+  Object -> NSDictionary
+  Array -> NSArray


###常用类
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;


import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import com.facebook.react.common.MapBuilder;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;


import javax.annotation.Nullable;

@ReactProp(name = "borderColor", customType = "Color")
@ReactProp(name="maxVisibleValueCount",defaultInt = 50)


@ReactMethod
 public void login(String userName, String password, Promise promise)

使用Promise比使用Callback更加的简洁，还能更加灵活的在多线程之间进行切换

Promise 一般在 NativeModule, Callback 一般在 ViewManager

### 自定义Package
+ createNativeModules 实现原生模块
+ createJSModules
+ createViewManagers 实现原生UI

```
package com.toast;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReactToastPackage implements ReactPackage {

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
		return Arrays.<NativeModule>asList(new ReactToastModule(reactContext));
	}


  	@Override
	public List<Class<? extends JavaScriptModule>> createJSModules() {
	    return Collections.emptyList();
	}

	@Override
	public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
	    // return Arrays.<ViewManager>asList(new LinearGradientManager());
	    return Collections.emptyList();
	}

}
```

###自定义NativeModule

```
package com.toast;

import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;

public class ReactToastModule extends ReactContextBaseJavaModule {

  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";

  public ReactToastModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "Toast";
  }

  @ReactMethod
  public void show(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }
}
```


###自定义UI

```
```