#React Native 从入门到高阶 资料积累


[一切从官方](http://facebook.github.io/react-native/docs/getting-started.html)


####使用View接受触屏控件

```

1、定义
componentWillMount(){
    this._panResponder = PanResponder.create({
        onStartShouldSetPanResponder: (evt, gestureState) => true,
        onMoveShouldSetPanResponder: (evt, gestureState) => true,
        onPanResponderGrant: this._handlePanResponderGrant,
        onPanResponderMove: this._handlePanResponderMove,
        onPanResponderRelease: this._handlePanResponderEnd,
        onPanResponderTerminate: this._handlePanResponderEnd,
    })
}

2、调用
{...this._panResponder.panHandlers}

3、属性解释

View.props.onStartShouldSetResponder：用户开始触摸屏幕的时候，是否愿意成为响应者[默认false,冒泡传递]；
View.props.onMoveShouldSetResponder：在每一个触摸点开始移动的时候，再询问一次是否响应触摸交互；
View.props.onResponderGrant：要开始响应触摸事件了；
View.props.onResponderReject：响应者现在另有其人，而且暂时不会放权，另作安排；
View.props.onResponderMove：用户正在屏幕上移动手指；
View.props.onResponderRelease：触摸操作结束收触发；
View.props.onResponderTerminationRequest：有其它组件请求接替响应者，当前View是否放权；
View.props.onResponderTerminate：响应权已经交出；

4、回调evt参数
changedTouches:[]
identifier:int
locationX:float
localtionY:float
pageX:float
pageY:float
target:int
timestamp:float(cal speed)
touches:[]

```

###[Navigator:路由](http://blog.csdn.net/tiem_erhu/article/details/51089482)

```
如果你得到了一个navigator对象的引用，则可以调用许多方法来进行导航： 
getCurrentRoutes() - 获取当前栈里的路由，也就是push进来，没有pop掉的那些 
jumpBack() - 跳回之前的路由，当然前提是保留现在的，还可以再跳回来，会给你保留原样 
jumpForward() - 进行跳转到相当于当前页面的下一个页面 
jumpTo(route) - 根据传入的路由信息，跳转到一个指定的路由，并且不卸载 
push(route) - 导航切换到一个新的页面，新的页面会进入栈中，可以使用jumpfonward()跳转回去 
pop() - 当前页面弹出栈，跳转下一个页面，并且卸载掉当前场景 
replace(route) - 使用传入的路由的指定页面替换掉当前场景 
replaceAtIndex(route, index) - 传入路由以及位置索引，使用该路由指 定的页面跳转到指定位置的页面 
replacePrevious(route) - 传入路由，通过指定路由替换前一个页面 
immediatelyResetRouteStack(routeStack) - 用新的路由数组来重置路由栈 
popToRoute(route) - pop，弹出相关页面，跳转到路由指定的页面，弹出的页面会被删除 
popToTop() - pop，弹出页面，导航到栈找中的第一个页面，弹出来的所有页面都被卸载 
resetTo(route) -进行导航到新的页面，并且重置整个路由栈

```

[打包一：](http://www.jianshu.com/p/32a99c273be1)

[打包二：](http://www.jianshu.com/p/32a99c273be1)

1.执行react-native init androiddemo（根据实际情况来创建）项目。
2.在androiddemo目录中找到路径/android/app/src/main，并在该目录下新建《assets》文件夹，这个文件夹存放android app的资源文件。
3.在/androiddemo/路径下执行react-native start命令，待执行完毕以后再执行以下命令
curl -k http://localhost:8081/index.android.bundle > android/app/src/main/assets/index.android.bundle
该命令的意思是将index.android.bundle下载并保存到assets资源文件夹中
这句命令是重点，如果assets目录中不存在该文件，则打包的apk在执行时显示空白。
4.打包的apk在未签名的情况下,在手机中（非root）是不允许安装的，所以需要添加gradle的android keystore配置，

生成key...
keytool ~  jdk_xx/jre/bin/keytool

1、执行
[keytool -genkey -alias mykey -keyalg RSA -validity 40000 -keystore demo.keystore](http://jingyan.baidu.com/article/59703552e877f98fc00740f0.html)

#####说明：
-genkey 产生密钥

-alias mykey 别名 mykey

-keyalg RSA 使用RSA算法对签名加密

-validity 40000 有效期限4000天

-keystore demo.keystore

```
在build.gradle文件中，具体配置如下
  //签名
signingConfigs{
    release {
        storeFile file("/Volumes/Android.KeyStore/AndroidDebug.keystore")
        storePassword "密码"
        keyAlias "keyAlias的名字"
        keyPassword "密码"
    }
}
 buildTypes {
    release {
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        signingConfig signingConfigs.release //添加这句话引用签名配置
    }
}
```
注意一下配置顺序
5.在androiddemo/android/目录中执行gradle assembleRelease命令，打包后的文件在    androiddemoi/android/app/build/outputs/apk目录中，例如app-release.apk。如果打包碰到    问题可以先执行 gradle clean 清理一下。
6.将apk复制到手机中安装运行 







###原生调用(原生模块调用、原生UI调用)


一、[调用原生](http://blog.csdn.net/woaini705/article/details/50899946)
```
一，继承 ReactContextBaseJavaModule 实现如下方法  自定义方法用 @ReactMethod注释
/**
            * 日志打印module
    * Created by ybj on 2016/2/26.
            */
    public class ReactLogModule extends ReactContextBaseJavaModule {
        private static final String MODULE_NAME="Log";
    private static final String TAG_KEY = "TAG";
    private static final String TAG_VALUE = "LogModule";

    public ReactLogModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }
    @ReactMethod
    public  void  d(String tag,String message){
        Log.d(tag,message);
       /* WritableMap params = Arguments.createMap();
        params.putString("TAG",tag);
        params.putString("MSG",message);
        getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("logInConsole", params);//对应的javascript层的事件名为logInConsole，注册该事件即可进行回调*/
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String,Object> constants= MapBuilder.newHashMap();
        constants.put(TAG_KEY,TAG_VALUE);
        return constants;
    }
}
二，继承ReactPackage，实现如下

/**
 * 日志打印 需要打印日志注册this
 * Created by ybj on 2016/2/26.
 */
public class ReactLogPackage implements ReactPackage {


    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules=new ArrayList<NativeModule>();
        ReactLogModule reactLogModule=new ReactLogModule(reactContext);
        modules.add(reactLogModule);
        return modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
三 添加package
mReactInstanceManager = ReactInstanceManager.builder()
        .setApplication(((Activity) mContext).getApplication())

        .setJSBundleFile(bundleFile)
                //  .setJSMainModuleName("test")
        .setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler() {
            @Override
            public void handleException(Exception e) {
            }
        })
        .addPackage(new MainReactPackage())
        .addPackage(new ReactLogPackage())
       

        .setUseDeveloperSupport(false)
        .setInitialLifecycleState(LifecycleState.RESUMED)
        .build();
mReactRootView.startReactApplication(mReactInstanceManager, "OperationActivity", null);
```



二、[调用原生组件](http://blog.csdn.net/woaini705/article/details/50900034)

```
**
 * 图片加载控件
 * Created by ybj on 2016/2/24.
 */
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    public static final String REACT_CLASS = "RCTTBImage";
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    @Override
    protected ReactImageView createViewInstance(ThemedReactContext reactContext) {
       // ReactImageView imageView=  new ReactImageView(reactContext);
        ReactImageView imageView = new ReactImageView(reactContext, Fresco.newDraweeControllerBuilder(), null);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return imageView;
    }
/*
    @ReactProp(name = PROP_TEST_ID)
    @Override
    public void setTestId(ImageView view, int testId) {
        super.setTestId(view, testId);
    }
*/

   //图片url设置
    @ReactProp(name = "urlPath")
    public void setUrlPath(ReactImageView view,@Nullable String url) {
        Log.d("TAG_YBJ", "setUrl" + url);
        if (url.toLowerCase().endsWith(".gif")) {
           /* DraweeController controller = Fresco.newDraweeControllerBuilder().setUri(url)
                    .setAutoPlayAnimations(true).build();
            this.setController(controller);*/

        } else {
            /*this.setImageURI(Uri.parse(url));*/
        }
        view.setSource(url);
      //  Image13lLoader.getInstance().loadImage(url,view);
    }
    //边角度数设置
    @ReactProp(name = "borderRadius", defaultFloat = 0f)
    public void setBorderRadius(ReactImageView view, float borderRadius) {
        view.setBorderRadius(borderRadius);
    }
    //缩放类型
    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(ReactImageView view, @Nullable String resizeMode) {
        view.setScaleType(ImageResizeMode.toScaleType(resizeMode));
    }

}
二，实现 ReactPackage
/**
 * 图片加载 需要加载图片需要注册this
 * Created by ybj on 2016/2/24.
 */
        public class ReactImagePackage implements ReactPackage {
            @Override
            public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
                return Collections.emptyList();
            }

            @Override
            public List<Class<? extends JavaScriptModule>> createJSModules() {
                return Collections.emptyList();
            }

            @Override
            public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
                return Arrays.<ViewManager>asList(
                        new ReactImageManager());

    }
}
三，添加package
        .setApplication(((Activity) mContext).getApplication())
        .setJSBundleFile(bundleFile)
                //  .setJSMainModuleName("test")
        .setNativeModuleCallExceptionHandler(new NativeModuleCallExceptionHandler() {
            @Override
            public void handleException(Exception e) {
            }
        })
      /* .setBundleAssetName("webviewindex.bundle")
        .setJSMainModuleName("webviewindex")*/
        .addPackage(new TBRnPackage())

        .addPackage(new ReactImagePackage())

        .setUseDeveloperSupport(false)
        .setInitialLifecycleState(LifecycleState.RESUMED)
        .build();
mReactRootView.startReactApplication(mReactInstanceManager, "BZMHomeOperationActivity", null);

myreact.addView(mReactRootView);
```




























###项目开发常用第三方库





[感应器IOS、](https://github.com/pwmckenna/react-native-motion-manager)
[感应器Android、](https://github.com/kprimice/react-native-sensor-manager)
[仿IOS Picker-ok、](https://github.com/beefe/react-native-picker)
[选择列表框、](https://github.com/d-a-n/react-native-modal-picker)
[仿IOS Picker-稍差、](https://github.com/lesliesam/react-native-wheel-picker)
[音频、](https://github.com/jsierles/react-native-audio)
[视频、](https://github.com/react-native-community/react-native-video)
[百度地图](https://github.com/lovebing/react-native-baidu-map)
[地图、](https://github.com/lelandrichardson/react-native-maps)
[Radio按钮、](https://github.com/moschan/react-native-simple-radio-button )
[checkbox按钮、](https://github.com/peng8/react-native-checkbox)
[屏幕截图、](https://github.com/gre/react-native-view-shot )
[拍照、相册并剪切、](https://github.com/ivpusic/react-native-image-crop-picker)
[Toast for Android IOS、](https://github.com/crazycodeboy/react-native-easy-toast)
[可滑动的Tab控制器、](https://github.com/skv-headless/react-native-scrollable-tab-view)



[下拉框、](https://github.com/sohobloo/react-native-modal-dropdown)
[IOS选择列表、](https://github.com/eyaleizenberg/react-native-custom-action-sheet)
[仿QQ向滑动删除、](https://github.com/dancormier/react-native-swipeout)
[ios&Android系统分享、](https://github.com/EstebanFuentealba/react-native-share )
[微信SDK集成示例、](https://github.com/beefe/react-native-wechat-ios)
[微信SDK集成示例、](https://github.com/beefe/react-native-wechat-android)
[微信SDK集成示例、](https://github.com/beefe/react-native-wechat-android/wiki/%E5%85%BC%E5%AE%B9iOS )
[第三方登录微博QQ免费，微信收费、](https://github.com/ParryQiu/react-native-open-share)
[IOS摇一摇、](https://cnpmjs.org/package/react-native-shake-event-ios)
[常用图标、](https://github.com/corymsmith/react-native-icons)
[强大的表单处理控件、](https://github.com/gcanti/tcomb-form-native )
[高德定位](https://github.com/xiaobuu/react-native-amap-location)
[Device Info](https://github.com/rebeccahughes/react-native-device-info)



[Android使用gif问题](http://blog.csdn.net/codetomylaw/article/details/52692824)
[react native项目列表、](https://react.parts/native reactNative)
[React/Native/webpack项目、](https://js.coach)
[React Native常见错误、](http://blog.csdn.net/goodchangyong/article/details/51323930) 
[最接近原生APP体验的高性能框架、](https://github.com/dcloudio/mui/tree/master/examples/hello-mui)


[ios&Android 股票、](https://github.com/7kfpun/FinanceReactNative)
[韩剧影视APP、](https://github.com/helengray/XiFan)
[OA项目、](https://github.com/talentjiang/react_native_office)
[blog项目、](https://github.com/togayther/react-native-cnblogs)
[知呼、](https://github.com/race604/ZhiHuDaily-React-Native)
[贷贷助手客户端、](https://github.com/liuhongjun719/react-native-DaidaiHelperNew)
[官方演示App ](https://github.com/facebook/react-native/tree/master/Examples)
[ReactNativeRubyChina ](https://github.com/henter/ReactNativeRubyChina)
[HackerNews-React-Native ](https://github.com/iSimar/HackerNews-React-Native)
[React-Native新闻客户端 ](https://github.com/tabalt/ReactNativeNews)
[newswatch(新闻客户端) ](https://github.com/bradoyler/newswatch-react-native)
[buyscreen(购买页面) ](https://github.com/appintheair/react-native-buyscreen)
[V2EX客户端]( https://github.com/samuel1112/v2er)
[react-native-todo ](https://github.com/joemaddalone/react-native-todo)
[react-native-beer ](https://github.com/muratsu/react-native-beer)
[react-native-stars ](https://github.com/86/react-native-stars)
[模仿天猫首页的app ](https://github.com/baofen14787/react-native-demo)
[ReactNativeChess ](https://github.com/csarsam/ReactNativeChess)
[react native 编写的音乐软件 ](https://github.com/Johnqing/miumiu)
[react-native-pokedex ](https://github.com/ababol/react-native-pokedex)
[CNode-React-Native ](https://github.com/SFantasy/CNode-React-Native)
[8tracks电台客户端 ](https://github.com/voronianski/EightTracksReactNative)
[React-Native实现的计算器 ](https://github.com/yoxisem544/Calculator-using-React-Native)
[房产搜索app ](https://github.com/jawee/react-native-PropertyFinder)
[知乎专栏app ](https://github.com/LeezQ/react-native-zhihu-app)
[ForeignExchangeApp ](https://github.com/peralmq/ForeignExchangeApp)

###开发技巧

+ adb devices 查看模拟器或真机是否连接

+ adb connect 127.0.0.1 手动连接服务器

+ adb kill-server

+ adb start-server

+ adb占用时可关闭pc机上的手机助手等

+ IOS模拟器弹出键盘：需要勾选 Hardware -> Keyboard -> Toggle Software keyboard

+ 当前设备的最小线宽: 1/PixelRatio.get() 

+ 强烈建议样式与组件写在同一文件中




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

var {xxx} = require('./filepath');

```

####常想不到的冷属性


+ Text中省略号属性： ellipsizeMode , 最新版本推出的属性, 显示不完全省略的位置, 一般配合numberOfLines 使用。 可选值'head', 'middle', 'tail', 'clip'， clip 只能在ios中使用, tail是默认值, 省略尾巴 显示方式如:”abcd…”




#####[Android 使用 GIF](http://blog.csdn.net/codetomylaw/article/details/52692824)
```
在build.gradle 中加入：
compile 'com.facebook.fresco:animated-gif:0.13.0'
然后就可以直接使用了，对，就这么简单。
<Image source={{uri:loadgif}} style={{width:20,height:20}}/>
```


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



###关于布局

```

static getFontScale()返回字体大小的缩放因子

获取到的比率是用来计算文字的绝对大小，所以对计算的精度要求很高的元素，应该使用这个比率。

例如用户在通过 Setting > Display > Font Size 设置设备的字体显示大小，这个值就会改变，默认的情况下返回设备的像素比。

3、 单位 dp 转换成 px

static getPixelSizeForLayoutSize(layoutSize:number)


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


###开发规范


###关于原生组件的封装

+ [自定义原生UI组件和VideoView视频播放器开发](http://www.lcode.org/react-native%e5%ae%9e%e6%88%98%e7%b3%bb%e5%88%97%e6%95%99%e7%a8%8b%e4%b9%8b%e8%87%aa%e5%ae%9a%e4%b9%89%e5%8e%9f%e7%94%9fui%e7%bb%84%e4%bb%b6%e5%92%8cvideoview%e8%a7%86%e9%a2%91%e6%92%ad%e6%94%be/)

+ [添加自定义UI组件](http://lib.csdn.net/article/react/25126)

+ [封装一个ReactNativeAndroid 的 NativeModule组件](http://blog.sina.com.cn/s/blog_4e1e357d0102yug0.html)


###组件封装步骤


自定义属性(attr、children)

1、View原属性继承：...View.propTypes

2、声明自定义属性类型

3、属性默认值

4、获取属性 this.props.xxx 及 this.props.children


#####ES5
```
React.createClass({

  propTypes: {
    //一、要求属性是JavaScript基本类型
    // 默认情况下，这些 prop 都是可传可不传的
    optionalArray: React.PropTypes.array,
    optionalBool: React.PropTypes.bool,
    optionalFunc: React.PropTypes.func,
    optionalNumber: React.PropTypes.number,
    optionalObject: React.PropTypes.object,
    optionalString: React.PropTypes.string,

    // 二、要求属性是可渲染节点(数字，字符串，DOM 元素或包含这些类型的数组)
    optionalNode: React.PropTypes.node,

    // 三、要求属性是某个React元素
    optionalElement: React.PropTypes.element,

    // 四、要求属性是某个指定类的实例
    optionalMessage: React.PropTypes.instanceOf(Message),

    // 五、要求属性取值为几个特定的值
    optionalEnum: React.PropTypes.oneOf(['News', 'Photos']),

    // 六、属性可以为指定类型中的任意一个
    optionalUnion: React.PropTypes.oneOfType([
      React.PropTypes.string,
      React.PropTypes.number,
      React.PropTypes.instanceOf(Message)
    ]),


    // 七、指定类型组成的数组
    optionalArrayOf: React.PropTypes.arrayOf(React.PropTypes.number),

    // 八、指定类型的属性构成的对象
    optionalObjectOf: React.PropTypes.objectOf(React.PropTypes.number),

    // 九、特定形状参数的对象
    optionalObjectWithShape: React.PropTypes.shape({
      color: React.PropTypes.string,
      fontSize: React.PropTypes.number
    }),

    // 十、任意类型
    optionAny: React.PropTypes.any

    // 十一、非空限定（在类型后加上 `isRequired`）
    requiredFunc: React.PropTypes.func.isRequired,
    requiredAny: React.PropTypes.any.isRequired,

  

    // 自定义验证器。不要直接使用 `console.warn` 或抛异常，因为这样 `oneOfType` 会失效。 
    customProp: function(props, propName, componentName) {
      if (!/matchme/.test(props[propName])) {
        return new Error('Validation failed!');
      }
    }
  },

  // 十二、属性默认值（此属性isRequired要去掉）
  getDefaultProps: function(){
      promptToUser: '确定吗?'
  }
});

```

#####ES6
```
class Demo extends Component {

  static propTypes = {
    // 继承View属性
    ...View.propTypes,
    //一、要求属性是JavaScript基本类型(默认情况下，这些 prop 都是可传可不传的。)
    optionalArray: PropTypes.array,
    optionalBool: PropTypes.bool,
    optionalFunc: PropTypes.func,
    optionalNumber: PropTypes.number,
    optionalObject: PropTypes.object,
    optionalString: PropTypes.string,

    // 二、要求属性是可渲染节点(数字，字符串，DOM 元素或包含这些类型的数组)
    optionalNode: PropTypes.node,

    // 三、要求属性是某个React元素
    optionalElement: PropTypes.element,

    // 四、要求属性是某个指定类的实例
    optionalMessage: PropTypes.instanceOf(Message),

    // 五、要求属性取值为几个特定的值
    optionalEnum: PropTypes.oneOf(['News', 'Photos']),

    // 六、属性可以为指定类型中的任意一个
    optionalUnion: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number,
      PropTypes.instanceOf(Message)
    ]),


    // 七、指定类型组成的数组
    optionalArrayOf: PropTypes.arrayOf(PropTypes.number),

    // 八、指定类型的属性构成的对象
    optionalObjectOf: PropTypes.objectOf(PropTypes.number),

    // 九、特定形状参数的对象
    optionalObjectWithShape: PropTypes.shape({
      color: PropTypes.string,
      fontSize: PropTypes.number
    }),

    // 十、任意类型
    optionAny: PropTypes.any

    // 十一、非空限定（在类型后加上 `isRequired`）
    requiredFunc: PropTypes.func.isRequired,
    requiredAny: PropTypes.any.isRequired,

  

    // 自定义验证器。不要直接使用 `console.warn` 或抛异常，因为这样 `oneOfType` 会失效。 
    customProp: function(props, propName, componentName) {
      if (!/matchme/.test(props[propName])) {
        return new Error('Validation failed!');
      }
    }
  };

  // 十二、属性默认值（此属性isRequired要去掉）
  static defaultProps = {
      promptToUser: '确定吗?'
  };
}
```

###子节点是节点的属性

```
React.Children.map(this.props.children, function (child) {
  return {...child}
})

1. React.Children.map(object children, function fn [, object context])
----遍历子元素，映射为一个新的子元素集合（跟 ES5 的 Array.map 差不多）
2. React.Children.forEach(object children, function fn [, object context])
----遍历子元素，对每一个子元素执行回调，但不像上述的 map 那样最终返回一个新的集合（跟 ES5 的 Array.forEach 差不多）
3. React.Children.count(object children)
----返回子元素的总数
4. React.Children.only(object children)
----返回仅有的一个子元素，否则（没有子元素或超过一个子元素）报错且不渲染任何东西

```