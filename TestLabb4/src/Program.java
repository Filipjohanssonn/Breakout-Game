
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

public class Program extends JFrame implements ActionListener
{
	/* Here you can change window size */
	public static final int WIDTH = 1150;
	public static final int HEIGHT = 600;
	/* ---------------------------------*/
	GameBoard board;
	GameState gameState;
	private JButton updateButton;
	/* Latest runs */
	public LatestRuns latestRuns;
	private JList latestRunsJlist;
	
	/* Highscore */
	private HighScore highScore;
	private JList highScoreJlist;
	
	private Person person;
	private int score;

	public Program() 
	{
		board = new GameBoard();
		JPanel panel = new JPanel();
		
		latestRuns = new LatestRuns();
		latestRunsJlist = new JList(latestRuns.getLatestRuns());
		latestRunsJlist.setFocusable(false);
		
		highScore = new HighScore();
		highScoreJlist = new JList(highScore.getHighScore());
		highScoreJlist.setFocusable(false);

		Person title = new Person("HIGHSCORE MAX: 3", 0);
		highScore.add(title);
		latestRuns.add("LATEST RUNS");
		/* JButton */
		updateButton = new JButton();
		updateButton.setFocusable(false);
		updateButton.setText("Update score");
		updateButton.setSize(100, 50);
		updateButton.setLocation(850, 500);
		updateButton.addActionListener(this);
		updateButton.setFocusPainted(false);
		
		/* JPanel */
		panel.add(highScoreJlist);
		panel.add(latestRunsJlist);
		panel.add(updateButton);
		panel.setBackground(Color.white);
		
		/* Sets GUI up properly */
		this.setLayout(new BorderLayout());
		add(board, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		show();
		board.start();
	}
	
	@Override
	protected void processKeyEvent(KeyEvent e) 
	{
		super.processKeyEvent(e);
		board.processKeyEvent(e);
	}
	
	/* Main */
	public static void main(String[] args)
	{
		Program program = new Program();
	}

	/* So the program updates when JButton is pressed. Lot of code here but important stuff for labb4. */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		/* If reset button is pressed */
		if(e.getSource() == updateButton)
		{
			if(board.getGs() == GameState.Playing)
			{
				board.setGs(GameState.Restart);
				/* Latest Run */
				/* If latest run list has less then 3 elements. You are allowed to add a new. */
				if(latestRuns.getEntireLatestRunList() <= 4)
				{
					latestRuns.add(board.getScore()); 
				}
				/* If latest run list has more then 4 elements. It removes the first score.  */
				if(latestRuns.getEntireLatestRunList() >= 5)
				{
					latestRuns.remove();					
				}
				/* Highscore */
				
				/* Removes the person at first position in list */
				if(highScore.getHighScoreSize() == 11)
				{
					highScore.remove(highScore.getHighScore().getElementAt(1));
				}
				
				/* Ask's the user to input there initials */
				JLabel label;
				label = new JLabel("to be changed");
				add(label);
				// Pre: Användaren skriver bara in 3 bokstäver
				String tmp = JOptionPane.showInputDialog("Enter 3 initials");
				label.setText(tmp); 
				Person p = new Person(tmp, board.getScore());
				highScore.add(p);
				
				/* Bubble sort created with help from [KAU] Axel in Labbhandledning in Zoom */
				for(int j = 0; j < 11; j++)
				{
					for(int k = 1; k < highScore.getHighScoreSize(); k++)
					{
						System.out.println( highScore.getHighScore().elementAt(k-1).getScore() + " vs " + highScore.getHighScore().elementAt(k).getScore());
						if(highScore.getHighScore().elementAt(k-1).getScore() > highScore.getHighScore().elementAt(k).getScore()) 
						{ 
							/* Swapping */
							Person temp;
							temp = highScore.getHighScore().elementAt(k-1);
							highScore.getHighScore().setElementAt(highScore.getHighScore().getElementAt(k), k-1);
							highScore.getHighScore().setElementAt(temp, k);
						}
					}
				}	
			}
		}
	}
}
