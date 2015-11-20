package dad.games;

import java.awt.Point;

public class Input {
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	private boolean shoot;
	private boolean quit;

	private int clickCount;
	private Point mouseLocation = new Point(-1, -1);

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return "L:" + left + "/R:" + right + "/U:" + up + "/D:" + down;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public Point getMouseLocation() {
		return mouseLocation;
	}

	public void setMouseLocation(Point mouseLocation) {
		this.mouseLocation = mouseLocation;
	}
	
	public void resetClick() {
		this.clickCount = 0;
	}

}
