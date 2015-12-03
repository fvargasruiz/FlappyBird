package dad.flappybird;

import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.flappybird.resources.Sounds;
import dad.games.ImageSprite;
import dad.games.Scene;
import dad.games.Sound;

public class JumpingBirdSprite extends ImageSprite {
	
	private static final float SPEED = 200.0f; 		// velocidad en píxeles/segundo (px/s)
	private static final float GRAVITY = 7.5f; 	// desaceleración vertical  

	private Image upImage, stillImage, downImage; 
	private Sound sound;
	private float speed;
	
	public JumpingBirdSprite(Scene screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		upImage = Images.load("bird0_2.png");
		stillImage = Images.load("bird0_1.png");
		downImage = Images.load("bird0_0.png");
		sound = Sounds.load("fly.aiff");
		setImage(stillImage);
		speed = 0f;
		setLocation(getScene().getWidth() / 4, getScene().getHeight() / 3);			
	}

	@Override
	public void update(long timeDiff) {
		float offset = 0f;
		if (getScene().getInput().isShoot() || getScene().getInput().getClickCount() > 0) {
			speed = -SPEED;
			setImage(upImage);
			sound.playIfCompleted();
		} else if (speed <= 0f) {
			setImage(stillImage);
		} else {
			setImage(downImage);
		}
		offset = speed * (timeDiff / 1000.0f);
		speed += GRAVITY;
		move(0, offset);
	}

}
