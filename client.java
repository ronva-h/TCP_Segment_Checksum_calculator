import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
 
public class client
{
    private static Socket socket = new Socket();
    public static void main(String args[])
    {
		Scanner scan = new Scanner(System.in);
		int inputport=0;
		int inputip=0;
		int inputclientport=0;
		int datalength=0;
		int p1,p2,p3;
		int segmentlength=0;
		String messagehex1="";
		String messagehex2="";
		String messagehex3="";
		String tcpprotocol = "0006";
		String inputstringclientip ="";
		String inputstringclientport = "";
		String inputstringclientisn ="";
		String hexinputstringclientisn="";
		String acknowledgementnumber="00000000";
		String inputstringip ="";
		String inputstringport ="";
		String inputstringisn ="";
		String inputstringmessage ="";
		String windowsize ="";
		String hexwindowsize ="";
		BufferedReader inputfile=null;
		String hexacks1seqnum="";
		int acks1seqnum=0;
		ArrayList<String> inputlist = new ArrayList<String>(); 
		
		ArrayList<String> syn = new ArrayList<String>(); 
		ArrayList<String> synack = new ArrayList<String>(); 
		ArrayList<String> acks1 = new ArrayList<String>();
		ArrayList<String> acks2 = new ArrayList<String>();
		ArrayList<String> acks3 = new ArrayList<String>();
		ArrayList<String> pshack = new ArrayList<String>();
		ArrayList<String> finack1 = new ArrayList<String>();
		ArrayList<String> finack2 = new ArrayList<String>();
		
		try{
			inputfile = new BufferedReader(new FileReader("input.txt"));
			inputstringclientip = inputfile.readLine();
            
			inputstringclientport = inputfile.readLine();
			inputclientport=Integer.parseInt(inputstringclientport);
			
			inputstringclientisn = inputfile.readLine();
			
			inputstringip = inputfile.readLine();
			
			inputstringport = inputfile.readLine();
			
			inputport=Integer.parseInt(inputstringport);
			
			inputstringisn = inputfile.readLine();
			
			inputstringmessage = inputfile.readLine();
			inputstringmessage = inputstringmessage+"\n";
			
			datalength=inputstringmessage.length();
			segmentlength=20+datalength; 
			
			windowsize = inputfile.readLine();
            
			inputfile.close();
		}
		
		catch (IOException e)
		{
			System.out.println(e);
		} 
		
        String[] clientip = inputstringclientip.split("\\.");
        
        String cip01=(  (Integer.toHexString(Integer.parseInt(clientip[0])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(clientip[0]))) : (""+Integer.toHexString(Integer.parseInt(clientip[0])))  ) + ( (Integer.toHexString(Integer.parseInt(clientip[1])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(clientip[1]))) : (""+Integer.toHexString(Integer.parseInt(clientip[1]))) );
        
        String cip23=( (Integer.toHexString(Integer.parseInt(clientip[2])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(clientip[2]))) : (""+Integer.toHexString(Integer.parseInt(clientip[2]))) )  + ( (Integer.toHexString(Integer.parseInt(clientip[3])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(clientip[3]))) : (""+Integer.toHexString(Integer.parseInt(clientip[3]))) );
              
        syn.add(cip01);
        syn.add(cip23);
        
		synack.add(cip01);
        synack.add(cip23);
        
		acks2.add(cip01);
        acks2.add(cip23);
      
		acks1.add(cip01);
        acks1.add(cip23);
       
		pshack.add(cip01);
        pshack.add(cip23);
       
        finack1.add(cip01);
        finack1.add(cip23);
       
		finack2.add(cip01);
        finack2.add(cip23);
       
        acks3.add(cip01);
        acks3.add(cip23);
       		 
        String[] serverip = inputstringip.split("\\."); 
        
        String sip01=(  (Integer.toHexString(Integer.parseInt(serverip[0])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(serverip[0]))) : (""+Integer.toHexString(Integer.parseInt(serverip[0])))  ) + ( (Integer.toHexString(Integer.parseInt(serverip[1])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(serverip[1]))) : (""+Integer.toHexString(Integer.parseInt(serverip[1]))) );
        
		String sip23=( (Integer.toHexString(Integer.parseInt(serverip[2])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(serverip[2]))) : (""+Integer.toHexString(Integer.parseInt(serverip[2]))) )  + ( (Integer.toHexString(Integer.parseInt(serverip[3])).length() == 1)? ("0"+ Integer.toHexString(Integer.parseInt(serverip[3]))) : (""+Integer.toHexString(Integer.parseInt(serverip[3]))) );       

		syn.add(sip01);
        syn.add(sip23);
       
		synack.add(sip01);
        synack.add(sip23);
       
		acks2.add(sip01);
        acks2.add(sip23);
       
		acks1.add(sip01);
        acks1.add(sip23);
       
		pshack.add(sip01);
        pshack.add(sip23);
       
        finack1.add(sip01);
        finack1.add(sip23);
       
		finack2.add(sip01);
        finack2.add(sip23);
       
        acks3.add(sip01);
        acks3.add(sip23);
		
        
        String zeroc="";
        String cport = Integer.toHexString(inputclientport);
        if(cport.length()<4)
        {
        	int z= 4-cport.length();
        	for(int e=0;e<z;e++)
        	{ 
        		zeroc = zeroc + "0";
        	}
        	cport=zeroc+cport;
        	
        }
        syn.add(cport);
       
		synack.add(cport);
       
		acks2.add(cport);
       
		acks1.add(cport);
       
		pshack.add(cport);
       
        finack1.add(cport);
       
		finack2.add(cport);
       
        acks3.add(cport);
               
        String zeros="";
        String sport = Integer.toHexString(inputport);
        if(sport.length()<4)
        {
        	int z1= 4-sport.length();
        	for(int e=0;e<z1;e++)
        	{ 
        		zeros = zeros + "0";
        	}
        	sport=zeros+sport;
        	
        }
        syn.add(sport);
       	synack.add(sport);
      
		acks2.add(sport);
       
		acks1.add(sport);
      
        acks3.add(sport);
       
		pshack.add(sport);
     
        finack1.add(sport);
      
		finack2.add(sport);
        
        syn.add("0006");
		synack.add("0006");
  		acks2.add("0006");
        acks3.add("0006");
		acks1.add("0006");
		pshack.add("0006");
       
        finack1.add("0006");
       
		finack2.add("0006");
       
       String hexsegmentlength=Integer.toHexString(segmentlength);
        String zeroseg="";
		 if(hexsegmentlength.length()<4)
        {
        	int z2= 4-hexsegmentlength.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zeroseg= zeroseg + "0";
        	}
        	hexsegmentlength=zeroseg+hexsegmentlength;
        	
        }
        syn.add("0014");
		synack.add("0014");
		acks2.add("0014");
		acks1.add("0014");
        acks3.add("0014");
		pshack.add(hexsegmentlength);
        finack1.add("0014");
		finack2.add("0014");
     
		hexwindowsize=Integer.toHexString(Integer.parseInt(windowsize));
		String zerowin="";
		 if(hexwindowsize.length()<4)
        {
        	int z2= 4-hexwindowsize.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zerowin= zerowin + "0";
        	}
        	hexwindowsize=zerowin+hexwindowsize;
        	
        }
		syn.add(hexwindowsize);
		synack.add(hexwindowsize);
		acks2.add(hexwindowsize);
		acks1.add(hexwindowsize);
        acks3.add(hexwindowsize);
		pshack.add(hexwindowsize);
        finack1.add(hexwindowsize);
		finack2.add(hexwindowsize);
		
		
		hexinputstringclientisn = Integer.toHexString(Integer.parseInt(inputstringclientisn));
		String zerocisn="";
		if(hexinputstringclientisn.length()<8)
        {
        	int z2= 8-hexinputstringclientisn.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zerocisn= "0"+zerocisn;
        	}
        	hexinputstringclientisn=zerocisn+hexinputstringclientisn;
        	
        }
		String clientseqnum1 = new StringBuilder().append(hexinputstringclientisn.charAt(0)).append(hexinputstringclientisn.charAt(1)).append(hexinputstringclientisn.charAt(2)).append(hexinputstringclientisn.charAt(3)).toString();
		String clientseqnum2 = new StringBuilder().append(hexinputstringclientisn.charAt(4)).append(hexinputstringclientisn.charAt(5)).append(hexinputstringclientisn.charAt(6)).append(hexinputstringclientisn.charAt(7)).toString();
		syn.add(clientseqnum1);
		syn.add(clientseqnum2);
		syn.add("5002");
		
		acks1seqnum=1+Integer.parseInt(inputstringclientisn);
		hexacks1seqnum = Integer.toHexString(acks1seqnum);
		String zerocisnacks="";
		if(hexacks1seqnum.length()<8)
        {
        	int z2= 8-hexacks1seqnum.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zerocisnacks= "0"+zerocisnacks;
        	}
        	hexacks1seqnum=zerocisnacks+hexacks1seqnum;
        	
        }
		String acksseqnum1 = new StringBuilder().append(hexacks1seqnum.charAt(0)).append(hexacks1seqnum.charAt(1)).append(hexacks1seqnum.charAt(2)).append(hexacks1seqnum.charAt(3)).toString();
		String acksseqnum2 = new StringBuilder().append(hexacks1seqnum.charAt(4)).append(hexacks1seqnum.charAt(5)).append(hexacks1seqnum.charAt(6)).append(hexacks1seqnum.charAt(7)).toString();
		acks1.add(acksseqnum1);
		acks1.add(acksseqnum2);
		acks1.add("5010");
		pshack.add(acksseqnum1);
		pshack.add(acksseqnum2);
        
		int acks1acknowledgementnum=1+Integer.parseInt(inputstringisn);
		String hexacks1acknowledgementnum = Integer.toHexString(acks1acknowledgementnum);
		String zeroacks1acknum="";
		if(hexacks1acknowledgementnum.length()<8)
        {
        	int z2= 8-hexacks1acknowledgementnum.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zeroacks1acknum= "0"+zeroacks1acknum;
        	}
        	hexacks1acknowledgementnum=zeroacks1acknum+hexacks1acknowledgementnum;
        	
        }
		String acks1acknum1 = new StringBuilder().append(hexacks1acknowledgementnum.charAt(0)).append(hexacks1acknowledgementnum.charAt(1)).append(hexacks1acknowledgementnum.charAt(2)).append(hexacks1acknowledgementnum.charAt(3)).toString();
		String acks1acknum2 = new StringBuilder().append(hexacks1acknowledgementnum.charAt(4)).append(hexacks1acknowledgementnum.charAt(5)).append(hexacks1acknowledgementnum.charAt(6)).append(hexacks1acknowledgementnum.charAt(7)).toString();
		acks1.add(acks1acknum1);
		acks1.add(acks1acknum2);
		pshack.add(acks1acknum1);
		pshack.add(acks1acknum2);
		
		acks2.add(acks1acknum1);
		acks2.add(acks1acknum2);
		finack1.add(acks1acknum1);
		finack1.add(acks1acknum2);
		
		finack2.add(acks1acknum1);
		finack2.add(acks1acknum2);
		
		int acks3ack=acks1acknowledgementnum+1;
        String hexacks3acknowledgementnum = Integer.toHexString(acks3ack);
        String zeroacks3acknum="";
		if(hexacks3acknowledgementnum.length()<8)
        {
        	int z2= 8-hexacks3acknowledgementnum.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zeroacks3acknum= "0"+zeroacks3acknum;
        	}
        	hexacks3acknowledgementnum=zeroacks3acknum+hexacks3acknowledgementnum;
        	
        }
        String acks3acknum1 = new StringBuilder().append(hexacks3acknowledgementnum.charAt(0)).append(hexacks3acknowledgementnum.charAt(1)).append(hexacks3acknowledgementnum.charAt(2)).append(hexacks3acknowledgementnum.charAt(3)).toString();
		String acks3acknum2 = new StringBuilder().append(hexacks3acknowledgementnum.charAt(4)).append(hexacks3acknowledgementnum.charAt(5)).append(hexacks3acknowledgementnum.charAt(6)).append(hexacks3acknowledgementnum.charAt(7)).toString();
        acks3.add(acks3acknum1);
        acks3.add(acks3acknum2);
        acks3.add("5010");
          	
		String hexinputstringisn = Integer.toHexString(Integer.parseInt(inputstringisn));
		String zerosisn="";
		if(hexinputstringisn.length()<8)
        {
        	int z2= 8-hexinputstringisn.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zerosisn= "0"+zerosisn;
        	}
        	hexinputstringisn=zerosisn+hexinputstringisn;
          }
		String serverseqnum1 = new StringBuilder().append(hexinputstringisn.charAt(0)).append(hexinputstringisn.charAt(1)).append(hexinputstringisn.charAt(2)).append(hexinputstringisn.charAt(3)).toString();
		String serverseqnum2 = new StringBuilder().append(hexinputstringisn.charAt(4)).append(hexinputstringisn.charAt(5)).append(hexinputstringisn.charAt(6)).append(hexinputstringisn.charAt(7)).toString();

		synack.add(serverseqnum1);
		synack.add(serverseqnum2);
		
		int acknowledgementnumbersynack = Integer.parseInt(inputstringclientisn)+1;
		String hexacknowledgementnumbersynack=Integer.toHexString(acknowledgementnumbersynack);
		String zerosynack="";
		if(hexacknowledgementnumbersynack.length()<8)
        {
        	int z2= 8-hexacknowledgementnumbersynack.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zerosynack= "0"+zerosynack;
        	}
        	hexacknowledgementnumbersynack=zerosynack+hexacknowledgementnumbersynack;
        	
        }		
		String synackacknowledgenum1 = new StringBuilder().append(hexacknowledgementnumbersynack.charAt(0)).append(hexacknowledgementnumbersynack.charAt(1)).append(hexacknowledgementnumbersynack.charAt(2)).append(hexacknowledgementnumbersynack.charAt(3)).toString();
		String synackacknowledgenum2 = new StringBuilder().append(hexacknowledgementnumbersynack.charAt(4)).append(hexacknowledgementnumbersynack.charAt(5)).append(hexacknowledgementnumbersynack.charAt(6)).append(hexacknowledgementnumbersynack.charAt(7)).toString();
		synack.add(synackacknowledgenum1);
		synack.add(synackacknowledgenum2);
		
		synack.add("5012");
		
		pshack.add("5018");
		
		finack1.add("5011");
		
        finack2.add("5011");
		
		int acks2acknowledgementnum=Integer.parseInt(inputstringclientisn)+1+datalength;
		String hexacknowledgementnumbersacks=Integer.toHexString(acks2acknowledgementnum);
		String zeroacks2ack="";
		if(hexacknowledgementnumbersacks.length()<8)
        {
        	int z2= 8-hexacknowledgementnumbersacks.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zeroacks2ack= "0"+zeroacks2ack;
        	}
        	hexacknowledgementnumbersacks=zeroacks2ack+hexacknowledgementnumbersacks;
     
        }
	String acks2ackacknowledgenum1 = new StringBuilder().append(hexacknowledgementnumbersacks.charAt(0)).append(hexacknowledgementnumbersacks.charAt(1)).append(hexacknowledgementnumbersacks.charAt(2)).append(hexacknowledgementnumbersacks.charAt(3)).toString();
	String acks2ackacknowledgenum2 = new StringBuilder().append(hexacknowledgementnumbersacks.charAt(4)).append(hexacknowledgementnumbersacks.charAt(5)).append(hexacknowledgementnumbersacks.charAt(6)).append(hexacknowledgementnumbersacks.charAt(7)).toString();
		acks2.add(acks2ackacknowledgenum1);
		acks2.add(acks2ackacknowledgenum2);
		acks2.add("5010");
		finack1.add(acks2ackacknowledgenum1);
		finack1.add(acks2ackacknowledgenum2);
				
		int finack2ack=acks2acknowledgementnum+1;
        String hexacknowledgementnumberfinack2=Integer.toHexString(finack2ack);
		String zerofin2ack="";
		if(hexacknowledgementnumberfinack2.length()<8)
        {
        	int z2= 8-hexacknowledgementnumberfinack2.length();
        	for(int e=0;e<z2;e++)
        	{ 
        		zerofin2ack= "0"+zerofin2ack;
        	}
        	hexacknowledgementnumberfinack2=zerofin2ack+hexacknowledgementnumberfinack2;
     
        }
    String finack2ackacknowledgenum1 = new StringBuilder().append(hexacknowledgementnumberfinack2.charAt(0)).append(hexacknowledgementnumberfinack2.charAt(1)).append(hexacknowledgementnumberfinack2.charAt(2)).append(hexacknowledgementnumberfinack2.charAt(3)).toString();
	String finack2ackacknowledgenum2 = new StringBuilder().append(hexacknowledgementnumberfinack2.charAt(4)).append(hexacknowledgementnumberfinack2.charAt(5)).append(hexacknowledgementnumberfinack2.charAt(6)).append(hexacknowledgementnumberfinack2.charAt(7)).toString();
        finack2.add(finack2ackacknowledgenum1);
        finack2.add(finack2ackacknowledgenum2);
        acks3.add(finack2ackacknowledgenum1);
        acks3.add(finack2ackacknowledgenum2);  

		 if (inputstringmessage.length() % 2 == 0)
        {
			for (int y = 0; y < inputstringmessage.length()-1; y = y + 2)
			{
				p1 = (int) (inputstringmessage.charAt(y));
				messagehex1 = Integer.toHexString(p1);
				p2 = (int) (inputstringmessage.charAt(y + 1));
				
				messagehex2 = messagehex1 + Integer.toHexString(p2);
				if(y == (inputstringmessage.length()-2)){
					String abc = "0"+Integer.toHexString(p2);
					messagehex2 = messagehex1 + abc;			
				}	
			   pshack.add(messagehex2);
				
			}
        }
        else
        {
           for (int y = 0; y < inputstringmessage.length()-2; y = y + 2)
			{
				p1 = (int) (inputstringmessage.charAt(y));
				messagehex1 = Integer.toHexString(p1);
				p2 = (int) (inputstringmessage.charAt(y + 1));
				messagehex2 = messagehex1 + Integer.toHexString(p2);
			   pshack.add(messagehex2);
			}
			p3 = (int) (inputstringmessage.charAt(inputstringmessage.length()-1));
			messagehex3="0a00";
			 pshack.add(messagehex3);
		   
        }

	/*System.out.println("syn list "+syn);
System.out.println("syn-ack list "+synack);
System.out.println("acks1 list "+acks1);
System.out.println("pshack list "+pshack);
System.out.println("acks2 list "+acks2);
System.out.println("finack1 list "+finack1);
System.out.println("finack2 list "+finack2);
System.out.println("acks3 list "+acks3);
*/

		int finalx=generateChecksum(syn);
		System.out.println("The checksum for clients's syn generated is = " + Integer.toHexString(finalx));

		int finalx1=generateChecksum(synack);
		System.out.println("The checksum for server's synack generated is = " + Integer.toHexString(finalx1));
		
		int finalx2=generateChecksum(acks1);
		System.out.println("The checksum for clients's ack generated is = " + Integer.toHexString(finalx2));
        
        int finalx3=generateChecksum(pshack);
		System.out.println("The checksum for clients's psh-ack generated is = " + Integer.toHexString(finalx3));
        
        int finalx4=generateChecksum(acks2);
		System.out.println("The checksum for server's ack generated is = " + Integer.toHexString(finalx4));
        
        int finalx5=generateChecksum(finack1);
		System.out.println("The checksum for clients's fin-ack generated is = " + Integer.toHexString(finalx5));
		
		int finalx6=generateChecksum(finack2);
		System.out.println("The checksum for server's fin-ack generated is = " + Integer.toHexString(finalx6));
        
        int finalx7=generateChecksum(acks3);
		System.out.println("The checksum for clients's ack generated is = " + Integer.toHexString(finalx7));
		
        try
        {
            String host = inputstringip;
            int port = inputport;
           // InetAddress address = InetAddress.getByName(host);
           socket.bind(new InetSocketAddress(inputstringclientip,inputclientport));
            socket.connect(new InetSocketAddress(host, port));
 
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            String sendMessage = inputstringmessage + "\n";
            bw.write(sendMessage);
            bw.flush();
            System.out.println("Message sent to the server : "+sendMessage);
			
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            System.out.println("Message received from the server : " +message);
        }
        catch (Exception exception)
        {
            System.out.print("Whoops! It didn't connect!\n");
        }
    }
	
	static int generateChecksum(ArrayList<String> inputlist)
    {
    	
        String hex_value = new String();
        int x,i, checksum = 0;
        for (i = 0; i < inputlist.size(); i = i + 1)
        {
            x = Integer.parseInt(inputlist.get(i),16);
			checksum += x;
			hex_value = Integer.toHexString(checksum);
			if (hex_value.length() > 4)
			{
				int carry = Integer.parseInt((""+ hex_value.charAt(0)), 16);
				hex_value = hex_value.substring(1, 5);
				checksum = Integer.parseInt(hex_value, 16);
				checksum += carry;
			}
        }
		//System.out.println("before compliment "+Integer.toHexString(checksum));
        checksum = generateComplement(checksum);
       return checksum;
    }
    static int generateComplement(int checksum)
    {
        checksum = Integer.parseInt("FFFF", 16) - checksum;
        return checksum;
    }
}
