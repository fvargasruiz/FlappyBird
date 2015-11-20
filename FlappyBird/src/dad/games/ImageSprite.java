package dad.games;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class ImageSprite extends Sprite {

	private Image image;
	
	public ImageSprite(Scene scene) {
		super(scene);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
		adjustToImage();
	}
	
	public void adjustToImage() {
		setSize(getImage().getWidth(null), getImage().getHeight(null));
	}

	@Override
	public void paint(Graphics2D g) {
		if (isVisible()) {
			g.setClip(getBounds());
			g.drawImage(image, getX(), getY(), null);
			g.setClip(null);
		}
	}

}
