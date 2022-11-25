package socket;

import java.net.*;  
import java.io.*;
class Serveur{  
    public static void main(String args[])throws Exception{  
        try {
            ServerSocket ss=new ServerSocket(3333);
            System.out.println("En attente de connnexion d'un client");
            Socket socket=ss.accept();
            System.out.println("Connexion etablie");

            File file = new File("D:/S3/Mr Naina/streaming/songs/The Chainsmokers & Coldplay - Something Just Like This.mp3");
            
            int ct=0;
            int len=(int)file.length();

            DataInputStream dip=new DataInputStream(new FileInputStream(file));
            
            DataOutputStream dop=new DataOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[1024];

            // System.out.println(bytes.length);

            // System.out.println(len/1024);
            
            // while(array < len){

                int n;
                while((n=dip.read(bytes)) != -1) {
                    dop.write(bytes, 0, n);
                    //dop.flush();
                }

            

              
            //     array+=1024;
            //     // System.out.println(array+" "+len);
                

                
         dop.close();
         dip.close();
         socket.close();
            // }
            // int len=(int)file.length();
            // byte[] bytes = new byte[1024];

            

            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        

    }
}  