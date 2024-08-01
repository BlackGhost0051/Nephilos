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

int main(){
    struct  my_arp_packet
    {
        unsigned char	h_dest[ETH_ALEN];	   /* destination eth addr */
        unsigned char	h_source[ETH_ALEN];	   /* source ether addr */
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


    return 0;
}