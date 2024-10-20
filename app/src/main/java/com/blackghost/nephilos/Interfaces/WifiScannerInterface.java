package com.blackghost.nephilos.Interfaces;

public interface WifiScannerInterface {
    void onScanCompleted(String result);
    void onPermissionRequired(String permission);
}
