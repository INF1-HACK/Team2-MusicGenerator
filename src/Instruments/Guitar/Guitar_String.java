import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

public class Guitar_String implements Runnable			//Has to be of Runnable interface to be run on a separate thread
{
	 int fs = 16*1024;			//The default frequency at 16kHz
	 int n = 80000;			//How many bytes for each note
	 int d = 100;		//The random array which is used to initialize the standing wave



	public Guitar_String(int freq)		//Class constructor which takes frequency as input
	{
		fs = (d+1) * freq /2;		//The algorithm will produce a note at 2*freq/(d+1) 
	}

	public  void play(SourceDataLine line, byte[] note, int ms)		//Writes the note to the line for given time in ms
	{
		int length = ms*10;							//Has to be multiplied by 10 so that the time is in milliseconds 
		int count = line.write(note,0,length);
	}

	public  byte[] createDelay(int length)		//Creates random byte array
	{
		byte[] b = new byte[length];
		Random r = new Random();

		for(int i=0;i<length;i++)
			b[i]=(byte)(2*r.nextInt(d)-1);

		return b;
	}
	
	public  byte[] karplus(byte[] delay, int length)		//The main algorithm where the standing wave is generated, it is also possible to extend to get better sound
	{
		length+=delay.length;
		int N = delay.length;
		byte[] b = new byte[length];

		for(int i=0; i<delay.length; i++)			//Writes the random delay array to the stating wave
			b[i] = delay[i];

		for(int i=delay.length; i< length; i++)		
			{
				if(i-(N+1)==-1)			//It's assumed if the pointer is -1 the value is 0
					b[i]=0;
				else
					b[i]=(byte)((b[i-N]+b[i-(N+1)])/2);		//Using the previous bytes it generates new ones
				//System.out.println(b[i]);
			}

		return b;
	}

	public void pluck() throws LineUnavailableException			//Plays the actual note 
	{
		final AudioFormat af = new AudioFormat(fs, 8, 1,true, true);		//Creates new audio player at freq fs and at 8 bits

		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		line.open(af, fs);		//Opens the line
		line.start();			//Allows write access to the line
		
		byte[] note =karplus(createDelay(d), n);		//Creates the note

		play(line, note, 1000);		//Writes the note to the line
		play(line, new byte[2*fs], 10);		//Adds an empty byte array so that the notes won't clash
		
		line.drain();		//Plays whatever is on the line
		line.close();		//Closes the line and frees up the memory

	}

	public void run()		//Entry point of the thread
	{
		try
		{
			pluck();
		}
		catch(Exception ex)
		{
			;
		}
	}

}