package socket;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.filechooser.FileSystemView;

import java.awt.image.BufferedImage;

import java.io.*;  
class ImageClient{  
    public static void main(String args[])throws Exception{  

        try{
            Socket socket=new Socket("127.0.0.1",3333);  
   
        
            int nb=0;
            int len=1024;

            // byte[] bytes=new byte[len];
            Vector<Byte> img=new Vector<>();
            while(true){
                
                DataInputStream dis=new DataInputStream(socket.getInputStream());

                byte b=dis.readByte();
                
                // bytes[nb]=b;
                
                
                if(dis.available() ==0 ){
                    break;
                } else {
                    img.add(b);
                }
             

                // ByteArrayInputStream inp=new ByteArrayInputStream(bytes);
                // DataInputStream in=new DataInputStream(dis);
                // AdvancedPlayer a=new AdvancedPlayer(in);
            }
            Object[] image=img.toArray();
            byte[] pic=new byte[image.length];
            for (int i = 0; i < pic.length; i++) {
                pic[i]=(byte)image[i];
            }

            ByteArrayInputStream bis = new ByteArrayInputStream(pic);
            BufferedImage b=ImageIO.read(bis);
            // BufferedImage finale=new BufferedImage(400,400,b.getType());
            ImageIcon icon=new ImageIcon(b);

            JFrame f=new JFrame();

            JLabel l=new JLabel();

            l.setIcon(icon);

            f.add(l);

            f.setSize(500,500);
            f.setVisible(true);
            

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

