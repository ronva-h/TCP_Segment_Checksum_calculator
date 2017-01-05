Program Execution:
	1. Please create a input.txt file and include as follows:
		Client IP address
		Client Port
		Client ISN
		Server IP address
		Server Port
		Server ISN
		Message to be sent
		Window Size

	2.The server should be running.
		we have uploaded the server program also ,you can check with that also
		for running our server code:
		"javac server.java"
		"java server" to run

	3.For running the client code
		 In command line type-
		
		 "javac client.java"
		 "java client" to run
	 
Description:

	The uploaded assignment contains client.java(client),server.java,input.txt
	Question was to find the TCP Checksum,
	To find the TCP checksum we are taking the ClientIP, ClientPort,ServerIP,Serverport,Protocol Number,Segmentlength,sequence number,acknowledgement number,Data,windowsize,flags(along with reserved bits and data offsets).
	we are not considering the optionslength and options data field.
	 we are taking the clientisn from the file to calculate the next ones.
	 we are taking the serverisn from the file to calculate the next ones.

	 when calculating the data checksum,
	 	 segmentlength = headerlength(20)+datalength+1 
	 	 we add 1, because last character is the line feed. 

	 while calculating the SYN,ACK,FIN... checksum
	 	segmentlength = headerlength(20).

	 By taking the above things we will compute the checksum in hexadecimal.

Difficulties faced:

	 We were not able to match the checksum which was shown in the wireshark.
	 we were not knowing what all should be added to compute the checksum.
	 coding for the carry in hexadecimal addition.

Reffered Links:

	http://stackoverflow.com/questions/14699249/java-socket-binding-to-local-port
	http://www.roman10.net/2011/11/27/how-to-calculate-iptcpudp-checksumpart-1-theory/
	http://stackoverflow.com/questions/24038683/wireshark-checksum-does-not-match


