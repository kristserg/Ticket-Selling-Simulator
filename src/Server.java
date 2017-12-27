//import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
//import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Server extends Thread
{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private List<String> list1 = Collections.synchronizedList(new ArrayList<String>(50));
	private List<String> list2 = Collections.synchronizedList(new ArrayList<String>(48));
	private List<String> list3 = Collections.synchronizedList(new ArrayList<String>(34));
	private List<String> list4 = Collections.synchronizedList(new ArrayList<String>(44));
	private List<String> list5 = Collections.synchronizedList(new ArrayList<String>(36));
	private List<String> list6 = Collections.synchronizedList(new ArrayList<String>(42));
	private Lock lock = new ReentrantLock();
	private static int sum;
	private static int soldTickets1 = 0;
	private static int soldTickets2 = 0;
	private static int soldTickets3 = 0;
	private static int soldTickets4 = 0;
	private static int soldTickets5 = 0;
	private static int soldTickets6 = 0;
	private static int price;
	private static String destination;
	
	public Server(Socket s, List<String> l1, List<String> l2, List<String> l3, List<String> l4, List<String> l5, List<String> l6) throws IOException 
	{
		socket = s;
		list1 = l1;
		list2 = l2;
		list3 = l3;
		list4 = l4;
		list5 = l5;
		list6 = l6;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		start();
	}
	public static int getSum()
	{
		return sum;
	}
	public static int getSoldTickets1()
	{
		return soldTickets1;
	}
	public static int getSoldTickets2()
	{
		return soldTickets2;
	}
	public static int getSoldTickets3()
	{
		return soldTickets3;
	}
	public static int getSoldTickets4()
	{
		return soldTickets4;
	}
	public static int getSoldTickets5()
	{
		return soldTickets5;
	}
	public static int getSoldTickets6()
	{
		return soldTickets6;
	}
	public void run()
	{
		lock.lock();
		Random rand = new Random();
		String str = "";
		String newPlace = "";
		try 
		{
			str = in.readLine();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		char c = str.charAt(0);
		String st = str.substring(2);
		boolean flag = true;
		
		switch(c)
		{
		case '1' : if(!list1.isEmpty())
					{while(!list1.contains(st)) 
					 {int pl = rand.nextInt(51); st = String.valueOf(pl);}
					 if(list1.contains(st))
					  {int ind = list1.indexOf(st); list1.remove(ind); newPlace = st; sum += 30; soldTickets1++; destination = "Varna"; price = 30;}}
						else {flag = false;};break;
		case '2' : if(!list2.isEmpty())
					{while(!list2.contains(st)) 
					 {int pl = rand.nextInt(49); st = String.valueOf(pl);}
				     if(list2.contains(st))
				      {int ind = list2.indexOf(st); list2.remove(ind); newPlace = st; sum += 20; soldTickets2++; destination = "Plovdiv"; price = 20;}}
				     	else {flag = false;};break;
		case '3' : if(!list3.isEmpty())
					{while(!list3.contains(st)) 
					 {int pl = rand.nextInt(35); st = String.valueOf(pl);}
				     if(list3.contains(st))
				   	  {int ind = list3.indexOf(st); list3.remove(ind); newPlace = st; sum += 15; soldTickets3++; destination = "Blagoevgrad"; price = 15;}}
						else {flag = false;};break;
		case '4' : if(!list4.isEmpty())
					{while(!list4.contains(st)) 
					 {int pl = rand.nextInt(45); st = String.valueOf(pl);}
				     if(list4.contains(st))
				   	  {int ind = list4.indexOf(st); list4.remove(ind); newPlace = st; sum += 22; soldTickets4++; destination = "Ruse"; price = 22;}}
				   		else {flag = false;};break;
		case '5' : if(!list5.isEmpty())
					{while(!list5.contains(st)) 
					 {int pl = rand.nextInt(37); st = String.valueOf(pl);}
				     if(list5.contains(st))
				   	  {int ind = list5.indexOf(st); list5.remove(ind); newPlace = st; sum += 18; soldTickets5++; destination = "Vidin"; price = 18;}}
						else {flag = false;};break;
		case '6' : if(!list6.isEmpty())
					{while(!list6.contains(st)) 
					 {int pl = rand.nextInt(43); st = String.valueOf(pl);}
					 if(list6.contains(st))
					  {int ind = list6.indexOf(st); list6.remove(ind); newPlace = st; sum += 18; soldTickets6++; destination = "Pleven"; price = 18;}}
						else {flag = false;};break;	
		}
		if(flag == true)
		{
			try
			{
				Thread.currentThread();
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			out.println(newPlace);
			String content = "";
			File f = new File("F:/Java/workspace/SJTProj/src/places.txt");
			try 
			{
				content = new Scanner(f).useDelimiter("\\Z").next();
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			String newCont = "Place " + newPlace + " to " + destination +  " on price " + price + " is successfully booked.\n";
			content += newCont;
			PrintWriter pw;
			try 
			{
				pw = new PrintWriter(f, "UTF-8");
				pw.println(content);
				pw.flush();
				pw.close();
			} 
			catch (FileNotFoundException | UnsupportedEncodingException e) 
			{
				e.printStackTrace();
			}	
		}
		else
		{
			try
			{
				Thread.currentThread();
				Thread.sleep(200);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			out.println("0");
		}
		try 
		{
			socket.close();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		lock.unlock();
	}
	/*public static String bookPlace(List<String> ls, String st, String np, 
			String dest, int p, boolean f, Random r)
	{
		if(!ls.isEmpty())
		{
			while(!ls.contains(st)) 
			{
				int pl = r.nextInt(51); 
				st = String.valueOf(pl);
			}
			if(ls.contains(st))
			{
				int ind = ls.indexOf(st); 
				ls.remove(ind); 
				np = st; 
				sum += p;  
				destination = dest; 
				price = p;
			}
		}
		else
		{
			f = false;
		}
		return np;
	}*/
}
