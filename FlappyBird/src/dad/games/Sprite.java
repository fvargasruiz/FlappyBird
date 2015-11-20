package dad.games;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Sprite {
	private float x, y;
	private int width, height;
	private Scene scene;
	private boolean visible;

	public Sprite() {
		this(0, 0);
	}

	public Sprite(int x, int y) {
		this((float)x, (float)y);
	}

	public Sprite(float x, float y) {
		visible = true;
		setRealX(x);
		setRealY(y);
	}

	public Sprite(Scene scene) {
		this(0, 0);
		this.scene = scene;
	}

	public int getX() {
		return Math.round(x);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return Math.round(y);
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public float getRealX() {
		return x;
	}

	public void setRealX(float x) {
		this.x = x;
	}

	public float getRealY() {
		return y;
	}

	public void setRealY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLocation(int x, int y) {
		setX(x);
		setY(y);
	}

	public void setLocation(float x, float y) {
		setRealX(x);
		setRealY(y);
	}

	public void setSize(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	protected Scene getScene() {
		return scene;
	}

	public void move(float incX, float incY) {
		x += incX;
		y += incY;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public void centerOnScene() {
		setLocation((getScene().getWidth() - getWidth()) / 2, (getScene().getHeight() - getHeight()) / 2);
	}

	public abstract void init();
	public abstract void update(long timeDiff);
	public abstract void paint(Graphics2D g);

}
