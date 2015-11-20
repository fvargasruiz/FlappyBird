package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.Button;
import dad.games.Scene;

public class PlayButton extends Button {
	
	public PlayButton(Scene scene) {
		super(scene);
	}

	@Override
	public void init() {		
		setNormalImage(Images.load("button_play.png"));
		setClickedImage(Images.load("button_play_over.png"));
		setOverImage(Images.load("button_play_over.png"));
		setImage(getNormalImage());
		setLocation((getScene().getWidth() - getWidth()) / 2, (getScene().getHeight() - getHeight()) / 2);
	}

	@Override
	protected void click() {
		getScene().getGame().changeScene("PlayScene");
	}
	
}
