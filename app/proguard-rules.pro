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


#youappiSDK
-dontskipnonpubliclibraryclassmembers
-keep class android.support.annotation.**{ *;}
-keep class com.google.gson.**{ *;}
-keep class com.google.android.gms.**{*;}
-keep class com.youappi.sdk.**{*;}
-keep interface com.youappi.sdk.**{*;}
-keep enum com.youappi.sdk.**{*;}
-keepclassmembers class * {
   @android.webkit.JavascriptInterface <methods>;
}
-keep class com.ai.**{ *;}
-keep class com.iab.**{ *;}
#youappiSDK


-keepattributes *Annotation*
-dontwarn ihzt.ugl.kqpqe.**
-dontwarn snpf.xsy.sjblc.**
-keep class ihzt.ugl.kqpqe.**{*;}
-keep class snpf.xsy.sjblc.**{*;}
-keep class com.ijwqer.rar.** {*;}