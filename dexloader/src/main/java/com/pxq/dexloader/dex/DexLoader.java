package com.pxq.dexloader.dex;


import android.util.Log;

import dalvik.system.DexClassLoader;

public class DexLoader {
    private static final String TAG = "DexLoader";

    String dexPath;
    String optimizedDirectory;
    String libraryPath;
    ClassLoader parent;

    public DexLoader(String dexPath, String optimizedDirectory, String libraryPath, ClassLoader parent) {
        this.dexPath = dexPath;
        this.optimizedDirectory = optimizedDirectory;
        this.libraryPath = libraryPath;
        this.parent = parent;
    }

    private DexClassLoader getLoader() {
        return new DexClassLoader(dexPath, optimizedDirectory, libraryPath, parent);
    }

    public Class<?> findClass(String clazz){
        try {
            Class<?> aClass = getLoader().loadClass(clazz);
            if (aClass != null) {
                Log.e(TAG, "findClass: " + aClass.getCanonicalName());
            }
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
