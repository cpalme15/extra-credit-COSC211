import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bank_Panel extends JPanel implements ActionListener{
	
	public static Panel Inputkey=new Panel();
	public static Panel Display=new Panel();
	public static Panel keynInput=new Panel();
	public static Panel Options=new Panel();
	public static JTextField Top_area=new JTextField();
	private static JTextField bottom_Area=new JTextField();
	public static JTextField input_Display=new JTextField(10);
	public static  JTextArea big_Display=new JTextArea(10,0);
	public static Font buttonFont;
	public static Font inputFont;
	public static Font DisplayFont;
	
	public Bank_Panel()
	{
	super();
	buttonFont = input_Display.getFont().deriveFont(Font.PLAIN, 30f);
	 inputFont=input_Display.getFont().deriveFont(Font.PLAIN,25f);
	 DisplayFont=big_Display.getFont().deriveFont(Font.PLAIN,35f);
	input_Display.setFont(inputFont);
	Top_area.setFont(inputFont);
	bottom_Area.setFont(inputFont);
	big_Display.setFont(DisplayFont);
	this.setLayout(new BorderLayout());// setting it to border layout
	Options.setLayout(new GridLayout(3,1));
	keynInput.setLayout(new BorderLayout());
	Inputkey.setLayout(new GridLayout(1,1));
	Display.setLayout( new GridLayout(1,1));
	keynInput.add(input_Display,BorderLayout.NORTH);
	input.keypad.setLayout(new GridLayout(4,3));
	this.add(keynInput,BorderLayout.LINE_START);
	keynInput.add(input.keypad,BorderLayout.CENTER);
	this.add(Display,BorderLayout.CENTER);
	Top_area.setBackground(new Color(245,245,220));
	Top_area.setText("Applet");
	Top_area.setEditable(false);
	bottom_Area.setBackground(new Color(245,245,220));
	bottom_Area.setEditable(false);
	bottom_Area.setText("Applet Started");
	this.add(bottom_Area,BorderLayout.SOUTH);
	this.add(Top_area,BorderLayout.NORTH);
	input_Display.setEditable(false);
	
	this.add(Options,BorderLayout.LINE_END);
	
	JScrollPane scroll= new JScrollPane(big_Display);
	Display.add(big_Display);
	input_Display.setBackground(Color.WHITE);;
	
	big_Display.setEditable(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
}
