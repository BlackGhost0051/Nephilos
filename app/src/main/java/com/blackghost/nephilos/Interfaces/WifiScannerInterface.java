package com.blackghost.nephilos.Interfaces;

import com.blackghost.nephilos.Objects.AP;

import java.util.List;

public interface WifiScannerInterface {
    void onScanCompleted(List<AP> result);
    void onPermissionRequired(String permission);
}
