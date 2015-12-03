package dad.flappybird;

import dad.games.ImageSprite;
import dad.games.Scene;

public abstract class PipeSprite extends ImageSprite {

	public static final float SPEED = -250f;

	private float speed;

	public PipeSprite(Scene screen) {
		super(screen);
		speed = SPEED;		
	}

	@Override
	public abstract void init();

	@Override
	public void update(long timeDiff) {
		float offset = speed * (timeDiff / 1000.0f);
		setRealX(getRealX() + offset);
		if (getX() + getWidth() < 0) {
			setX(getScene().getWidth());
		}
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
