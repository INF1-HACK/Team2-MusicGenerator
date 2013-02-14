import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Random;

public class Guitar_String implements Runnable
{
	 int fs = 16*1024;
	 int n = 80000;
	 int d = 100;

	public  void play(SourceDataLine line, byte[] note, int ms)
	{
		int length = ms*10;
		int count = line.write(note,0,length);
	}
	public  byte[] createDelay(int length)
	{
		byte[] b = new byte[length];
		Random r = new Random();
		for(int i=0;i<length;i++)
			b[i]=(byte)(2*r.nextInt(d)-1);

		return b;
	}
	
	public  byte[] karplus(byte[] delay, int length)
	{
		length+=delay.length;
		int N = delay.length;
		byte[] b = new byte[length];
		for(int i=0; i<delay.length; i++)
			b[i] = delay[i];

		for(int i=delay.length; i< length; i++)
			{
				if(i-(N+1)==-1)
					b[i]=0;
				else
					b[i]=(byte)((b[i-N]+b[i-(N+1)])/2);
				//System.out.println(b[i]);
			}

		return b;
	}

	public Guitar_String(int freq)
	{
		fs = (d+1) * freq /2;
	}

	public  void pluck() throws LineUnavailableException
	{
		final AudioFormat af = new AudioFormat(fs, 8, 1,true, true);

		SourceDataLine line = AudioSystem.getSourceDataLine(af);
		line.open(af, fs);
		line.start();	
		
			byte[] note =karplus(createDelay(d), n);
			play(line, note, 1000);
			play(line, new byte[2*fs], 10);
		

		line.drain();
		line.close();

	}

	public void run()
	{
		try{


		pluck();
	}
	catch(Exception ex)
	{
		;
	}
	}

}