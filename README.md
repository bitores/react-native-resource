#React Native 从入门到高阶 资料积累

###相关库


###

[感应器IOS](https://github.com/pwmckenna/react-native-motion-manager)

[感应器Android](https://github.com/kprimice/react-native-sensor-manager)

[仿IOS Picker-ok](https://github.com/beefe/react-native-picker)

[选择列表框](https://github.com/d-a-n/react-native-modal-picker)

[仿IOS Picker-稍差](https://github.com/lesliesam/react-native-wheel-picker)

[音频](https://github.com/jsierles/react-native-audio)

[视频](https://github.com/react-native-community/react-native-video)

[地图](https://github.com/lelandrichardson/react-native-maps)

[Radio按钮](https://github.com/moschan/react-native-simple-radio-button )

[屏幕截图](https://github.com/gre/react-native-view-shot )

[拍照、相册并剪切](https://github.com/ivpusic/react-native-image-crop-picker)

[Toast for Android IOS](https://github.com/crazycodeboy/react-native-easy-toast)


###
[下拉框](https://github.com/sohobloo/react-native-modal-dropdown)

[IOS选择列表](https://github.com/eyaleizenberg/react-native-custom-action-sheet)

[仿QQ向滑动删除](https://github.com/dancormier/react-native-swipeout)

[ios&Android系统分享](https://github.com/EstebanFuentealba/react-native-share )

[微信SDK集成示例](https://github.com/beefe/react-native-wechat-ios)

[微信SDK集成示例](https://github.com/beefe/react-native-wechat-android)

[微信SDK集成示例](https://github.com/beefe/react-native-wechat-android/wiki/%E5%85%BC%E5%AE%B9iOS )

[第三方登录微博QQ免费，微信收费](https://github.com/ParryQiu/react-native-open-share)

[IOS摇一摇](https://cnpmjs.org/package/react-native-shake-event-ios)

[常用图标](https://github.com/corymsmith/react-native-icons)

[强大的表单处理控件](https://github.com/gcanti/tcomb-form-native )

[ios&Android 股票](https://github.com/7kfpun/FinanceReactNative)

[韩剧影视APP](https://github.com/helengray/XiFan)

[react native项目列表](https://react.parts/native reactNative)

[React/Native/webpack项目](https://js.coach)

[React Native常见错误](http://blog.csdn.net/goodchangyong/article/details/51323930) 

[最接近原生APP体验的高性能框架](https://github.com/dcloudio/mui/tree/master/examples/hello-mui)




###开发技巧

+ adb devices 查看模拟器或真机是否连接

+ adb connect 127.0.0.1 手动连接服务器

+ adb kill-server

+ adb start-server

+ adb占用时可关闭pc机上的手机助手等

+ IOS模拟器弹出键盘：需要勾选 Hardware -> Keyboard -> Toggle Software keyboard

+ 当前设备的最小线宽: 1/PixelRatio.get() 




####项目中常加入第三方项目技巧

+ npm i -S react-native-xxx

+ rnpm link react-native-xxxx



####export 与 import

#####方式一

```
export default xxx; 

调用 
import xxx from './filepath'
```

#####方式二

```
export xx;
export yy;
export zz;

调用

import {xx, yy, zz} from './filepath';
```

#####方式三

```
module.exports = xxx;

调用

var xxx = './filepath';
```

#####方式四

```
exports.xxx = ...;

调用

var {xxx} = './filepath';

```

####常想不到的冷属性


+ TextInput中省略号属性： ellipsizeMode , 最新版本推出的属性, 显示不完全省略的位置, 一般配合numberOfLines 使用。 可选值'head', 'middle', 'tail', 'clip'， clip 只能在ios中使用, tail是默认值, 省略尾巴 显示方式如:”abcd…”

+



#####[ScrollView中按钮反应慢](http://www.cnblogs.com/pofabs/p/5109021.html)
```
 ScrollView中按钮反应慢，设置scrollview的keyboardShouldPersistTaps={true}
```
#####ListView中scroll与touch冲突
```
<ListView
    automaticallyAdjustContentInsets={false}
    keyboardDismissMode="on-drag"
    keyboardShouldPersistTaps={true}
    showsVerticalScrollIndicator={false}
  /> 
```


####IOS9.0+ fetch时报错,是因为新版本作了一些限制，所以需要进行一些设置

```
IOS9.0+ fetch request need some settings in the file  Info.plist, instead of the default setting for  NSAppTransportSecurity 
<key>NSAppTransportSecurity</key>
<dict>
  <key>NSAllowsArbitraryLoads</key>
  <true/>
  <key>NSExceptionDomains</key>
  <dict>
    <key>localhost</key>
    <dict>
      <key>NSTemporaryExceptionAllowsInsecureHTTPLoads</key>
      <true/>
    </dict>
  </dict>
</dict>



react native 执行fetch在安卓没问题，ios上却提示网络请求失败？
iOS 强制要求使用 https，如果你请求的接口是 http协议就会出错，临时解决办法是在 Info.plist 里面加上：

    <key>NSAppTransportSecurity</key>
    <dict>
      <key>NSAllowsArbitraryLoads</key>
      <true/>
    </dict>

但是这个办法只能今年底前使用，之后苹果汇禁止这么用，所以尽快部署 https 接口
IOS   我在此处不使用catch处理错误，控制台会一直报Unhandled promise rejection错误
```




http://lib.csdn.net/article/react/25126  React Native 添加自定义UI组件
http://blog.sina.com.cn/s/blog_4e1e357d0102yug0.html 如何自定义封装一个ReactNativeAndroid 的 NativeModule组件
http://www.lcode.org/react-native%e5%ae%9e%e6%88%98%e7%b3%bb%e5%88%97%e6%95%99%e7%a8%8b%e4%b9%8b%e8%87%aa%e5%ae%9a%e4%b9%89%e5%8e%9f%e7%94%9fui%e7%bb%84%e4%bb%b6%e5%92%8cvideoview%e8%a7%86%e9%a2%91%e6%92%ad%e6%94%be/   React Native实战系列教程之自定义原生UI组件和VideoView视频播放器开发

http://blog.csdn.net/guohesheng/article/details/52213705 第三方截图




###关于布局

```

static getFontScale()返回字体大小的缩放因子

获取到的比率是用来计算文字的绝对大小，所以对计算的精度要求很高的元素，应该使用这个比率。

例如用户在通过 Setting > Display > Font Size 设置设备的字体显示大小，这个值就会改变，默认的情况下返回设备的像素比。

3、 单位 dp 转换成 px

static getPixelSizeForLayoutSize(layoutSize:number)
1
在UI开发的过程中最长使用到的方法

自适应布局方案
自适应布局方案采用了，将 UI 等比放大到 app 上的自适应布局。

UI 给默认 640 的图，采用 pxToDp 函数，即可将 UI 等比放大到 Android 机器上。

import {Dimensions} from 'react-native';

// 58 app 只有竖屏模式，所以可以只获取一次 width
const deviceWidthDp = Dimensions.get('window').width;
// UI 默认给图是 640
const uiWidthPx = 640;

function pxToDp(uiElementPx) {
return uiElementPx *  deviceWidthDp / uiWidthPx;
}

export default pxToDp;
调用方法

import pxToDp from './pxToDp';

...
<View style={{width:pxToDp(100),height:pxToDp(100)}}></View>
...
如果 UI 图默认不是 640 宽，可以通过 PS 设置为 640 宽

PixelRatio.get() === 1
    mdpi Android devices (160 dpi)
PixelRatio.get() === 1.5
    hdpi Android devices (240 dpi)
PixelRatio.get() === 2
    iPhone 4, 4S
    iPhone 5, 5c, 5s
    iPhone 6
xhdpi Android devices (320 dpi)
    PixelRatio.get() === 3
iPhone 6 plus
    xxhdpi Android devices (480 dpi)
PixelRatio.get() === 3.5
    Nexus 6
```