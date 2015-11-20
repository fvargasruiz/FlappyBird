package dad.games;


import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;

public class Game extends BackgroundTask {

	private static final int DEFAULT_FPS = 200;
	
	private List<GameListener> listeners;

	private int fps;
	private Input input;

	private JComponent canvas;
	private BufferedImage buffer;
	
	private Scene currentScene, nextScene;
	private Map<String, Scene> scenes;
	
	public Game(JComponent canvas) {
		this(canvas, DEFAULT_FPS);
	}
	
	public Game(JComponent canvas, int fps) {
		super();
		this.fps = fps;
		this.canvas = canvas;
		initGame();
	}
	
	private void initGame() {
		listeners = new ArrayList<GameListener>();

		scenes = new HashMap<String, Scene>();
		
		input = new Input();
		
		canvas.setFocusable(true);
		canvas.setDoubleBuffered(true);		
		canvas.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) { onKeyPressed(e); }
			public void keyReleased(KeyEvent e) { onKeyReleased(e); }
		});
		canvas.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { onMousePressed(e); }
			public void mouseReleased(MouseEvent e) { onMouseReleased(e); }
		});	
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) { onMouseMoved(e); }
		});
		
		buffer = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
		initRenderingHits(buffer.createGraphics());

	}

	private void initRenderingHits(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	protected void onMousePressed(MouseEvent e) {
		input.setClickCount(e.getClickCount());
	}

	protected void onMouseReleased(MouseEvent e) {
		input.setClickCount(0);
	}
	
	protected void onMouseMoved(MouseEvent e) {
		input.setMouseLocation(e.getPoint());
	}

	protected void onKeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: input.setQuit(true); break;
		case KeyEvent.VK_LEFT: input.setLeft(true); break;
		case KeyEvent.VK_RIGHT: input.setRight(true); break;
		case KeyEvent.VK_UP: input.setUp(true); break;
		case KeyEvent.VK_DOWN: input.setDown(true); break;
		case KeyEvent.VK_SPACE: input.setShoot(true); break;
		}
	}

	protected void onKeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: input.setQuit(false); break;
		case KeyEvent.VK_LEFT: input.setLeft(false); break;
		case KeyEvent.VK_RIGHT: input.setRight(false); break;
		case KeyEvent.VK_UP: input.setUp(false); break;
		case KeyEvent.VK_DOWN: input.setDown(false); break;
		case KeyEvent.VK_SPACE: input.setShoot(false); break;
		}
	}
	
	public void setScene(String sceneName) {
		currentScene = scenes.get(sceneName);		
	}

	public void changeScene(String sceneName) {
		nextScene = scenes.get(sceneName);
		if (currentScene != null) {
			currentScene.setFinish(true);
		} else {
			setFinish(true);
		}
	}
	
	public JComponent getCanvas() {
		return canvas;
	}

	public void quit() {
		changeScene(null);
	}
	
	public void run() {
		setFinish(false);
		while (!isFinish()) {
			currentScene.start(true);
			if (nextScene == null) {
				setFinish(true);
			} else {
				currentScene = nextScene;
			}
		}
		fireGameFinished();
	}
	
	public final Input getInput() {
		return input;
	}
	
	public void add(Scene scene) {
		scenes.put(scene.getName(), scene);
	}
	
	public Graphics2D getGraphics() {
		return (Graphics2D) buffer.getGraphics();
	}
	
	public void repaint() {
		canvas.getGraphics().drawImage(buffer, 0, 0, buffer.getWidth(), buffer.getHeight(), null);
	}

	public int getFPS() {
		return fps;
	}

	public int getWidth() {
		return canvas.getWidth();
	}

	public int getHeight() {
		return canvas.getHeight();
	}
	
	@Override
	public void stop() {
		if (currentScene != null) {
			nextScene = null;
			currentScene.stop();
		}
		super.stop();
	}
	
	public void addGameListener(GameListener listener) {
		listeners.add(listener);
	}
	
	public void removeGameListener(GameListener listener) {
		listeners.remove(listener);
	}
	
	private void fireGameFinished() {
		for (GameListener listener : listeners) {
			listener.gameFinished();
		}
	}

	public void setCursor(Cursor cursor) {
		canvas.setCursor(cursor);
	}
	
}
