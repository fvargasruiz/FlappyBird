package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.ImageSprite;
import dad.games.Scene;

public class PipeUpSprite extends ImageSprite {

	public PipeUpSprite(Scene screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("pipe_up.png"));
		setLocation(getScene().getWidth(), getScene().getHeight() / 2);
	}

	@Override
	public void update(long timeDiff) {
		float offset = - 250.0f * (timeDiff / 1000.0f);
		setRealX(getRealX() + offset);
		if (getX() + getWidth() < 0) {
			setX(getScene().getWidth());
		}
	}

}
