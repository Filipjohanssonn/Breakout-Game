import java.awt.*;

public class Ball extends Sprite
{
	public BallState state;
	private int direction;
	private int xSpeed;
	private int ySpeed;
	public int ballLife;

	
	public Ball(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		/* Here you can adjust speed of ball */
		xSpeed = 3;
		ySpeed = 3;
		/*---------------*/
		ballLife = 0;
		state = BallState.Start;
	}

	@Override
	public void update(Keyboard keyboard)
	{
		
		/* Ball spawn position */
		if(state == BallState.Start)
		{
			setY(520);
			setX(500);
		}
		
		/* When space is pressed, ballstate = Idle */
		if (keyboard.isKeyDown(Key.Space))
		{
			state = BallState.Idle;
		}
		
		if(state == BallState.Idle)
		{
			setX(getX() + 2 * xSpeed);
			setY(getY() + 2 * ySpeed);
		}
		
		/* Collision med brick. */
		if(state == BallState.CollidingBrick)
		{
			setX(getX() + (xSpeed * 2));
			setY(getY() + (ySpeed * 2));
		}
		
		/* Collision with paddle. */
		if(state == BallState.CollidingPaddle)
		{
			setX(getX() + (xSpeed * 2));
			setY(getY() + (ySpeed * 2));
		}
		
		/* Collision with right wall. */
		if(state == BallState.RightWallCollide)
		{
			setX(getX() + (xSpeed * 2));
			setY(getY() + (ySpeed*2));
		}
		
		if(state == BallState.LeftWallCollide)
		{
			setX(getX() + (xSpeed * 2));
			setY(getY() + (ySpeed * 2));
		}
		
		/* Collision med roof. */
		if(state == BallState.RoofCollide)
		{
			setX(getX() + (xSpeed * 2));
			setY(getY() + (ySpeed * 2));
		}
		
		/* If ball gets under screen = lose one life */
		if(getY() >= 580)
		{
			state = BallState.Start;
			ballLife = ballLife + 1;
		}
		
		if(state == BallState.PaddleCornerCollide)
		{
			setY(600);
		}
		
	}
	
	@Override
	public void draw(Graphics2D graphics)
	{
		graphics.setColor(Color.decode("#e7e7de"));
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	/* Checks if ball is colliding with bricks */
	public Boolean isColliding(SquareCollection sq)
	{
		Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle blockRect = new Rectangle(sq.getX(), sq.getY(), sq.getWidth(), sq.getHeight());
		
		return playerRect.intersects(blockRect);
		
	}
	
	/* Checks if ball is colliding with paddle */
	public boolean isColliding2(Paddle paddle)
	{
		Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle blockRect = new Rectangle(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
		
		return playerRect.intersects(blockRect);
	}
	
	/* Checks if ball is colliding with roof */
	public boolean isColliding3(Borders border1)
	{
		Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle blockRect = new Rectangle(border1.getX(), border1.getY(), border1.getWidth(), border1.getHeight());
		
		return playerRect.intersects(blockRect);
	}
	
	/* Checks if ball is colliding with left wall */
	public boolean isColliding4(Borders border2)
	{
		Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle blockRect = new Rectangle(border2.getX(), border2.getY(), border2.getWidth(), border2.getHeight());
		
		return playerRect.intersects(blockRect);
	}
	
	/* Checks if ball is colliding with right wall */
	public boolean isColliding5(Borders border3)
	{
		Rectangle playerRect = new Rectangle(getX(), getY(), getWidth(), getHeight());
		Rectangle blockRect = new Rectangle(border3.getX(), border3.getY(), border3.getWidth(), border3.getHeight());
		
		return playerRect.intersects(blockRect);
	}

	
	public int getDirection()
	{
		return direction;
	}

	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	public int getxSpeed()
	{
		return xSpeed;
	}

	public void setxSpeed(int xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public int getySpeed()
	{
		return ySpeed;
	}

	public void setySpeed(int ySpeed)
	{
		this.ySpeed = ySpeed;
	}

	public int getBallLife()
	{
		return ballLife;
	}

	public void setBallLife(int ballLife)
	{
		this.ballLife = ballLife;
	}
	

}
