package dad.flappybird;

import java.awt.Graphics2D;
import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.games.Scene;
import dad.games.Sprite;

public abstract class NumberSprite extends Sprite {
	private Integer number;
	private Image [] numbers;
	private int numberWidth;
	
	public NumberSprite(Scene scene) {
		super(scene);
		init();
	}

	@Override
	public void init() {
		this.numbers = new Image[10];
		this.number = 0;
		for (int i = 0; i <= 9; i++) {
			numbers[i] = Images.load("number_context_0" + i + ".png");
		}
		this.numberWidth = numbers[0].getWidth(null);
	}

	@Override
	public void paint(Graphics2D g) {
		int x = (int)getX();
		String text = "" + this.number;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
