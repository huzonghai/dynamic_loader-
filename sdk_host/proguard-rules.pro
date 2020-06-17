# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontusemixedcaseclassnames
# 不混淆第三方引用的库
-dontskipnonpubliclibraryclasses
# 不做预校验
-dontpreverify
# 忽略警告
-ignorewarning
#关闭压缩
-dontshrink
#关闭优化
-dontoptimize
#关闭预效验
-dontpreverify
#表示ProGuard对代码迭代优化测试
-optimizationpasses 5


-keep class com.server.core.Start
-keepclassmembernames class com.server.core.Start{
        public static void instance(java.lang.String);
        public static void instance(android.content.Context);
  }


#-obfuscationdictionary dic_var_10004.txt
#-classobfuscationdictionary dic_string.txt
#-packageobfuscationdictionary dic_string.txt

-keepclassmembers class * {
   @android.webkit.JavascriptInterface <methods>;
}