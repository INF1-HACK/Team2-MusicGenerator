public class Guitar
{
	private Note[] notes;

	//In the final version there won't be a main function, as it will be just a class, not a program	
	public void Play()		
	{
		Guitar_String[] strings = new Guitar_String[notes.length];		//Each note has to have its own class, depending whether we will need to re-use the note, it might be beneficial to use a  class
			//Simple array for the chord

		for(int i=0; i< notes.length; i++)		//Loops and plays 5 notes, starting with middle C
		{
			strings[i] = new Guitar_String(notes[i].getFrequency());
			try
			{
				strings[i].pluck();
			}
			catch (Exception ex)
			{
				;
			}			
		}	
	}

	public Guitar(Note[] _notes)
	{
		notes = _notes;
	}


//Plays notes from the array, nearly simultaneously at 50 ms delay - to simulate the delay between plucking the strings
	public  void playChord(int[] notes)			
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