package dad.flappybird;

import java.awt.Graphics2D;
import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.games.Scene;
import dad.games.Sprite;

public class LandSprite extends Sprite {
	
	public static final float SPEED = -250f; 

	private Image image;
	
	private float speed;
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
		speed = SPEED;
	}

	@Override
	public void update(long timeDiff) {
		float offset = speed * (timeDiff / 1000.0f);
		imageX += offset;
		if (speed < 0 && imageX <= -imageWidth) {
			imageX = 0;
		} else if (speed > 0 && imageX >= 0) {
			imageX = -imageWidth;
		}
	}

	@Override
	public void paint(Graphics2D g) {
		int x = Math.round(imageX);
		while (x < getWidth()) {
			g.drawImage(image, getX() + x, getY(), null);
			x += imageWidth;
		}
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
