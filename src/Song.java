import java.util.ArrayList;
public class Song {
	private ArrayList<Note> notes;
	private int temp;
	private ArrayList<Beat> beats;
	private ArrayList<Chord> chords;
	
	public Song(ArrayList<Note> notes, ArrayList<Beat> beats, ArrayList<Chord> chords, int temp){
		this.notes = notes;
		this.chords = chords;
		this.beats = beats;
		this.temp = temp;
	}
	
	public void setNotes(ArrayList<Note> nList){
		notes = nList;
	}
	
	public void setBeats(ArrayList<Beat> bList){
		beats = bList;
	}
	
	public void setChords(ArrayList<Chord> chList){
		chList = chords;
	}
	
	public void setTemp(int t){
		temp = t;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public ArrayList<Chord> getChords(){
		return chords;
	}
	public ArrayList<Beat> getBeats(){
		return beats;
		
	}
	public int getTemp(){
		return temp;
	}
	public void play(){
		
	}
 
}