import java.awt.Color;
import java.awt.Graphics2D;

public class Borders extends Sprite
{

	public Borders(int x, int y, int width, int height)
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
		graphics.setColor(Color.decode("#BABAEC"));
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
