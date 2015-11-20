package dad.games;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements LineListener, Runnable {

    private boolean playCompleted = true;
    private byte [] audio;
	private Thread thread;
    
    public Sound(URL url) throws IOException, URISyntaxException {
		audio = Files.readAllBytes(Paths.get(url.toURI()));
    }
    
    public Sound(byte [] audio) {
		this.audio = audio;
    }
    
    public void playIfCompleted() {
    	if (isPlayCompleted()) play();
    }
     
    public void play() {
    	thread = new Thread(this);
    	thread.start();
    }
     
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.STOP) {
            playCompleted = true;
        }
    }
    
    public synchronized boolean isPlayCompleted() {
		return playCompleted;
	}

	@Override
	public void run() {
        try {
        	playCompleted = false;
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audio));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();
            while (!isPlayCompleted());
            audioClip.close();
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
