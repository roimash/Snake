/****************************************************************************
 * This code programmed by me (Roi Mashiah) in 2010
 *****************************************************************************/
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class Main extends Applet implements Runnable,KeyListener
{
	int score;
	int SizeX, SizeY;
	boolean start = false , stop = false , foodCreated = false;

	Snake Snake = new Snake();
	Food Food = new Food();
	Image LauncherImg,GameOverImg;
	AudioClip coin,LiveDown;
	Thread th;

	public Main()
	{
		score = 0;
		SizeX = 200;
		SizeY = 200;
	}
    public void init()
	{
    	addKeyListener(this);
    	this.setFocusable(true);
		this.setLayout(new FlowLayout());
		this.setSize(this.SizeX,this.SizeY);
		Food.SetLimits(this.SizeX,this.SizeY);
		System.out.println(getCodeBase());
		LauncherImg = getImage(getCodeBase(), "Launcher.gif");
		GameOverImg = getImage(getCodeBase(), "GameOver.gif");
		coin = getAudioClip(getCodeBase(), "coin.au");
		LiveDown = getAudioClip(getCodeBase(), "powerdown.au");
    }
    public void start()
    {
		th = new Thread(this);
		th.start();
    }
    public void paint(Graphics g)
    {
		if(!start)
		{
			g.drawImage(LauncherImg,this.SizeX/8,this.SizeY/8,this);
		}
		else if(!stop)
    	{
    		g.drawRect(0,0,SizeX-1,SizeY-1);
    		//g.drawString("??????:"+this.lives, 10, 10);
    		Food.paintFood(g);
    		g.setColor(Color.BLACK);
    		Snake.DrawSnake(g);
    	}
    	else
    	{
			g.drawImage(GameOverImg,this.SizeX/8,this.SizeY/8,this);
			Font f = new Font("Verdana",Font.BOLD,18);
			g.setFont(f);
			g.drawString(""+this.score,68,135);

    	}
    }
	public void keyPressed(KeyEvent e)
	{
		int Key = e.getKeyCode();
		switch (Key)
		{
			case KeyEvent.VK_ENTER:
				this.start = true;
				break;
			case KeyEvent.VK_UP:
				if(Snake.getDirection() != 'd')
					Snake.ChangeDirection('u');
				break;
			case KeyEvent.VK_DOWN:
				if(Snake.getDirection() != 'u')
					Snake.ChangeDirection('d');
				break;
			case KeyEvent.VK_LEFT:
				if(Snake.getDirection() != 'r')
					Snake.ChangeDirection('l');
				break;
			case KeyEvent.VK_RIGHT:
				if(Snake.getDirection() != 'l')
					Snake.ChangeDirection('r');
				break;
			case KeyEvent.VK_W:
				if(Snake.getDirection() != 'd')
					Snake.ChangeDirection('u');
				break;
			case KeyEvent.VK_S:
				if(Snake.getDirection() != 'u')
					Snake.ChangeDirection('d');
				break;
			case KeyEvent.VK_A:
		if(Snake.getDirection() != 'r')
					Snake.ChangeDirection('l');
				break;
			case KeyEvent.VK_D:
				if(Snake.getDirection() != 'l')
					Snake.ChangeDirection('r');
				break;
		}
		repaint();
	}
    public void run()
    {
		//Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
    	while(this.stop != true)
    	{
    		System.out.println("Running...");
    		if(start)
    		{
    			Snake.Move();
    			if(foodCreated == false)
    			{
    				Food.CreateFood();
    				foodCreated = true;
    			}
    			//Checks Snake coin get
    			if((Snake.SnakeHead.x == Food.Location.x) && (Snake.SnakeHead.y == Food.Location.y))
    			{
    				int lives = Snake.getLives();
    				switch (lives)
    				{
    					case 3:
    						this.score += 100;
    						break;
    					case 2:
    						this.score += 200;
    						break;
    					case 1:
    						this.score += 300;
    						break;
    					default:
    						this.score += 100;
    				}
    				this.coin.play();
    				Snake.addBody();
    				foodCreated = false;
    			}
    			//Checks Snake Wall - Crush
    			if(Snake.CheckBorders(this.SizeX,this.SizeY) == true)
    			{
    				//this.stop = true;
    				Snake.ChangeLives(Snake.getLives()-1);
    				this.LiveDown.play();
    				Snake.ChangeSpeed(Snake.getSpeed() - 10);
    				if(Snake.getLives() == 0)
   						this.stop = true;
   				}
    			//Check Snake Self-Crush
    			if(Snake.CheckSnakeCrush() == true)
    			{
    				//this.stop = true;
    				Snake.ChangeLives(Snake.getLives()-1);
    				this.LiveDown.play();
    				Snake.ChangeSpeed(Snake.getSpeed() - 10);
    				if(Snake.getLives() == 0)
   						this.stop = true;
   				}
   				Snake.removeTail();
    			repaint();
    			try
    			{
    				Thread.sleep(Snake.getSpeed());
    			}
    			catch (InterruptedException ex){}
    			//Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
    		}
    	}
    }
	//Auto functions.stop & destroy = Runnable,2 others = KeyLister.
    public void stop() {}
    public void destroy() {}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
}