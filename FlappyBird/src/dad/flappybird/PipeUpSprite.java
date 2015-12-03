package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.Scene;

public class PipeUpSprite extends PipeSprite {

	public PipeUpSprite(Scene screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("pipe_up.png"));
		setLocation(getScene().getWidth(), getScene().getHeight() / 2);
	}
	
}
