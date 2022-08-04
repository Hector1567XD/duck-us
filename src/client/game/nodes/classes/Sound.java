package client.game.nodes.classes;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
   Clip clip;
   URL soundURL[] = new URL[30]; 
   
   public Sound (){
       soundURL[0] = getClass().getResource("/client/resources/game/sounds/Pisada 1.wav");
       soundURL[1] = getClass().getResource("/client/resources/game/sounds/Pisada 2.wav");
       soundURL[2] = getClass().getResource("/client/resources/game/sounds/Pisada 3.wav");
       soundURL[4] = getClass().getResource("/client/resources/game/sounds/Pato impostor.wav");
   }
   
   public void setFile(int i) {
       try{
         AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
         clip = AudioSystem.getClip();
         clip.open(ais);
       }catch(Exception e) {
           
       }
           
   }
   
   public void play(){
       clip.start();
   }
   
   public void loop() {
       clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
   
   public void stop() {
       clip.stop();
       
   }
}
