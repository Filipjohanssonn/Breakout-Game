import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle extends Sprite
{

	public Paddle(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
	}

	@Override
	public void update(Keyboard keyboard)
	{
		/* Moves the paddle to the left */
		if (keyboard.isKeyDown(Key.Left))
		{
			setX(getX() - 10);
		}
		
		/* Moves the paddle to the right */
		if (keyboard.isKeyDown(Key.Right))
		{
			setX(getX() + 10);
		}	
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		graphics.setColor(Color.decode("#BABAEC"));
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}

}
