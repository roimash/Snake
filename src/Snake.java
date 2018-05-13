import java.util.*;
import java.awt.*;
//The Site to Learn Java:http://www.javacooperation.gmxhome.de/BallBewegungEng.html
public class Snake
{
	private Vector snake = new Vector();
	private int speed;
	private int lives;
	private int length;
	private int BorderX,BorderY;
	private char direction;  
	private boolean SnakeInited = false;
	Point SnakeHead;
	
	public Snake()
	{
		this.speed = 50;
		this.length = 3;
		this.lives = 3;
		this.direction = 'r';
			for (int i = 0; i < this.length; i++)
				snake.addElement(new Point(10*i,60));
		this.SnakeHead = (Point)snake.lastElement();
	}
	public void removeTail()
	{
		snake.removeElementAt(0);	
	}
	public void ClearSnake()
	{
		this.snake.removeAllElements();
	}
	public boolean CheckBorders(int x, int y) //Checks walls collision
	{
		BorderX = x-1;
		BorderY = y-1;
		SnakeHead = (Point)snake.lastElement();
		if (SnakeHead.x > BorderX) // If snake passes right border
		{
			this.ClearSnake();
			this.direction = 'r';
			this.length = 3;
			for (int i = 0; i < this.length+1; i++)
				snake.addElement(new Point(10*i,60));
			return true;
		}		
		else if (SnakeHead.x < 0)// If snake passes left border
		{
			this.ClearSnake();
			this.direction = 'r';
			this.length = 3;
			for (int i = 0; i < this.length+1; i++)
				snake.addElement(new Point(10*i,60));
			return true;
		}
		else if (SnakeHead.y > BorderY)// If snake passes lower border
		{
			this.ClearSnake();
			this.direction = 'r';
			this.length = 3;
			for (int i = 0; i < this.length+1; i++)
				snake.addElement(new Point(10*i,60));
			return true;
		}
		else if (SnakeHead.y < 0)// If snake passes upper border
		{
			this.ClearSnake();
			this.direction = 'r';
			this.length = 3;
			for (int i = 0; i < this.length+1; i++)
				snake.addElement(new Point(10*i,60));
			return true;
		}
		return false;
	}
	public boolean CheckSnakeCrush()
	{
		SnakeHead = (Point)snake.lastElement();
		Point body;
		int i = 0;
		while(i < this.length-1)
		{
			body = (Point)snake.elementAt(i);
			if((SnakeHead.x == body.x) && (SnakeHead.y == body.y))
			{
				this.ClearSnake();
				this.direction = 'r';
				this.length = 3;
				for (int j = 0; j < this.length+1; j++)
					snake.addElement(new Point(10*j,60));
				return true;
			}
			i++;
		}
		return false;
	}
	public void addBody()
	{
		Point first;
		first = (Point)snake.lastElement();
		switch (direction)
		{
			case 'u':
				snake.addElement(new Point(first.x,first.y-10));
				break;
			case 'd':
				snake.addElement(new Point(first.x,first.y+10));
				break;
			case 'r':
				snake.addElement(new Point(first.x+10,first.y));
				break;
			case 'l':
				snake.addElement(new Point(first.x-10,first.y));
				break;
		}
		this.length++;
	}
	//-------------------------
	//Change Elements functions
	//-------------------------
	public void ChangeDirection(char changeDirection)
	{
		this.direction = changeDirection;
	}
	public void ChangeSpeed(int changeSpeed)
	{
		this.speed = changeSpeed;
	}
	public void ChangeLength(int changeLength)
	{
		this.length = changeLength;
	}
	public void ChangeLives(int lives)
	{
		this.lives = lives;
	}
	//-------------------------
	//Get Elements functions
	//-------------------------
	public char getDirection()
	{
		return this.direction;
	}
	public int getSpeed()
	{
		return this.speed;
	}
	public int getLength()
	{
		return this.length;
	}
	public int getLives()
	{
		return this.lives;
	}
	//-------------------------
	//Move Snake
	//-------------------------
	public void Move()
	{
		SnakeHead = (Point)snake.lastElement();
		if(this.direction == 'r')
		{
			snake.addElement(new Point(SnakeHead.x+10,SnakeHead.y));
		}
		if(this.direction == 'l')
		{
			snake.addElement(new Point(SnakeHead.x-10,SnakeHead.y));
		}
		if(this.direction == 'u')
		{
			snake.addElement(new Point(SnakeHead.x,SnakeHead.y-10));
		}
		if(this.direction == 'd')
		{
			snake.addElement(new Point(SnakeHead.x,SnakeHead.y+10));
		}
	}
	public void DrawSnake(Graphics g)
	{
		for (int i = 0; i < this.length; i++)
		{
			if(i == this.length-1)
			{
				if(this.lives == 3)
				{
					g.setColor(Color.green);
					g.fillRect(((Point)snake.elementAt(i)).x,((Point)snake.elementAt(i)).y, 9,9);
				}
				else if(this.lives == 2)
				{
						g.setColor(Color.orange);
						g.fillRect(((Point)snake.elementAt(i)).x,((Point)snake.elementAt(i)).y, 9,9);
				}
				else if(this.lives == 1)
				{
					g.setColor(Color.red);
					g.fillRect(((Point)snake.elementAt(i)).x,((Point)snake.elementAt(i)).y, 9,9);
				}
			}
			else
			{
				g.setColor(Color.black);
				g.fillRect(((Point)snake.elementAt(i)).x,((Point)snake.elementAt(i)).y, 9,9);
			}
		}
	}
}
