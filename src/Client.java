import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
//import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client extends Thread
{
	private int destination;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Lock lock = new ReentrantLock();
	
	public Client(InetAddress addr, int dest) 
	{
		try 
		{
			socket = new Socket(addr, 1234);
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			destination = dest;
			start();
		} 
		catch(IOException e) 
		{
			try 
			{
				 socket.close();
			}
			catch(IOException e2) 
			{
				e2.printStackTrace();
			}
		}
	}
	public void run()
	{
		lock.lock();
		int place = 0;
		Random r = new Random();
		switch(destination)
		{
		case 1: while(place == 0) {place = r.nextInt(51);};break;
		case 2: while(place == 0) {place = r.nextInt(49);};break;
		case 3: while(place == 0) {place = r.nextInt(35);};break;
		case 4: while(place == 0) {place = r.nextInt(45);};break;
		case 5: while(place == 0) {place = r.nextInt(37);};break;
		case 6: while(place == 0) {place = r.nextInt(43);};break;
		}
		String dest = "" + destination;
		String pl = "" + place;
		out.println(dest + " " + pl);
		try 
		{
			in.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			 lock.unlock();
		}
	}
}
