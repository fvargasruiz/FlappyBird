package dad.flappybird.ui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import dad.flappybird.MenuScene;
import dad.flappybird.PlayScene;
import dad.flappybird.resources.Images;
import dad.games.Game;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private Game game;

	public MainFrame() {
		initFrame();
		initComponents();
	}

	private void initFrame() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setFocusable(false);
        setResizable(false);
        setIconImage(Images.load("icon.png"));
		getContentPane().setPreferredSize(new Dimension(576, 512));
		pack();
        setLocationRelativeTo(null);     
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) { onWindowClosing(e); }
		});
	}

	protected void onWindowClosing(WindowEvent e) {
		game.stop();
		dispose();
	}

	private void initComponents() {
		game = new Game((JComponent)getContentPane());
		game.add(new MenuScene(game));
		game.add(new PlayScene(game));
		game.setScene("MenuScene");
		game.start();
//		game.addGameListener(new GameListener() {
//			public void gameFinished() { onGameFinished(); }
//		});
	}

//	protected void onGameFinished() {
//		dispose();
//	}
		
}
