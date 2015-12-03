package dad.flappybird;

import java.awt.Graphics2D;
import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.games.Scene;
import dad.games.Sprite;

public class BackgroundSprite extends Sprite {

	public static final float SPEED = -150.0f;

	private float speed;
	private Image image;
	private String imageName;

	private float imageX;
	private int imageWidth;

	public BackgroundSprite(Scene screen, String imageName) {
		super(screen);
		this.imageName = imageName;
		init();
	}

	@Override
	public void init() {
		setLocation(0, 0);
		setSize(getScene().getWidth(), getScene().getHeight());
		image = Images.load(imageName);
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
