package com.colorpickerview;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;

import java.util.Map;

/**
 * 圆形组件基础类管理器
 */
public class ColorPickerViewManager extends SimpleViewManager<ColorPickerView> {

    /**
     * 设置js引用名
     * @return String
     */
    @Override
    public String getName() {
        return "ColorPickerView";
    }

    /**
     * 创建UI组件实例
     * @param reactContext
     * @return CircleView
     */
    @Override
    protected ColorPickerView createViewInstance(ThemedReactContext reactContext) {
        return new ColorPickerView(reactContext);
    }

    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
            ItemSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onChoosed")
        );
    }
}