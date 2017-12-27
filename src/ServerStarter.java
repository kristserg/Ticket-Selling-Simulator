import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerStarter 
{	
	private static List<String> fillTheList(List<String> s, int nos)
	{
		for(int i = 1; i <= nos; i++)
		{
			s.add(String.valueOf(i));
		}
		return s;
	}
	
	public static void main(String[] args) throws IOException
	{
		File f = new File("F:/Java/workspace/SJTProj/src/destinations.txt");
		String content = new Scanner(f).useDelimiter("//Z").next();
		String[] contCheck = content.split("-");
		int i = contCheck.length;
		int count = 0;
		for(int j = 0; j < i; j++)
		{
			if(j % 2 == 1)
			{
				if(contCheck[j].compareTo(String.valueOf(40)) < 0)
				{
					count++;
				}
			}
		}
		//System.out.println(count);
		if(i != 13 && count < 2)
		{
			System.out.println("Invalid data!");
			System.exit(-1);
		}
		int[] numOfSeats = {0, 0, 0, 0, 0, 0};
		int k = 0;
		for(int j = 0; j < i; j++)
		{
			if(j % 2 == 1)
			{
				numOfSeats[k] = Integer.valueOf(contCheck[j]);
				k++;
			}
		}
		List<String> list1 = Collections.synchronizedList(new ArrayList<String>(numOfSeats[0]));
		List<String> list2 = Collections.synchronizedList(new ArrayList<String>(numOfSeats[1]));
		List<String> list3 = Collections.synchronizedList(new ArrayList<String>(numOfSeats[2]));
		List<String> list4 = Collections.synchronizedList(new ArrayList<String>(numOfSeats[3]));
		List<String> list5 = Collections.synchronizedList(new ArrayList<String>(numOfSeats[4]));
		List<String> list6 = Collections.synchronizedList(new ArrayList<String>(numOfSeats[5]));
		list1 = fillTheList(list1, numOfSeats[0]);
		list2 = fillTheList(list2, numOfSeats[1]);
		list3 = fillTheList(list3, numOfSeats[2]);
		list4 = fillTheList(list4, numOfSeats[3]);
		list5 = fillTheList(list5, numOfSeats[4]);
		list6 = fillTheList(list6, numOfSeats[5]);

		ServerSocket s = new ServerSocket(1234);
		System.out.println("Server Started");
		try 
		{
			while(true) 
			{
				Socket socket = s.accept();
				try 
				{
					new Server(socket, list1, list2, list3, list4, list5, list6);
				}
				catch(IOException e) 
				{
					socket.close();
				}
				int sum = Server.getSum();
				int tickets1 = Server.getSoldTickets1();
				int tickets2 = Server.getSoldTickets2();
				int tickets3 = Server.getSoldTickets3();
				int tickets4 = Server.getSoldTickets4();
				int tickets5 = Server.getSoldTickets5();
				int tickets6 = Server.getSoldTickets6();
				System.out.println("Prodadeni sa bileti na stoinost: " + sum);
				System.out.println("There are " + tickets1 + " sold tickets to Varna.");
				System.out.println("There are " + tickets2 + " sold tickets to Plovdiv.");
				System.out.println("There are " + tickets3 + " sold tickets to Blagoevgrad.");
				System.out.println("There are " + tickets4 + " sold tickets to Ruse.");
				System.out.println("There are " + tickets5 + " sold tickets to Vidin.");
				System.out.println("There are " + tickets6 + " sold tickets to Pleven.");
			}
		}
		finally 
		{
			s.close();
		}
	}
}
