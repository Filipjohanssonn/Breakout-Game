import java.awt.*;

public class ColoredBox extends Sprite 
{
	private Color color;
	private int life;
	private int hitCount = 0;
	
	public ColoredBox(int x, int y, int width, int height, Color color, int life)
	{
		super(x, y, width, height);
		this.color = color;
		this.setLife(life);
	}

	@Override
	public void update(Keyboard keyboard)
	{
		
	}

	@Override
	public void draw(Graphics2D graphics)
	{
		graphics.setColor(color);
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}

	public int getLife()
	{
		return life;
	}

	public void setLife(int life)
	{
		this.life = life;
	}

	public int getHitCount()
	{
		return hitCount;
	}

	public void setHitCount(int hitCount)
	{
		this.hitCount = hitCount;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}

}
