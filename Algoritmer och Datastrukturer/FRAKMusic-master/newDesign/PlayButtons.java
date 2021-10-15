import javax.sound.sampled.*;
import java.io.*;
import java.util.*;
/**
 * <h1>Player.java</h1>
 * <p>
 * This class creates methods for the Player to play the playlist which also creates here
 * </p>
 * 
 * @author Andre Frisk, Fredrik Kortetjarvi, Kristoffer Guachalla, Rohullah
 *         Khorami
 * @version 1.0
 */
public class PlayButtons{
    Long currentFrame; 
    Clip clip; 
    String status; 
    ArrayQueue<PlayerItem> songlist = new ArrayQueue();  
    AudioInputStream audioInputStream; 
    static String filePath = "/home/fredrik/FRAKMusic/Music";
    static String path = "/home/fredrik/FRAKMusic/Music/";
    /**
     * 
     * @throws UnsupportedAudioFileException ??
     * @throws IOException ??
     * @throws LineUnavailableException ??
     */
PlayButtons()throws UnsupportedAudioFileException,IOException, LineUnavailableException
{  
    { 
        adding(new File(filePath));
        audioInputStream =  
                AudioSystem.getAudioInputStream(new File(path+songlist.dequeue().getFilename())); 
        
        // create clip reference 
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    } 
}   
/**
 * The method to start the song
 * @throws LineUnavailableException ??
 * @throws IOException ??
 */
public void play()throws LineUnavailableException,IOException{
    clip.start();
    status ="play";
}
/**
 * The method to pause the song
 */
public void pause(){
    if (status.equals("paused"))  
        { 
            System.out.println("audio is already paused"); 
            return; 
        } 
        this.currentFrame =  
        this.clip.getMicrosecondPosition(); 
        clip.stop(); 
        status = "paused"; 
}
/**
 * The method to stop the playlist from playing
 */
public void stop(){
    currentFrame = 0L; 
        clip.stop(); 
        clip.close(); 
}
/**
 * play the next song 
 * @throws UnsupportedAudioFileException ??
 * @throws IOException ??
 * @throws LineUnavailableException ??
 */
public void next()throws UnsupportedAudioFileException,IOException, LineUnavailableException{
    clip.stop();
    clip.close();
    PlayerItem remove = songlist.dequeue();
    audioInputStream =  
    AudioSystem.getAudioInputStream(new File(path+remove.getFilename()).getAbsoluteFile()); 
    clip.open(audioInputStream);
    clip.start();

}
/**
 * Queue a song to the queuelist
 * @param next; the song to ge queued
 */
public void add(PlayerItem next){
    songlist.enqueue(next);
}
/**
 * Creates a plsylist (adds all songs)
 * @param source; send in the link to the folder where the musicfiles are located
 */
public void adding(final File source) {
                final File[] listOfFiles = source.listFiles();

                for (File file : listOfFiles) {
                        if (file.isDirectory()) {
                                adding(file);
                        } else {
                                
                                PlayerItem musicItem = new PlayerItem(file);
                                songlist.enqueue(musicItem);
                                

                        }
                }
        }
/**
 * A method to search for a specific song and play it
 * @param song; the song to be searched
 * @throws UnsupportedAudioFileException ??
 * @throws IOException ??
 * @throws LineUnavailableException ??
 */
public void search(PlayerItem song)throws UnsupportedAudioFileException,IOException, LineUnavailableException {
	clip.stop();
    clip.close();
    audioInputStream = 
    AudioSystem.getAudioInputStream(new File(path+song.getFilename()).getAbsoluteFile()); 
    clip.open(audioInputStream);
    clip.start();
	}
}
