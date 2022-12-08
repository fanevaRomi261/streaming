package affichage;

import javax.swing.JFrame;
import java.awt.event.*;
import java.io.File;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public class ThreadVideo extends Thread{

    
    @Override
    public void run() {
        

        JFrame fr = new JFrame("My First Media Player");
        fr.setBounds(100, 100, 600, 400);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        fr.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });

        File dir=new File("./temp");


        // try {Thread.sleep(700);} catch (Exception e1) {}

        if(dir.listFiles()[0].exists()==true){
            File temp=dir.listFiles()[0];

            while(true){
                if(temp.length() != 0){
                    System.out.println(temp.length());
                    break;
                }
            }
            
            fr.add(mediaPlayerComponent);
            fr.setVisible(true);

            mediaPlayerComponent.mediaPlayer().media().play("./temp/"+temp.getName());

            temp.deleteOnExit(); 
        }



    }
}