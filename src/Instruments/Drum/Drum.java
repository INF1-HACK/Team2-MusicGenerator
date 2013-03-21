import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

public class Drum
{

	  int fs = 16*1024;			//The default frequency at 16kHz
	  int n = 80000;			//How many bytes for each note
	  int d = 800;		//The random array which is used to initialize the standing wave
	  Note[] notes;
	//p -> size of the random wavetable 150-500
	//b -> blend factor 0.0-1.0
	//rand -> the random wavetable
	private  byte[] produceSound(double b, byte[] rand, int size)
	{
		byte[] sound = new byte[size];
		int t = rand.length;
		for(int i =0; i<rand.length; i++)
			sound[i] = rand[i];

		Random r = new Random();

		for(int i = t; i<size-1; i++)
		{
			double d= r.nextDouble();
			if(d>b)
				sound[i] =(byte) (0.5 * (sound[i-t] + sound[i-t+1]));
			else
				sound[i] =(byte) ((-0.5) * (sound[i-t] + sound[i-t+1]));
		}

		return sound;

	}

	/*Creates random byte array*/
	public  byte[] createDelay(int length)		
	{
		byte[] b = new byte[length];
		Random r = new Random();

		for(int i=0;i<length;i++)
			b[i]=(byte)(2*r.nextInt(length)-1);

		return b;
	}

	public  void play(SourceDataLine line, byte[] note, int ms)		
	{
		int length = ms*10;							/*Has to be multiplied by 10 so that the time is in milliseconds */
		int count = line.write(note,0,length);
	}

	/* Plays the actual note*/
	public  void hit(Note _note) throws LineUnavailableException		
	{
		int freq = _note.getFrequency();
		int duration = _note.getDuration();
		final AudioFormat af = new AudioFormat(freq, 8, 1,true, true);		/*Creates new audio player at freq fs and at 8 bits*/

		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		line.open(af, fs);		/*Opens the line*/
		line.start();			/*Allows write access to the line*/
		
		byte[] note =produceSound(0.5,createDelay(d), n);		/*Creates the note*/

		play(line, note, duration);		/*Writes the note to the line*/
		play(line, new byte[2*fs], 10);		/*Adds an empty byte array so that the notes won't clash*/
		
		line.drain();		/*Plays whatever is on the line*/
		line.close();		/*Closes the line and frees up the memory*/

	}

	public Drum(Note[] _notes)
	{
		notes = _notes;
	}

	public void Play()
	{
		try
		{
			for(int i=0; i<notes.length; i++)
				hit(notes[i]);
		}
		catch(LineUnavailableException ex)
		{
			;
		}
	}
}