package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.Scene;

public class PipeDownSprite extends PipeSprite {

	public PipeDownSprite(Scene screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("pipe_down.png"));
		setLocation(getScene().getWidth(), -200);
	}

}
