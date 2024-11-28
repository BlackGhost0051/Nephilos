package com.blackghost.nephilos.Objects;

public class AP {
    private String ssid;
    private String bssid;
    private String signalStrength;
    private String encryption;
    private int channel;
    private int frequency;

    public AP(String ssid, String bssid, String signalStrength, String encryption, int frequency){
        this.ssid = ssid;
        this.bssid = bssid;
        this.signalStrength = signalStrength;
        this.encryption = encryption;
        this.frequency = frequency;
    }

    public String getSsid() {
        return ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public String getSignalStrength() {
        return signalStrength;
    }

    public String getEncryption() {
        return encryption;
    }

    public int getChannel() {
        calculateChannel(frequency);
        return channel;
    }

    public int getFrequency() {
        return frequency;
    }

    private void calculateChannel(int frequency){
        if(frequency >= 2412 && frequency <= 2484){
            channel = (frequency - 2412) / 5 + 1;
        } else if(frequency >= 5170 && frequency <= 5825){
            channel = (frequency - 5170) / 5 + 34;
        } else {
            channel = -1;
        }
    }
}
