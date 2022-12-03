package socket;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import affichage.*;

import java.io.*;  
class Mp3Client{  
    public static void main(String args[])throws Exception{  

        try{
            Socket socket=new Socket("127.0.0.1",3333);  

            ObjectInputStream test=new ObjectInputStream(socket.getInputStream());

            int size=test.readInt();
            String[] hira=new String[size];
            String[] nomHira=new String[size];
            
            for (int i = 0; i < size; i++) {
                hira[i]=(String)test.readObject();
                String []splt=hira[i].split("/");
                nomHira[i]=splt[5];
            }

            ClientFrame frame=new ClientFrame(nomHira);

            while(frame.getChoix() == -1 ){
                System.out.println("en attente de votre choix");
            }

            System.out.println("choix faite");
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            out.writeUTF(hira[frame.getChoix()]);


            
            
        
            int nb=0;
            int len=1024;

            byte[] bytes=new byte[len];
            while(true){
                
                DataInputStream dis=new DataInputStream(socket.getInputStream());

                byte b=dis.readByte();
                
                bytes[nb]=b;
                
                if(nb==len-1){
                    nb=0;
                } else {
                    nb+=1;
                }

                // ByteArrayInputStream inp=new ByteArrayInputStream(bytes);
                // DataInputStream in=new DataInputStream(inp);
                AdvancedPlayer a=new AdvancedPlayer(dis);

                // Thread.sleep(1000);
                if(dis.available() != 0){
                    System.out.println("playing");
                    a.play();
                } else {
                    // System.out.println("tapitra");
                    a.stop();
                    break;
                }                
            }

        } catch(Exception e){
            System.out.println("fin de la chanson");
            // e.printStackTrace();
        }
    }
}

