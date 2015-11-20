package dad.games;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Scene extends BackgroundTask {

	private Game game;
	private String name;
	private long delay;

	public Scene(Game game, String name) {
		super();
		this.name = name;
		this.game = game;
		this.delay = 1000 / game.getFPS();
	}

	public String getName() {
		return name;
	}

	public Game getGame() {
		return game;
	}
	
	public Input getInput() {
		return getGame().getInput();
	}

	public int getWidth() {
		return game.getWidth();
	}

	public int getHeight() {
		return game.getHeight();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 0, getWidth(), getHeight());
	}
	
	@Override
	public void run() {
		
		init();

		setFinish(false);

		long beforeTime = System.currentTimeMillis();
		long timeDiff;
		
		while (!isFinish()) {
			
			timeDiff = System.currentTimeMillis() - beforeTime; // calcula tiempo desde último sleep

			beforeTime = System.currentTimeMillis();

			update(timeDiff);
			render(game.getGraphics());
			game.repaint();

			timeDiff = System.currentTimeMillis() - beforeTime; // calcula tiempo que actualiza y repinta

			if (timeDiff < delay) sleep(delay - timeDiff);
			
		}

	}

	protected abstract void init();
	protected abstract void update(long timeDiff);
	protected abstract void render(Graphics2D g);

}
