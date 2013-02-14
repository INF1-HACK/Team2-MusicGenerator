public class Guitar
{
	
	public static void main(String[] args) 
	{
		Guitar_String[] strings = new Guitar_String[10];
		int[] notes = new int[3];
		/*for(int i=0; i< strings.length; i++)
		{
			strings[i] = new Guitar_String(330+((0+i)*6));
			try
			{
				strings[i].pluck();
			}
			catch (Exception ex)
			{
				;
			}			
		}*/
		notes[0]=523;	//C
		notes[1]=330;	//E
		notes[2]=392;	//G
		for(int i=0; i<5;i++)
		{
			try{
				playCord(notes);
				Thread.sleep(500);
			}
			catch(Exception ex)
			{
				;
			}
		}
		
	}

	public static void playCord(int[] notes)
	{
		for(int i=0; i<notes.length;i++)
		{
			Thread t = new Thread(new Guitar_String(notes[i]));
			t.start();
			try{
			Thread.sleep(50);
			}
			catch(Exception ex)
			{
				;
			}
		}
	}

}