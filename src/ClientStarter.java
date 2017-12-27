import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ClientStarter 
{
	public static void main(String[] args) throws UnknownHostException 
	{
		InetAddress addr = InetAddress.getByName(null);
		for(int i = 1; i < 7; i++)
		{
			for(int j = 0; j < 10; j++)
			{
				new Client(addr, i);
				try 
				{
					Thread.currentThread();
					Thread.sleep(1000);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
		//System.out.println("Ready!");
		ExecutorService executor = Executors.newFixedThreadPool(30);
        for (int i = 1; i < 7; i++) 
        {
        	for (int j = 0; j < 30; j++)
        	{
        		Runnable worker = new Client(addr, i);
                executor.execute(worker);
                try 
                {
					Thread.currentThread();
					Thread.sleep(200);
				} 
                catch (InterruptedException e) 
                {
					e.printStackTrace();
				}
        	}
        }
        executor.shutdown();
	}
}
