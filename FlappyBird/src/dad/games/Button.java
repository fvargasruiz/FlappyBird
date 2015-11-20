package dad.games;

import java.awt.Cursor;
import java.awt.Image;

public abstract class Button extends ImageSprite {

	private Image normalImage;
	private Image overImage;
	private Image clickedImage;

	public Button(Scene scene) {
		super(scene);
	}
	
	@Override
	public void update(long timeDiff) {
		if (!isVisible()) return;
		
		Input input = getScene().getInput();
		if (getBounds().contains(input.getMouseLocation())) {
			getScene().getGame().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			if (input.getClickCount() > 0) {
				setImage(clickedImage);
				input.resetClick();
				getScene().getGame().setCursor(Cursor.getDefaultCursor());
				click();
			} else {
				setImage(overImage);
			}
		} else {
			setImage(normalImage);
			getScene().getGame().setCursor(Cursor.getDefaultCursor());
		}
	}

	public void setNormalImage(Image normalImage) {
		this.normalImage = normalImage;
		setImage(this.normalImage);
	}
	
	public Image getNormalImage() {
		return normalImage;
	}

	public void setOverImage(Image overImage) {
		this.overImage = overImage;
	}
	
	public Image getOverImage() {
		return overImage;
	}

	public void setClickedImage(Image clickedImage) {
		this.clickedImage = clickedImage;
	}
	
	public Image getClickedImage() {
		return clickedImage;
	}

	protected abstract void click();

}
