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

> js调用

```
var CircleView = requireNativeComponent("CircleView",{
  name:"CircleView", // 用于调试信息显示
  propTypes: {       // 重要：它定义了该组件拥有哪些属性可以使用
    ...View.propTypes
  }
});
```

>原生UI中的回调实现

```
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;

...

private final EventDispatcher mEventDispatcher;

...

mEventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();

...

mEventDispatcher.dispatchEvent(new ItemSelectedEvent(getId(), mCenterPaint.getColor()));

...

class ItemSelectedEvent extends Event<ItemSelectedEvent> {

    public static final String EVENT_NAME = "xxxxx";

    private final int mValue;

    protected ItemSelectedEvent(int viewTag,  int value) {
        super(viewTag);
        mValue = value;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("data", mValue);
        return eventData;
    }
}

。。。

import com.facebook.react.common.MapBuilder;
import java.util.Map;

...

@Override
public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        ItemSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onChoosed")
    );
}

...

onChoosed={(e)=>{
    console.log(e.nativeEvent.data);
}}
```












> zhui

```
getName : 该方法是暴露给 RN 端调用的名称
createViewInstance : 该方法用来返回 PtrFrameLayout 的实例
getCommandsMap : 接收 RN 发来的命令
receiveCommand : 处理 RN 发来的命令

addEventEmitters : 发送到 RN
getExportedCustomDirectEventTypeConstants : 暴露给 RN (监听 PtrFrameLayout事件并回调到 RN 方法中)


public class ReactPtrLayout extends ViewGroupManager<PtrFrameLayout> {

    private static final int STOP_REFRESH=1;

    @Override
    public String getName() {
        return "PtrFrameLayout";
    }

    @Override
    protected PtrFrameLayout createViewInstance(ThemedReactContext reactContext) {
        final PtrFrameLayout rootView= (PtrFrameLayout)LayoutInflater.from(reactContext).inflate(R.layout.ptr_layout,null);
        return  rootView;
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("stop_refresh",STOP_REFRESH);
    }

    @Override
    public void receiveCommand(PtrFrameLayout root, int commandId, @Nullable ReadableArray args) {
        switch (commandId){
            case STOP_REFRESH:
                root.completeRefresh(PtrState.REFRESH_SUCCESS);
                return;
        }
    }

    @Override
    protected void addEventEmitters(final ThemedReactContext reactContext, final PtrFrameLayout view) {
        super.addEventEmitters(reactContext, view);
        view.setOnRefreshListener(new PtrFrameLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher()
                        .dispatchEvent(new RefreshEvent(view.getId(), SystemClock.nanoTime()));
            }
        });
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("topRefresh", MapBuilder.of("registrationName", "onRefresh"))
                .build();
    }


}

```