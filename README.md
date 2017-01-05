# program-that-computes-checksum-for-all-TCP-segments-sent-from-client-to-sender
program that computes checksum for all TCP segments sent from client to sender.

program that computes checksum for all TCP segments sent from client to sender for the following.
The values given below are only examples and should not be hard coded. Either these should be taken thru an input file.¬
for example:
. Client IP Address: e.g. 192.168.1.42¬
. Client port number: e.g. 12345¬
. Client ISN used: e.g. 23456¬
. Server IP Address: e.g. 192.168.1.22¬
. Server Port number: e.g. 8080¬
. Server ISN used: e.g. 56789¬
. Data transferred: "Networks"¬
. TCP receive window size: 32768¬
Assuming that none of TCP options are used and there is no urgent data. Compute the checksum and display
its value for all packets exchanged for¬
a) all 3 packets in connection setup i.e. SYN-SYN/ACK-ACK¬
b) Both the packets in Data transfer from client to server and its ack¬
c) all 4 packets in Connection close initiated by Client i.e. FIN-ACK-FIN-ACK¬
