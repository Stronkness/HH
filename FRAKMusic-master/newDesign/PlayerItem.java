import javax.sound.sampled.*;
import java.io.*;
import java.util.*;
/**
 * <h1>Player.java</h1>
 * <p>
 * This class creates a PlayerItem which contains the four properties needed. Also get methods are implemented.
 * </p>
 * 
 * @author Andre Frisk, Fredrik Kortetjarvi, Kristoffer Guachalla, Rohullah
 *         Khorami
 * @version 1.0
 */
public class PlayerItem {
	private String name;
	private String artist;
	private String filename;
	private String time;
	
	/**
	 * Creates the PlayerItem with the four properties name, artist, filename and time
	 * @param file; the .wav with the properties
	 */
	public PlayerItem(File file) {
		filename = file.getName();
		String[] getSongorAuthor = {"0","0"};
		getSongorAuthor = filename.split("[-.]");
		artist = getSongorAuthor[0];
		name = getSongorAuthor[1];
		time = getDuration(file);

	}
	/**
	 * These four methods returns the name, artist, filename or the time
	 * @return; the property 
	 */
	public String getName() {
		return name;
	}

	public String getArtist() {
		return artist;
	}

	public String getFilename() {
		return filename;
	}

	public String getTime() {
		return time;
	}
	/**
	 * Gives the duration of the song in the type String
	 * @param file; .wav file to get the duration on
	 * @return; ammount of time for the specific song
	 */
	private static String getDuration(File file){
		try{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
		AudioFormat format = audioInputStream.getFormat();
		long frames = audioInputStream.getFrameLength();
		double durationInSeconds = (frames+0.0) / format.getFrameRate(); 
		int durationInSeconds2 = (int)durationInSeconds;
		String sec = durationInSeconds2/60 +":"+(int)durationInSeconds%60;
		
		return sec; 
		}catch(UnsupportedAudioFileException e){
			 e.fillInStackTrace();
			 return null;
		}catch(IOException e){
			 e.fillInStackTrace();
			 return null;
		}
	
	}
	
	
}

