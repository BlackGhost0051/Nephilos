# Nephilos

![](./app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp "Nephilos")

Android set of tools for working with the network (need ROOT).

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
    - [JNILibs](#jnilibs)
        - [arp_spoof.c](#arp_spoofc)
        - [fill_arp_table.c](#fill_arp_tablec)
        - [get_interfaces.c](#get_interfacesc)

# Documentation

## Fragments

### Wifi Scanner

The Wifi Scanner fragment is responsible for scanning and displaying available Wi-Fi networks. 

It allows users to view the:
```
SSID
BSSID
Signal Strength
Encryption
Frequency
```
### Mac Scanner

The Mac Scanner fragment scans the local network for devices and displays their MAC addresses. It provides insights into connected devices within the same subnet.

I use this command to get info:
```
su -c ip n show
```

### Port Scanner

The Port Scanner fragment allows users to scan TCP ports on a target IP address to check for open or closed TCP ports.

### Send Request

The Send Request fragment facilitates sending HTTP requests to a specified URL. It allows users to specify request methods (GET, POST) and view the server response.

### Arp Spoof

TEST

The Arp Spoof fragment enables ARP spoofing on the local network, allowing users to intercept traffic between devices by spoofing their ARP responses.


### DNS Lookup
### Settings

The Settings fragment manages application settings and preferences, allowing users to configure preferences.

## JNILibs
### arp_spoof.c
### fill_arp_table.c
### get_interfaces.c



Now includes:
- WIFI scanner
- MAC scanner
- Port scanner ( TCP )
- Request send ( GET POST )
- ARP spoof ( test )

Planning:
- DHCP spoof
- ICMP send
- Request send (...)
- URL to IP
- Beacon Flooding Attack ( 802.11 Flooding AP)
- Free AP
- aMITM
- Deauthenticate Clients ( 802.11 )