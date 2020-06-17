package com.example.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.farm.cloudy.snowy.Start;


public class MainActivity extends Activity {

    public static final String FILE_NAME = "plugin.jar";
    public String URL = "http://www.baidu.com?from=234542";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        //模拟网络请求插件热更包
        //把dex包（plugin.jar）放入dada/data/xx.xx.xx/files 目录下
        Utils.extractAssets(newBase, FILE_NAME);
    }

    /**
     * sdk.host 为外层代码，apk集成
     * sdk.dex  为动态加载plugin
     * 目的:  测试apk与插件的双向通讯
     * 流程：通过SDK初始化（正常插件需要从服务器下发，放到应用指定位置，这里为了测试直接copy到dada/data/xx.xx.xx/files）
     *       传入url给到插件，插件注册解屏广播，启动SDK中的1像素activity，同时传给activity url与time两个字段，
     *       activity拿到数据后，又调用插件中的performTask方法，把当前的activity上下文和数据又传给了插件
     *       插件拿到数据后，webView加载网页，并在网页加载完成，延迟time后，做了一个模拟点击事假
     *       至此整个事件流程完成
     *
     *
     * 总结：目前demo阶段仅仅使用DexClassLoader 简单的动态加载纯jar代码，【例如实现插件（包APK资源）中的组件更新，资源更新的话
     *       需参考三方的热修复等成熟框架】
     *
     *       插件中调用apk中代码可以使用反射来调用（demo中是通过Intent直接启动的activity,）
     *       apk中调用插件的任何代码就需要DexClassLoader来调用了，
     *       demo中通过DexClassLoader，loadClass方式强转成对应接口作为通讯方式直接调用的
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化SDK
        Start.instance(this, URL, FILE_NAME);
    }

}
