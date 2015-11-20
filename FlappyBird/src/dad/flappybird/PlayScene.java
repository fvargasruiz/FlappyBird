package dad.flappybird;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dad.flappybird.resources.Sounds;
import dad.games.Game;
import dad.games.Scene;
import dad.games.Sprite;

public class PlayScene extends Scene {

	public PlayScene(Game game) {
		super(game, "PlayScene");
	}

	private MenuButton menuButton;
	private FPSSprite fps;
	private BirdSprite bird;
	private BackgroundSprite background;
	private LandSprite land;
	private GameOverSprite gameover;
	private GetReadySprite ready;
	private ScoreSprite score;
	private List<Sprite> pipes;
	
	private Random random = new Random();
	private long time;
	private boolean isGameover;
	private int rnd;
	private boolean betweenPipes;
	
	@Override
	protected void init() {
		
		background = new BackgroundSprite(this, "bg_day.png");
		fps = new FPSSprite(this);
		bird = new BirdSprite(this);
		land = new LandSprite(this);
		gameover = new GameOverSprite(this);
		ready = new GetReadySprite(this);
		menuButton = new MenuButton(this);
		pipes = new ArrayList<Sprite>();
		score = new ScoreSprite(this);
		
		gameover.setVisible(false);
		
		isGameover = false;
		betweenPipes = false;
		time = 0;
		rnd = 0;
		
	}

	@Override
	protected void update(long timeDiff) {
		
		fps.update(timeDiff);		
		menuButton.update(timeDiff);
		
		if (getInput().isQuit()) {
			
			getGame().changeScene("MenuScene");
			
		}
		else if (isGameover) {
			
			gameover.setVisible(true);
			
		}
		else if (!isGameover && !ready.isVisible()) {
			
			land.update(timeDiff);
	        background.update(timeDiff);
			bird.update(timeDiff);
			score.update(timeDiff);
			
			// crea una tubería cada 2 segundos
			if (time > 1000L + rnd) {
				
				int pipeY = -100 + random.nextInt(200); 
				
				PipeUpSprite newPipeUp = new PipeUpSprite(this);
				newPipeUp.init();
				newPipeUp.move(0, pipeY);

				PipeDownSprite newPipeDown = new PipeDownSprite(this);
				newPipeDown.init();
				newPipeDown.move(0, pipeY);
				
				pipes.add(newPipeUp);
				pipes.add(newPipeDown);

				time = 0;
				
				rnd = random.nextInt(500);
			}
			
			// actualiza las tuberías
			List<Sprite> removedPipes = new ArrayList<Sprite>();
			for (Sprite pipe : pipes) {
				pipe.update(timeDiff);
				if (!pipe.getBounds().intersects(getBounds())) {
					removedPipes.add(pipe);
				}
			}
			pipes.removeAll(removedPipes);
			
			checkCollisions();
			
			time += timeDiff;
			
		} 
		else if (ready.isVisible()){
			
			ready.update(timeDiff);
			
		}
	}

	@Override
	protected void render(Graphics2D g) {
		background.paint(g);
		for (Sprite pipe : pipes) {
			pipe.paint(g);
		}
		land.paint(g);
		fps.paint(g);
		menuButton.paint(g);
		bird.paint(g);
		score.paint(g);
		gameover.paint(g);
		ready.paint(g);
	}
	
	private void checkCollisions() {
		
		// evitar que el pajarito se salga por arriba o por abajo
		if (bird.getY() < 0) bird.setY(0);
		if (bird.getY() + bird.getHeight() > getHeight()) bird.setY(getHeight() - bird.getHeight());

		// choque con el suelo
		if (bird.getBounds().intersects(land.getBounds())) {
			isGameover = true;
			Sounds.load("collision.aiff").play();			
		}
		
		// choque con tubería
		for (Sprite pipe : pipes) {
			if (bird.getBounds().intersects(pipe.getBounds())) {
				isGameover = true;
				Sounds.load("collision.aiff").play();							
			}
		}
		
		// comprueba que se paso la tubería (aumentar puntuación)
		boolean between = false;
		for (Sprite pipe : pipes) {
			if (bird.getX() >= pipe.getX() && bird.getX() <= pipe.getX() + pipe.getWidth()) { 
				between = true;
			}
		}
		if (between && !betweenPipes) {
			score.incScore(1);
			Sounds.load("point.aiff").play();
			betweenPipes = true;
		}
		if (!between && betweenPipes) {
			betweenPipes = false;
		}
		
	}
	
}
