
// File written by Andreas Almgren and tested by André Frisk and Linnéa Olsson 

public class Decode{
	public static void main(String[] args)
	{
		
		int value = Integer.parseInt(args[0]);
		
		while(!StdIn.isEmpty())
		{
			int a = StdIn.readInt();

			if(a == value)
			{
				System.out.println(StdIn.readLine() .trim());
				break;
			}
		
			else
			{
				StdIn.readLine();
			}
		}
	}
}
