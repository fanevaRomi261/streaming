package pkgServeur;

import java.net.*;  
import java.io.*;

public class Serveur {
    public static void main(String args[])throws Exception{  
        try {


            ServerSocket ss=new ServerSocket(3333);
            System.out.println("En attente de connnexion d'un client");
            Socket socket=ss.accept();
            System.out.println("Connexion etablie");

            ObjectOutputStream fichier=new ObjectOutputStream(socket.getOutputStream());
            File dir=new File("./fichiers");

            File[] rehetra=dir.listFiles();

            fichier.writeInt(rehetra.length);

            for (int i = 0; i < rehetra.length; i++) {
                fichier.writeObject(rehetra[i].getName());
            }

            DataInputStream test=new DataInputStream(socket.getInputStream());
            String nom=test.readUTF();

            File file=new File("./fichiers/"+nom);
               
            DataInputStream dip=new DataInputStream(new FileInputStream(file));
            
            DataOutputStream dop=new DataOutputStream(socket.getOutputStream());


            byte[] bytes = new byte[1024];

            int n;

            while((n=dip.read(bytes)) != -1) {
                dop.write(bytes, 0, n);
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
