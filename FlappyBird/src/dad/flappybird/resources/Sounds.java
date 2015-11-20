package dad.flappybird.resources;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import dad.games.Sound;

public class Sounds {
	
	private static Map<String, byte[]> cache = new HashMap<String, byte[]>();

	public static Sound load(String name) {
		byte [] audio = cache.get(name);
		if (audio == null) {
			URL url = Sounds.class.getResource(name);
			try {
				audio = Files.readAllBytes(Paths.get(url.toURI()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			cache.put(name, audio);
		}
		return new Sound(audio);
	}
	
}
