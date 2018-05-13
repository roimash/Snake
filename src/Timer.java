import java.sql.Time;
import java.util.TimerTask;
import java.applet.*;
import java.awt.*;
public class Timer extends Applet implements Runnable
{
	Thread th;
	int counter;
	boolean timesUp = false;
	
	Time t;
	
	public Timer(int time)
	{
	}
	public void DrawTimer(Graphics g)
	{
		g.drawString("Time Remaining:"+this.t, 10, 10);
	}
	public void start()
	{
		th = new Thread(this);
		th.start();
	}
	public void init()
	{
		setLayout(new FlowLayout());
		setSize(200,200);
	}
	public void run()
	{
		t.setTime(100000);
		
		try
		{
			t.wait(100);
		}
		catch(InterruptedException ex){};
	}
	public void stop(){}
	public void destroy(){}
}