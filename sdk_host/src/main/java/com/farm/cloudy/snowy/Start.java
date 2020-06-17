package com.farm.cloudy.snowy;

import android.content.Context;

import java.io.File;

import dalvik.system.DexClassLoader;

import static android.content.Context.MODE_PRIVATE;

public class Start {
    private static ImplementFactory _implementFactory;

    public static void instance(Context context,String url, String fileName) {
        String dexPath = context.getFilesDir().getAbsolutePath() + File.separator + fileName;
        String optimizedDirectory = context.getDir("dex", MODE_PRIVATE).getAbsolutePath();
        try {
            DexClassLoader dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, null, context.getClassLoader());
            _implementFactory = (ImplementFactory) dexClassLoader.loadClass("com.server.core.Start").newInstance();
            _implementFactory.init(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImplementFactory getImplementFactory() {
        return _implementFactory;
    }
}
