
// File written by Andreas Almgren and tested by André Frisk and Linnéa Olsson 

public class BiggestCity
{
	public static void main(String[] args)
	{
		int cOrAcode[] = new int[48326];
		int year[] = new int[48326];
		int sexCode[] = new int[48326];
		int cCode[] = new int[48326];
		double val[] = new double[48326]; 
			
		for(int i = 0; i < 48326; i++)
		{
			cOrAcode[i] = StdIn.readInt();
			year[i] = StdIn.readInt();
			sexCode[i] = StdIn.readInt();
			cCode[i] = StdIn.readInt();
			val[i] = StdIn.readDouble(); 
		}
		int a[] = new int[100]; 
		int b[] = new int[100];
		for(int i = 0; i < a.length; i++) 
		{
			a[i] = 0; 
			b[i] = 0;
		}

		for(int i = 0; i < 48326; i += 1)
		{
			if(sexCode[i] == 0)  
			{
				if(a[year[i] % 100] < val[i])
				//System.out.println(a[year[i]]);
				//System.out.println(a[year[i]] % 100);							
				{
					a[year[i] % 100] = (int) val[i];
					b[year[i] % 100] = i;

				}
	
			}
		}
		System.out.println("Lista över städer som hade det största antalet invånare över alla år som mätningarna berör:");

		for(int i = 0; i < 100; i++)
		{
			if(year[b[i]] % 100 > 17 && a[i] != 0)
			{
				System.out.println("Landets kod: " + (int) cOrAcode[b[i]] + " Stadens kod: " + (int) cCode[b[i]] + " År: 19" + i + " Antal invånare: " + a[i]);
			}
		}
		for(int i = 0; i < 100; i++)
		{

			if(year[b[i]] % 100 < 10 && a[i] != 0)
			{
				System.out.println("Landets kod: " + (int) cOrAcode[b[i]] + " Stadens kod: " + (int) cCode[b[i]] + " År: 200" + i + " Antal invånare: " + a[i]);
			}

			if(year[b[i]] % 100 > 10 && year[b[i]]%100 < 16 && a[i] != 0)
			{
					System.out.println("Landets kod: " + (int) cOrAcode[b[i]] + " Stadens kod: " + (int) cCode[b[i]] + " År: 20" + i + " Antal invånare: " + a[i]);
			}
		}
	}
}
