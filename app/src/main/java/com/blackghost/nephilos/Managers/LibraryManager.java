package com.blackghost.nephilos.Managers;

import android.content.Context;

public class LibraryManager {
    // Add
    // Getting so files from apk ( manual ) and copping to data/app/<package>/libs/ABI

    public static String getLibraryDir(Context context) {
        return context.getApplicationInfo().nativeLibraryDir;
    }
}
