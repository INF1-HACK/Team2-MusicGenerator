public class Note {
char step; //musical step from A to G
int octave; //octave
int alter; //ranges from -1 to 1 whether the note is flat, natural or sharp
int duration; //length of the note
public Note() {

}
//getters and setters
public void setStep(char ch) {
step = ch;
}
public void setOctave(int i) {
octave = i;
}
public void setAlter(int i) {
alter = i;
}
public void setDuration(int i) {
duration = i;
}
public String toString() {
return (step + " " + Integer.toString(octave) + " " + Integer.toString(alter) + " " + Integer.toString(duration));
}
}