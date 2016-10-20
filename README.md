#React Native 从入门到高阶 资料积累

###相关库

```
https://github.com/pwmckenna/react-native-motion-manager  for IOS
https://github.com/kprimice/react-native-sensor-manager  感应器 for Android
https://github.com/d-a-n/react-native-modal-picker
https://github.com/beefe/react-native-picker
https://github.com/AigeStudio/WheelPicker
https://github.com/lesliesam/react-native-wheel-picker
https://github.com/d-a-n/react-native-modal-picker/ 
https://github.com/sohobloo/react-native-modal-dropdown
https://github.com/eyaleizenberg/react-native-custom-action-sheet
https://github.com/dcloudio/mui/tree/master/examples/hello-mui



https://github.com/jsierles/react-native-audio
https://github.com/dancormier/react-native-swipeout
https://github.com/lelandrichardson/react-native-maps 地图

https://github.com/moschan/react-native-simple-radio-button Radio按钮


https://github.com/EstebanFuentealba/react-native-share  ios&Android系统分享

https://github.com/beefe/react-native-wechat-ios
https://github.com/beefe/react-native-wechat-android
https://github.com/beefe/react-native-wechat-android/wiki/%E5%85%BC%E5%AE%B9iOS    微信SDK集成示例

https://github.com/ParryQiu/react-native-open-share 第三方登录微博QQ免费，微信收费 [wechat,weibo,qq,renren ...]

https://github.com/7kfpun/FinanceReactNative  ios&Android 股票

https://github.com/gre/react-native-view-shot 屏幕截图

http://lib.csdn.net/article/react/25126  React Native 添加自定义UI组件
http://blog.sina.com.cn/s/blog_4e1e357d0102yug0.html 如何自定义封装一个ReactNativeAndroid 的 NativeModule组件
http://www.lcode.org/react-native%e5%ae%9e%e6%88%98%e7%b3%bb%e5%88%97%e6%95%99%e7%a8%8b%e4%b9%8b%e8%87%aa%e5%ae%9a%e4%b9%89%e5%8e%9f%e7%94%9fui%e7%bb%84%e4%bb%b6%e5%92%8cvideoview%e8%a7%86%e9%a2%91%e6%92%ad%e6%94%be/   React Native实战系列教程之自定义原生UI组件和VideoView视频播放器开发
https://github.com/helengray/XiFan  个使用react native实现的韩剧影视APP
http://blog.csdn.net/guohesheng/article/details/52213705 第三方截图

https://github.com/dancormier/react-native-swipeout

https://github.com/ivpusic/react-native-image-crop-picker

react-native-image-crop-picker 0.9.7 图片选择器可拍照- react-native": "^0.33.0","react": "^15.3.2",


http://blog.csdn.net/goodchangyong/article/details/51323930 React Native资料库



<ListView
            automaticallyAdjustContentInsets={false}
            keyboardDismissMode="on-drag"
            keyboardShouldPersistTaps={true}
            showsVerticalScrollIndicator={false}
          /> 解决scroll与touch冲突


http://www.cnblogs.com/pofabs/p/5109021.html  ScrollView中按钮反应慢，设置scrollview的keyboardShouldPersistTaps={true}


ellipsizeMode , 最新版本推出的属性, 显示不完全省略的位置, 一般配合numberOfLines 使用。 可选值'head', 'middle', 'tail', 'clip'， clip 只能在ios中使用, tail是默认值, 省略尾巴 显示方式如:”abcd…”



# before installing node-gyp on windows
npm install --global --production windows-build-tools

# install node-gyp globally
npm install -g node-gyp



https://cnpmjs.org/package/react-native-shake-event-ios

https://github.com/crazycodeboy/react-native-easy-toast  IOS Android Toast

https://github.com/corymsmith/react-native-icons


https://github.com/gcanti/tcomb-form-native 强大的表单处理控件支持Json模式～可插拔和感觉


https://react.parts/native reactNative 项目列表
https://js.coach   所有项目（React/Native/webpack/...）


IOS9.0+ fetch request need some settings in the file  Info.plist, instead of the default setting for  NSAppTransportSecurity ...                                                            <key>NSAppTransportSecurity</key>
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


IOS TextInput获得焦点时，模拟器弹出键盘：需要勾选 Hardware -> Keyboard -> Toggle Software keyboard


react native 执行fetch在安卓没问题，ios上却提示网络请求失败？                                                     iOS 强制要求使用 https，如果你请求的接口是 http协议就会出错，临时解决办法是在 Info.plist 里面加上：

    <key>NSAppTransportSecurity</key>
    <dict>
      <key>NSAllowsArbitraryLoads</key>
      <true/>
    </dict>
但是这个办法只能今年底前使用，之后苹果汇禁止这么用，所以尽快部署 https 接口

IOS   我在此处不使用catch处理错误，控制台会一直报Unhandled promise rejection错误


borderwidth : 1/PixelRatio.get() 
返回的就是当前设备的最小线宽

自Facebook 开源出React 后，天猫技术团队就在一直关注，并对比现有Html5系的Hybird解决方案的差异性。
React-Native 是Android端实现实现动态部署的另一种思路，绕过dexLoad【一些现有的Android插件框架】，同时实现了前端人员和客户端人员资源的共享，总之是非常值得期待和尝试

返回字体大小的缩放因子

static getFontScale()
1
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