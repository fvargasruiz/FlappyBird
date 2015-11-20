package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.ImageSprite;
import dad.games.Scene;

public class PipeDownSprite extends ImageSprite {

	public PipeDownSprite(Scene screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("pipe_down.png"));
		setLocation(getScene().getWidth(), -200);
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
