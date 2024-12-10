# Nephilos

![](./app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp "Nephilos")

Nephilos is a set of Android networking tools for advanced network management and testing. Root access is required for these features, as they provide low-level access to networking capabilities and perform actions that require system-level permissions.

# Content

- [Documentation](#documentation)
    - [Fragments](#fragments)
        - [Wifi Scanner](#wifi-scanner)
        - [Mac Scanner](#mac-scanner)
        - [Port Scanner](#port-scanner)
        - [Send Request](#send-request)
        - [Arp Spoof](#arp-spoof)
        - [DNS Lookup](#dns-lookup)
        - [Settings](#settings)
    - [Structure](#structure)
      - [Dir structure](#dir-structure)
    - [JNILibs](#jnilibs)
        - [Overview](#overview)
        - [Build System](#build-system)
        - [JNI C Source Files](#jni-c-source-files)
          - [arp_spoof.c](#arp_spoofc)
          - [fill_arp_table.c](#fill_arp_tablec)
          - [get_interfaces.c](#get_interfacesc)

# Documentation

## Fragments

### Wifi Scanner

The Wifi Scanner fragment scans and displays available Wi-Fi networks, giving insights into nearby access points.

Features include:

```
SSID: Network name.
BSSID: Access point’s MAC address.
Signal Strength: Measured in dBm.
Encryption: Encryption type (e.g., WPA2, WEP).
Channel: 3
Frequency: Channel frequency in GHz (e.g., 2.4 GHz or 5 GHz bands).
```

### Mac Scanner

The Mac Scanner identifies devices on the local network by their MAC addresses and displays relevant device information. This is useful for discovering connected devices within the subnet.

- Command Used: `su -c ip n show`
- Output: Lists all devices on the network with details on MAC addresses and current statuses.

### Port Scanner

The Port Scanner fragment allows users to scan TCP ports on a target IP address to check for open or closed TCP ports.

### Send Request

The Send Request fragment facilitates sending HTTP requests to a specified URL. It allows users to specify request methods (GET, POST) and view the server response.

### Arp Spoof

TEST

The Arp Spoof fragment enables ARP spoofing on the local network, allowing users to intercept traffic between devices by spoofing their ARP responses.


### DNS Lookup

TEST

The DNS Lookup fragment allows users to perform DNS queries to resolve domain names to IP addresses.

### Settings

The Settings fragment manages application settings and preferences, allowing users to configure preferences.

## Structure

### Dir structure
```
Nephilos
├── README.md
├── app

arp.xml
dns_24.xml
http_24.xml
mac.xml
save_24.xml
settings_24.xml
storage_24.xml
white_border.xml
white_down_border.xml
wifi_24.xml

MacScannerTask.java
PortScannerTask.java
WifiScannerTask.java

ArpSpoofFragment.java
DNSLookupFragment.java
MacScannerFragment.java
MainFragment.java
PortScannerFragment.java
RequestFragment.java
SettingsFragment.java
WifiScannerFragment.java
RequestInterface.java
WifiScannerInterface.java

DNSManager.java
LibraryManager.java
RequestManager.java

AP.java
Device.java

MainActivity.java
```

## JNILibs

### Overview

The JNILibs section consists of C source files compiled using JNI (Java Native Interface) to interact with low-level network functions directly from Android. JNI allows these C files to run on Android devices, providing capabilities that require native code execution for performance or system-level access, such as ARP spoofing, manipulating ARP tables, and retrieving network interfaces. Once compiled, each file is executed through an external process.

### Build System

The `CMakeLists.txt` file sets up the build system for compiling these native C files and managing their output paths to create `.so` libraries, making them accessible in the Android environment.

### JNI C Source Files

#### arp_spoof.c
#### fill_arp_table.c
#### get_interfaces.c

The `get_interfaces.c` file retrieves and displays a list of network interfaces available on the device. It uses system calls to obtain interface names, providing insight into network hardware that can be utilized for network-related operations.



Now includes:
- WIFI scanner
- MAC scanner
- Port scanner ( TCP )
- Request send ( GET POST )
- DNS Lookup ( test )
- ARP spoof ( test )

Planning:
- DHCP spoof
- ICMP send
- Request send (...)
- URL to IP
- Beacon Flooding Attack ( 802.11 Flooding AP)
- Free AP
- MITM
- Deauthenticate Clients ( 802.11 )
- Save pcap
- Web tester ( like burp )

Comment
- Save info
- Save format