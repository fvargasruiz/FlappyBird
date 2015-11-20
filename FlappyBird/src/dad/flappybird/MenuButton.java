package dad.flappybird;

import dad.flappybird.resources.Images;
import dad.games.Button;
import dad.games.Scene;

public class MenuButton extends Button {
	
	public MenuButton(Scene scene) {
		super(scene);
		init();
	}

	@Override
	public void init() {		
		setNormalImage(Images.load("button_menu.png"));
		setClickedImage(Images.load("button_menu.png"));
		setOverImage(Images.load("button_menu.png"));
		setLocation(getScene().getWidth() - getWidth() - 5, getScene().getHeight() - getHeight() - 5);
	}

	@Override
	protected void click() {
		getScene().getGame().changeScene("MenuScene");
	}
	
}
