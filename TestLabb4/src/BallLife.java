import java.awt.Color;
import java.awt.Graphics2D;

public class BallLife extends Sprite
{

	public BallLife(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
	}

	@Override
	public void update(Keyboard keyboard)
	{
		
		
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		graphics.setColor(Color.decode("#e7e7de"));
		graphics.fillOval(getX(), getY(), getWidth(), getHeight());
		
	}

}
