package dad.flappybird;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.games.Scene;
import dad.games.Sprite;

public class LandSprite extends Sprite {

	private Image image;
	
	private float imageX;
	private int imageWidth;
	
	public LandSprite(Scene screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		image = Images.load("land.png");
		setSize(getScene().getWidth() - 1, image.getHeight(null) - 1);
		setLocation(0, getScene().getHeight() - getHeight() - 1);
		imageX = 0f;
		imageWidth = image.getWidth(null);
	}

	@Override
	public void update(long timeDiff) {
		float offset = - 250.0f * (timeDiff / 1000.0f);
		imageX += offset;
		if (imageX <= -imageWidth) {
			imageX = 0;
		}
	}

	@Override
	public void paint(Graphics2D g) {
		int x = Math.round(imageX);
		while (x < getWidth()) {
			g.drawImage(image, getX() + x, getY(), null);
			x += imageWidth;
		}
		g.setColor(Color.RED);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
	}

}
