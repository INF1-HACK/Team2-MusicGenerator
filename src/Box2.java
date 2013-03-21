import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;

public class Box2 {
	
	private static JButton playButton;
	private static JButton loadButton;
	
  public static void main(String args[]) {
    JFrame horizontalFrame = new JFrame("Horizontal");
    horizontalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    Box horizontalBox = Box.createHorizontalBox();
    
    //horizontalBox.add(new JTextField("Choose"));
    
    playButton = new JButton("Play");
    horizontalBox.add(playButton);
    
    playButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e)
        {
            System.out.println("You clicked the play button");
        }
    });
    
    horizontalBox.add(Box.createVerticalStrut(30));
    
    loadButton = new JButton("Load");
    horizontalBox.add(loadButton);
    
    loadButton.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e)
        {
            System.out.println("You clicked the load button");
        }
    });
    
    horizontalFrame.add(horizontalBox, BorderLayout.CENTER);
    horizontalFrame.setSize(150, 150);
    horizontalFrame.setVisible(true);   
  }
  

}