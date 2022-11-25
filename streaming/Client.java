package socket;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.net.*;
import java.util.Scanner;
import java.io.*;  
class Client{  
    public static void main(String args[])throws Exception{  
        
        Socket socket=new Socket("127.0.0.1",3333);  

        
        
        int nb=0;
        int i=0;

        int len=100000;
        byte[] bytes=new byte[len];

        DataInputStream dis=new DataInputStream(socket.getInputStream());
        while(true){
            
            byte b=dis.readByte();
            if(nb < len){    
                bytes[nb]=b;
                nb+=1;
            } else {
                ByteArrayInputStream inp=new ByteArrayInputStream(bytes);
                DataInputStream in=new DataInputStream(inp);
                AdvancedPlayer a=new AdvancedPlayer(in);
                a.play();
                nb=0;
            }       
            // nb=0;    
        }

            
            

        
                

    }
}

