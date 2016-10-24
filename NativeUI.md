#原生UI调用

> CircleView.java

```
package com.hzj;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.facebook.react.uimanager.PixelUtil;

/**
 * 圆形组件组件基础类
 */
public class CircleView extends View {

    private final String TAG = "CircleView";
    private Paint mPaint; // 画笔
    private float mRadius;  // 圆的半径

    public CircleView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint); // 画一个半径为100px的圆
        Log.d(TAG, "绘图");
    }

    /**
     * 设置圆的背景色
     * @param color
     */
    public void setColor(Integer color) {
        mPaint.setColor(color); // 设置画笔颜色
        invalidate();   // 更新画板
    }

    /**
     * 设置圆的半径
     * @param radius
     */
    public void setRadius(Integer radius) {
        /**
         * 由于JS传过的数字是dip单位,需要转换为实际像素
         * 使用com.facebook.react.uimanager包中的PixelUtil,进行转换
         */
        mRadius = PixelUtil.toPixelFromDIP(radius);
        invalidate();
    }
}
```

> CircleManager.java

```
package com.hzj;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * 圆形组件基础类管理器
 */
public class CircleManager extends SimpleViewManager<CircleView> {

    /**
     * 设置js引用名
     * @return String
     */
    @Override
    public String getName() {
        return "MCircle";
    }

    /**
     * 创建UI组件实例
     * @param reactContext
     * @return CircleView
     */
    @Override
    protected CircleView createViewInstance(ThemedReactContext reactContext) {
        return new CircleView(reactContext);
    }

    /**
     * 传输背景色参数
     * @param view
     * @param color
     */
    @ReactProp(name = "color")
    public void setColor(CircleView view, Integer color) {
        view.setColor(color);
    }

    /**
     * 传输半径参数
     * @param view
     * @param radius
     */
    @ReactProp(name = "radius")
    public void setRadius(CircleView view, Integer radius) {
        view.setRadius(radius);
    }
}
```

> MainPackage.java

```
package com.hzj;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 自定义组件模块注册类
 */
public class MainPackage implements ReactPackage {

    /**
     * 创建原生模块
     * @param reactContext
     * @return
     */
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    /**
     * 创建原生UI组件控制器
     * @param reactContext
     * @return
     */
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
            new CircleManager()
        );
    }
}
```