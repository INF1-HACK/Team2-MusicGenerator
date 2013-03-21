public class Guitar
{
	//In the final version there won't be a main function, as it will be just a class, not a program	
	public static void main(String[] args)		
	{
		Guitar_String[] strings = new Guitar_String[5];		//Each note has to have its own class, depending whether we will need to re-use the note, it might be beneficial to use a static class
		int[] notes = new int[3];		//Simple array for the chord

		for(int i=0; i< strings.length; i++)		//Loops and plays 5 notes, starting with middle C
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
		}

		try
		{
			Thread.sleep(1000);			//Waits for a second before playing chords
		}
		catch(Exception ex)
		{
			;
		}

		notes[0]=523;	//C
		notes[1]=330;	//E
		notes[2]=392;	//G


		for(int i=0; i<3;i++)		//Plays the chords 3 times, with a second delay between them
		{
			try{
				playChord(notes);
				Thread.sleep(1000);
			}
			catch(Exception ex)
			{
				;
			}
		}
		
	}
//Plays notes from the array, nearly simultaneously at 50 ms delay - to simulate the delay between plucking the strings
	public static void playChord(int[] notes)			
	{
		for(int i=0; i<notes.length;i++)
		{
			Thread t = new Thread(new Guitar_String(notes[i]));		//Creates a new thread for each of the notes
			t.start();			//Plays the note

			try{
			Thread.sleep(50);		//Waits for 50ms
			}
			catch(Exception ex)
			{
				;
			}
		}
	}

}