package com.blackghost.nephilos.Managers;

import android.content.Context;

public class LibraryManager {
    static private final String arpSpoofLibrary = "libarp_spoof.so";

    // Add
    // Getting so files from apk ( manual ) and copping to data/app/<package>/libs/ABI

    public static String getLibraryDir(Context context) {
        return context.getApplicationInfo().nativeLibraryDir;
    }

    public static String getArpSpoofLibrary() {
        return arpSpoofLibrary;
    }
}
