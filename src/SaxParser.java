import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParser extends DefaultHandler {
List<Note> noteL;
String musicXmlFileName;
String tmpValue;
Note noteTmp;
public SaxParser(String musicXmlFileName) {
this.musicXmlFileName = musicXmlFileName;
noteL = new ArrayList<Note>();
parseDocument();
printDatas();
}
private void parseDocument() {
//parse
SAXParserFactory factory = SAXParserFactory.newInstance();
try {
SAXParser parser = factory.newSAXParser();
parser.parse(musicXmlFileName, this);
} catch (ParserConfigurationException e) {
System.out.println("ParserConfig error");
} catch (SAXException e) {
System.out.println("SAXException: xml not well formed");
} catch (IOException e) {
System.out.println("IO error");
}
}
private void printDatas() {
for(Note tmpN : noteL) {
System.out.println(tmpN.toString());
}
}
@Override
public void startElement(String s, String s1, String elementName, Attributes attribute) throws SAXException {
//if current element is note, create new note
//clear tmpValue at the start of each element
if(elementName.equalsIgnoreCase("note")){
noteTmp = new Note();
}
}
@Override
public void endElement(String s, String s1, String element) throws SAXException {
if(element.equals("note")) {
noteL.add(noteTmp);
}
if(element.equals("step")) {
noteTmp.setStep(tmpValue.toCharArray()[0]);
if(tmpValue.length() > 1) {
switch(tmpValue.toCharArray()[1]) {
case '#': noteTmp.setAlter(1);
case 'b': noteTmp.setAlter(-1);
}
} else {
noteTmp.setAlter(0);
}
}
if(element.equals("octave")) {
noteTmp.setOctave(Integer.parseInt(tmpValue));
}
if(element.equals("duration")) {
noteTmp.setDuration(Integer.parseInt(tmpValue));
}
}
@Override
public void characters(char[] ac, int i, int j) throws SAXException {
tmpValue = new String(ac, i, j);
}

public static void main(String[] args) {
new SaxParser("test.xml");
}
}