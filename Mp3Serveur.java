package socket;

import java.net.*;  
import java.io.*;
class Mp3Serveur{  
    public static void main(String args[])throws Exception{  
        try {


            ServerSocket ss=new ServerSocket(3333);
            System.out.println("En attente de connnexion d'un client");
            Socket socket=ss.accept();
            System.out.println("Connexion etablie");

            ObjectOutputStream hira=new ObjectOutputStream(socket.getOutputStream());
            File dir=new File("D:/S3/Mr Naina/streaming/songs");
            File[] rehetra=dir.listFiles();
            hira.writeInt(rehetra.length);
            // hira.writeObject(rehetra.length);
            for (int j = 0; j < rehetra.length; j++) {
                hira.writeObject("D:/S3/Mr Naina/streaming/songs/"+rehetra[j].getName());
            }
            
            // while(true){

                DataInputStream test=new DataInputStream(socket.getInputStream());
                String chanson=test.readUTF();

                File file = new File(chanson);
                
                // while(true){
    
                DataInputStream dip=new DataInputStream(new FileInputStream(file));
                
                DataOutputStream dop=new DataOutputStream(socket.getOutputStream());

                byte[] bytes = new byte[1024];



                int n;

                while((n=dip.read(bytes)) != -1) {
                    dop.write(bytes, 0, n);
                    // System.out.println(n);
                }

                // dop.write(21323);
                System.out.println("envoie avec succes");

                dop.close();
                dip.close();

            // }


            // }

                
           
            socket.close();
        
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        

        

    }
}  