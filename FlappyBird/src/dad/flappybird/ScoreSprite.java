package dad.flappybird;

import java.awt.Graphics2D;
import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.games.Scene;
import dad.games.Sprite;

public class ScoreSprite extends Sprite {
	private Integer score;
	private Image [] numbers;
	private int numberWidth, numberHeight;
	
	public ScoreSprite(Scene scene) {
		super(scene);
		init();
	}

	@Override
	public void init() {
		this.numbers = new Image[10];
		this.score = 0;
		for (int i = 0; i <= 9; i++) {
			numbers[i] = Images.load("number_score_0" + i + ".png");
		}
		this.numberWidth = numbers[0].getWidth(null);
		this.numberHeight = numbers[0].getHeight(null);
		update(0L);
	}

	@Override
	public void paint(Graphics2D g) {
		int x = (int)getX();
		String text = "" + this.score;
		for (int i = 0; i < text.length(); i++) {
			int num = 0;
			try {
				num = Integer.parseInt(text.substring(i, i + 1));
			} catch (NumberFormatException e) {
				break;
			}
			g.drawImage(numbers[num], x, (int)getY(), null);
			x += numberWidth;
		}
	}
	
	protected int calcWidth(String text) {
		int width = 0;
		for (int i = 0; i < text.length(); i++) {
			width += numberWidth;
		}
		return width;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	public void incScore(int increase) {
		this.score += increase;
	}

	@Override
	public void update(long timeDiff) {
		int numbers = ("" + score).length();  
		setSize(numbers * (numberWidth + 1), numberHeight);
		setLocation(getScene().getWidth() - getWidth() - 5, 5);
	}

}
