import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JComponent;

public class GameBoard extends JComponent {
	private final int FPS = 60; 
	private Game game;
	private Keyboard keyboard;
	private GameState gs;
	public GameBoard()
	{
		keyboard = new Keyboard();
		game = new Game(this);
		gs = game.getGameState();
	}

	@Override
	protected void paintComponent(Graphics arg0) 
	{
		super.paintComponent(arg0);
		Graphics2D graphics = (Graphics2D)arg0;
		graphics.setColor(Color.decode("#000000"));
		graphics.fillRect(0, 0, getWidth(), getHeight());
		game.draw(graphics);
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) 
	{
		super.processKeyEvent(e);
		if (e.getID() == KeyEvent.KEY_PRESSED)
			keyboard.processKeyEvent(e.getKeyCode(), true);
		else if (e.getID() == KeyEvent.KEY_RELEASED)
			keyboard.processKeyEvent(e.getKeyCode(), false);
	}
	
	public int getScore()
	{
		return game.getScoreCounter();
	}
	
	public GameState getGs()
	{
		return game.getGameState(); 
	}

	public void setGs(GameState gs)
	{
		game.setGameState(gs);
	}

	public void start() 
	{
		while(true) 
		{
			game.update(keyboard);
			try 
			{
				Thread.sleep(1000 / FPS); //Throttle thread
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
