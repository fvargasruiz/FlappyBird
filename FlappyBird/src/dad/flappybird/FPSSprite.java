package dad.flappybird;

import java.awt.Graphics2D;
import java.awt.Image;

import dad.flappybird.resources.Images;
import dad.games.Scene;

public class FPSSprite extends NumberSprite {
	
	private long ms = 0L;
	private Image fpsImage;
	
	public FPSSprite(Scene scene) {
		super(scene);
	}

	@Override
	public void init() {
		super.init();
		ms = 0;
		fpsImage = Images.load("fps.png");
        setNumber(0);
		setSize(0, fpsImage.getHeight(null));
		setLocation(5, getScene().getHeight() - getHeight() - 5);
	}
	
	@Override
	public void update(long timeDiff) {		
        ms += timeDiff;
        if (ms >= 250) {
        	setNumber((int)(1000.0 / timeDiff));
        	ms = 0;
        }
	}
	
	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		int width = calcWidth("" + getNumber());
		g.drawImage(fpsImage, getX() + width + 1, (int)getY(), null);
	}

}
