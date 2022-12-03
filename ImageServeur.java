package socket;

import java.net.*;  
import java.io.*;
class ImageServeur{  
    public static void main(String args[])throws Exception{  
        try {


            ServerSocket ss=new ServerSocket(3333);
            System.out.println("En attente de connnexion d'un client");
            Socket socket=ss.accept();
            System.out.println("Connexion etablie");


            File file = new File("D:/S3/Mr Naina/streaming/pictures/fraise.jpg");
               
            DataInputStream dip=new DataInputStream(new FileInputStream(file));
            
            DataOutputStream dop=new DataOutputStream(socket.getOutputStream());

            // System.out.println(file.);

            // System.out.println(dip.readByte());

            byte[] bytes = new byte[1024];

            int n;

            while((n=dip.read(bytes)) != -1) {
                dop.write(bytes, 0, n);
                // System.out.println(n);
            }

            System.out.println("envoie avec succes");

            dop.close();
            dip.close();
            socket.close();
        
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        

        

    }
}  