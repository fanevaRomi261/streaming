package pkgClient;

import javazoom.jl.player.advanced.AdvancedPlayer;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JList;
import affichage.*;
import java.io.*;  
import java.net.*;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.nio.file.*;
import java.awt.event.*;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class Client {
    
    public static void main(String[] args) {
        
        try {
        
            Socket socket=new Socket("127.0.0.1",3333);  

            ObjectInputStream test=new ObjectInputStream(socket.getInputStream());

            int size=test.readInt();
            String[] fichier=new String[size];
            
            for (int i = 0; i < size; i++) {
                fichier[i]=(String)test.readObject();
            }

            ClientFrame frame=new ClientFrame(fichier);

            while(frame.getChoix() == -1 ){
                System.out.println("en attente de votre choix");
            }

            System.out.println("choix faite");
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());
            out.writeUTF(fichier[frame.getChoix()]);

            if(fichier[frame.getChoix()].contains(".mp3") == true){

                int nb=0;
                int len=1024;

                byte[] bytes=new byte[len];
                while(true){
                    
                    DataInputStream dis=new DataInputStream(socket.getInputStream());
                    AdvancedPlayer a=new AdvancedPlayer(dis);

                    if(dis.available() != 0){
                        System.out.println("playing");
                        a.play();
                    } else {
                        a.stop();
                        break;
                    }                
                }

            }
            else if(fichier[frame.getChoix()].contains(".jpg") == true){

                Vector<Byte> img=new Vector<>();

                while(true){
                    
                    DataInputStream dis=new DataInputStream(socket.getInputStream());

                    byte b=dis.readByte(); 

                    if(dis.available() !=0 ){
                        img.add(b);
                    } else {
                        break;
                    }
                }

                Object[] image=img.toArray();
                byte[] pic=new byte[image.length];
                for (int i = 0; i < pic.length; i++) {
                    pic[i]=(byte)image[i];
                }

                // System.out.println(pic.length);

                ByteArrayInputStream bis = new ByteArrayInputStream(pic);
                BufferedImage b=ImageIO.read(bis);
                
                JFrame f=new JFrame();
                
                JLabel l=new JLabel();
                Image dimg = b.getScaledInstance(500, 500,Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(dimg);

                l.setIcon(icon);

                f.add(l);

                f.setSize(500,500);
                f.setVisible(true);

            }
            else if(fichier[frame.getChoix()].contains(".mp4") == true){
                int nb=0;
                int len=1024*500;


                byte[] bytes=new byte[len];
                

                ThreadVideo t=new ThreadVideo();
                
                
                File temp=File.createTempFile("video", ".mp4", new File("./temp"));
            
                t.start();


                while(true){
                    
                    DataInputStream dis=new DataInputStream(socket.getInputStream());

                    byte b=dis.readByte();

                    if(nb!=len-1){
                        bytes[nb]=b;
                        nb+=1;
                    } else {
                        nb=0;
                        FileOutputStream fost= new FileOutputStream(temp,true);
                        fost.write(bytes);
                    }                    
                }
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
