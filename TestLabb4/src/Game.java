import java.awt.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game
{
	/* To avoid ConcurrentModificationException */
	private CopyOnWriteArrayList<SquareCollection> squareCollect;
	/* The bricks */
	private ArrayList <SquareCollection> RemoveList;
	/* Score */
	public int scoreCounter;
	/* Top border */
	private Borders border1;
	/* Left border */
	private Borders border2;
	/* Right border */
	private Borders border3;
	/* Moving paddle */
	private Paddle paddle;
	/* The ball */
	private Ball ball;

	/* Ball Life */
	private BallLife ballLife1;
	private BallLife ballLife2;
	private BallLife ballLife3;
	
	private Boolean startNewGame = false;
	
	GameState gameState;
	
	public Game(GameBoard board) 
	{
		RestartGame();	
	}

	public void update(Keyboard keyboard) 
	{
		if(gameState == GameState.Playing)
		{
			paddle.update(keyboard);
			ball.update(keyboard);
			ballLife1.update(keyboard);
			ballLife2.update(keyboard);
			ballLife3.update(keyboard);
			RemoveList = new ArrayList<> ();
			
			/* Check if ball collide with bricks and add the collided brick to an remove list */
			/* https://www.youtube.com/watch?v=K9qMm3JbOH0&ab_channel=AwaisMirza */
			for(SquareCollection sq : squareCollect)
			{
				sq.update(keyboard);
				
				if(ball.isColliding(sq))
				{
					sq.setHitCount(sq.getHitCount() + 1);
					sq.setColor(Color.decode("#BABAEC"));
					System.out.println("TRÄFFAD");
					
					if(sq.getLife() == sq.getHitCount())
					{
						RemoveList.add(sq);
						squareCollect.remove(sq);
						scoreCounter = scoreCounter + 1 * sq.getLife();
					}
					
					if(ball.getX() + 10  <= sq.getX() || ball.getX() + 1 >= sq.getX() + sq.getWidth())
					{	
						ball.state = BallState.CollidingBrick;
						ball.setxSpeed(ball.getxSpeed()*(-1));
					} 
					else 
					{
						ball.state = BallState.CollidingBrick;
						ball.setySpeed(ball.getySpeed()*(-1));
					}
					
				}
				/* Om alla brickor är borta && om man inte har några liv kvar */
				if(scoreCounter >= 30 || ball.ballLife == 3)
				{
					/* Startar om spelet */
					//this.setGameState(gameState.Restart);
					System.out.println("GAMEOVER. You got " + scoreCounter + " points");
					startNewGame = true;
					
				}
	
			}
			
			/* Checks if the ball touches the paddle */
			if(ball.isColliding2(paddle))
			{
				System.out.println("Boll x och y: " + ball.getX() + " " + ball.getY());
				System.out.println("Paddle x och y: " + paddle.getX() + " " + paddle.getY());
				System.out.println(" ");
				
				if(ball.getX() >= paddle.getX() && ball.getX() <= paddle.getX() + 99 && ball.getY() <= paddle.getY())
				{
				System.out.println("You hit the paddle");
				ball.state = BallState.CollidingPaddle;
				ball.setySpeed(ball.getySpeed()*(-1));
				} else
				{
					System.out.println("You did not hit the paddle");
					ball.setY(600);
				}
			}
			
			/* Checks if the ball touches the roof */
			if(ball.isColliding3(border1))
			{
				System.out.println("You hit the roof");
				ball.state = BallState.RoofCollide;
				ball.setySpeed(ball.getySpeed()*(-1));
			}
			/* Checks if the ball touches the left wall */
			if(ball.isColliding4(border2))
			{
				//System.out.println("You hit the left wall");
				ball.state = BallState.LeftWallCollide;
				ball.setxSpeed(ball.getxSpeed()*(-1));
			}
			/* Checks if the ball touches the right wall */
			if(ball.isColliding5(border3))
			{
				//System.out.println("You hit the right wall");
				ball.state = BallState.RightWallCollide;
				ball.setxSpeed(ball.getxSpeed()*(-1));
			}
			
			/* Shows how many life's the player has left */
			if(ball.ballLife == 1)
			{
				ballLife1.setX(1000);
			}
			if(ball.ballLife == 2)
			{
				ballLife2.setX(1200);
			}
			if(ball.ballLife == 3)
			{
				ballLife3.setX(1400);
			}
		}
		
		/* Resets the game */
		if(keyboard.isKeyDown(Key.Enter))
		{
			System.out.println("ENTER IS PRESSED");
			startNewGame = false;
			RestartGame();
		}
		
	}
	

	public void RestartGame()
	{
		squareCollect = new CopyOnWriteArrayList <SquareCollection>();
		for (int i = 0; i < 6; i++)
		{
			/* Flytta till egen klass */
			squareCollect.add(new SquareCollection(60 + i * 110, 110, 100, 40, Color.decode("#9896f1"), 2));
			squareCollect.add(new SquareCollection(60 + i * 110, 160, 100, 40, Color.decode("#BABAEC"), 1));
			squareCollect.add(new SquareCollection(60 + i * 110, 210, 100, 40, Color.decode("#9896f1"), 2));
		}
		this.gameState = GameState.Playing;
		/* Setting score to '0' when game starts. */
		scoreCounter = 0;
		paddle = new Paddle(350, 567, 100, 10);
		ball = new Ball(500, 520, 15, 15);
		ballLife1 = new BallLife(700, 5, 20, 20);
		ballLife2 = new BallLife(730, 5, 20, 20);
		ballLife3 = new BallLife(760, 5, 20, 20);
		border1 = new Borders(0, 30, 800, 10);
		border2 = new Borders(0, 30, 10, 600);
		border3 = new Borders(790, 30, 10, 600);	
	}
	
	

	public int getScoreCounter()
	{
		return scoreCounter;
	}

	public void setScoreCounter(int scoreCounter)
	{
		this.scoreCounter = scoreCounter;
	}

	public GameState getGameState()
	{
		return gameState;
	}

	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
	}

	public void draw(Graphics2D graphics) 
	{
		for(SquareCollection sq : squareCollect)
		{
			sq.draw(graphics);
		}	
		if(startNewGame == true)
		{
			graphics.setFont(new Font("default", Font.BOLD, 28));
			graphics.drawString("PRESS ENTER TO START NEW GAME", 200, 400);
			graphics.setFont(new Font("default", Font.BOLD, 20));
			graphics.drawString("PRESS UPDATE SCORE BUTTON TO UPDATE SCORE", 200, 450);
		}

		/* Integer to String */
		String Score = Integer.toString(scoreCounter);
		graphics.setFont(new Font("default", Font.BOLD, 16));
		graphics.drawString("Score: " + Score, 20, 20);
		
		paddle.draw(graphics);
		ball.draw(graphics);
		ballLife1.draw(graphics); ballLife2.draw(graphics); ballLife3.draw(graphics);
		border1.draw(graphics);	border2.draw(graphics);	border3.draw(graphics);
	}
}
