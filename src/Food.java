import java.util.Random;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Food extends Applet
{
	Point Location = new Point();
	Random rnd = new Random();
	private int BorderX,BorderY;
	
	public void SetLimits(int x , int y)
	{
		BorderX = x;
		BorderY = y;
	}
	public void CreateFood()
	{
		Location.x = rnd.nextInt(BorderX/10)*10;
		Location.y = rnd.nextInt(BorderY/10)*10;
	}
	public void paintFood(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillOval(Location.x,Location.y,9,9);
	}
}
