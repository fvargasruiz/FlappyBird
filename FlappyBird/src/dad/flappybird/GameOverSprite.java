package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.ImageSprite;
import dad.games.Scene;

public class GameOverSprite extends ImageSprite {

	public GameOverSprite(Scene screen) {
		super(screen);
		init();
	}

	@Override
	public void init() {
		setImage(Images.load("text_game_over.png"));
		centerOnScene();
		setVisible(false);
	}

	@Override
	public void update(long timeDiff) {
		// no hace nada
	}
	

}
