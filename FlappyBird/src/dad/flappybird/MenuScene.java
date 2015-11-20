package dad.flappybird;

import java.awt.Graphics2D;

import dad.games.Game;
import dad.games.Scene;

public class MenuScene extends Scene {

	private PlayButton playButton;
	private BackgroundSprite background;
	private TitleSprite title;

	public MenuScene(Game game) {
		super(game, "MenuScene");
	}

	@Override
	protected void init() {
		background = new BackgroundSprite(this, "bg_night.png");
		title = new TitleSprite(this);
		playButton = new PlayButton(this);
		
		background.init();
		title.init();
		playButton.init();
	}

	@Override
	protected void update(long timeDiff) {
		if (getInput().isShoot()) {
			letsPlay();
		}
        background.update(timeDiff);
		title.update(timeDiff);
		playButton.update(timeDiff);
	}

	@Override
	protected void render(Graphics2D g) {
		background.paint(g);
		title.paint(g);
		playButton.paint(g);
	}
	
	protected void letsPlay() {
		getGame().changeScene("PlayScene");
	}
	
}
