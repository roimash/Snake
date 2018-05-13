import java.util.TimerTask;
import java.applet.*;
import java.awt.*;
public class Timerrr extends Applet implements Runnable
{
	Thread th;
	int counter;
	boolean timesUp = false;
	
	public Timerrr(int time)
	{
		this.counter = time;
		while(this.counter > 0)
		{
			this.counter -= 1;
			try{
			th.sleep(1000);
			}
			catch(InterruptedException ex){}
			repaint();
		}
		this.timesUp = true;
	}
	public void DrawTimer(Graphics g)
	{
		g.drawString("Time Remaining:"+this.counter, 10, 10);
	}
	public void start()
	{
		th = new Thread(this);
		th.start();
	}
	public void init(){}
	public void run(){}
	public void stop(){}
	public void destroy(){}
}