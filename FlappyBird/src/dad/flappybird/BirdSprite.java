package dad.flappybird;

import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.flappybird.resources.Sounds;
import dad.games.ImageSprite;
import dad.games.Scene;
import dad.games.Sound;

public class BirdSprite extends ImageSprite {
	
	private static final float SPEED = 450.0f; // velocidad en píxeles/segundo (px/s)

	private Image upImage, stillImage, downImage; 
	private Sound sound;
	
	public BirdSprite(Scene screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		upImage = Images.load("bird0_2.png");
		stillImage = Images.load("bird0_1.png");
		downImage = Images.load("bird0_0.png");
		sound = Sounds.load("fly.aiff");
		still();
		setLocation(getScene().getWidth() / 4, getScene().getHeight() / 3);			
	}

	@Override
	public void update(long timeDiff) {
		float offset = 0f;
		if (getScene().getInput().isUp()) {
			up(timeDiff);			
		} else if (getScene().getInput().isDown()) {
			down(timeDiff);
		} else {
			still();
		}
		move(0, offset);
	}

	private void still() {
		setImage(stillImage);
	}
	
	private void up(long timeDiff) {
		float offset = -SPEED * (timeDiff / 1000.0f);
		if (getImage() == upImage)
			still();
		else if (getImage() == downImage)
			setImage(upImage);
		else if (getImage() == stillImage)
			setImage(downImage);
		sound.playIfCompleted();
		move(0, offset);
	}

	private void down(long timeDiff) {
		float offset = SPEED * (timeDiff / 1000.0f);
		setImage(downImage);
		sound.playIfCompleted();
		move(0, offset);		
	}
}
