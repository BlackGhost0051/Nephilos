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
        - [Settings](#settings)
    - [JNILibs](#jnilibs)
        - [arp_spoof.c](#arp_spoofc)
        - [fill_arp_table.c](#fill_arp_tablec)
        - [get_interfaces.c](#get_interfacesc)

# Documentation

## Fragments

### Wifi Scanner
### Mac Scanner
### Port Scanner
### Send Request
### Arp Spoof
### Settings

## JNILibs
### arp_spoof.c
### fill_arp_table.c
### get_interfaces.c



Now includes:
- WIFI scanner
- MAC scanner
- Port scanner ( TCP )
- Request send ( test )
- ARP spoof ( test )

Planning:
- DHCP spoof
- ICMP send
- Request send ( POST, GET...)
- URL to IP
- Beacon Flooding Attack ( 802.11 fake AP)
- Deauthenticate Clients ( 802.11 )