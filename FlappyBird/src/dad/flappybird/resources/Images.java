package dad.flappybird.resources;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Images {
	
	private static Map<String, Image> cache = new HashMap<String, Image>();

	public static Image load(String name) {
		Image image = cache.get(name);
		if (image == null) {
			image = new ImageIcon(Images.class.getResource(name)).getImage();
			cache.put(name, image);
		}
		return image;
	}
	
}
