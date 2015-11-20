package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.ImageSprite;
import dad.games.Scene;

public class TitleSprite extends ImageSprite {

	public TitleSprite(Scene screen) {
		super(screen);
	}

	@Override
	public void init() {
		setImage(Images.load("title.png"));
		setLocation((getScene().getWidth() - getWidth()) / 2, (getScene().getHeight() - getHeight()) / 4);
	}

	@Override
	public void update(long timeDiff) {
		// no hace nada
	}

}
