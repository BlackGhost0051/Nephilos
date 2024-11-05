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
    - [JNILibs](#jnilibs)
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