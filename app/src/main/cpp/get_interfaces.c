#include <stdio.h>
#include <string.h>
#include <sys/ioctl.h>
#include <net/if.h>
#include <unistd.h>
#include <sys/socket.h>

int main() {
    struct ifconf ifc;
    struct ifreq ifr[50];
    int sockfd;
    int interfaces_count;

    sockfd = socket(AF_INET, SOCK_DGRAM, 0);
    if (sockfd < 0) {
        perror("socket");
        return 1;
    }

    ifc.ifc_len = sizeof(ifr);
    ifc.ifc_ifcu.ifcu_buf = (char *)ifr;

    if (ioctl(sockfd, SIOCGIFCONF, &ifc) < 0) {
        perror("ioctl");
        return 1;
    }

    interfaces_count = ifc.ifc_len / sizeof(struct ifreq);

    for (int i = 0; i < interfaces_count; i++) {
        printf("%s\n", ifr[i].ifr_name);
        fflush(stdout);
    }

    close(sockfd);
    return 0;
}
