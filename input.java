import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class input extends JPanel implements ActionListener{
	private JButton[] barr1=new JButton[12];
	private JButton[] barr2=new JButton[3];
	private String[] caption_Keypad={"7","8","9","4","5","6","1","2","3","0",".","CE"};
	private String[] caption_Options={"A","B","C"};
	private JPanel keypad=new JPanel();
	private JPanel Inputkey=new JPanel();
	private JPanel Display=new JPanel();
	private JPanel Options=new JPanel();
	private JPanel keynInput=new JPanel();
	private JTextField input_Display=new JTextField(10);
	private JTextArea big_Display=new JTextArea(10,0);
	private JTextField Top_area=new JTextField();
	private JTextField bottom_Area=new JTextField();
	ObjectInputStream ois=null;//writing to binary files
	ObjectOutputStream oos=null;
		public input()
		{
			super();// to call the JPanel
			this.setLayout(new BorderLayout());// setting it to border layout
			 Font buttonFont = input_Display.getFont().deriveFont(Font.PLAIN, 30f);
			 Font inputFont=input_Display.getFont().deriveFont(Font.PLAIN,25f);
			 Font Displayfont=big_Display.getFont().deriveFont(Font.PLAIN,35f);
			// create grid layout for the buttons
			Options.setLayout(new GridLayout(3,1));
			keynInput.setLayout(new BorderLayout());
			Inputkey.setLayout(new GridLayout(1,1));
			Display.setLayout( new GridLayout(1,1));
			keynInput.add(input_Display,BorderLayout.NORTH);
			keypad.setLayout(new GridLayout(4,3));
			input_Display.setFont(inputFont);
			this.add(keynInput,BorderLayout.LINE_START);
			keynInput.add(keypad,BorderLayout.CENTER);
			this.add(Display,BorderLayout.CENTER);
			Top_area.setBackground(new Color(245,245,220));
			Top_area.setFont(inputFont);
			Top_area.setText("Applet");
			Top_area.setEditable(false);
			bottom_Area.setBackground(new Color(245,245,220));
			bottom_Area.setFont(inputFont);
			bottom_Area.setEditable(false);
			bottom_Area.setText("Applet Started");
			this.add(bottom_Area,BorderLayout.SOUTH);
			this.add(Top_area,BorderLayout.NORTH);
			big_Display.setFont(Displayfont);
			Display.add(big_Display);
				this.add(Options,BorderLayout.LINE_END);
				JScrollPane scroll = new JScrollPane(big_Display);
				scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				Display.add(scroll);
				scroll.setSize(10,scroll.getHeight());
				big_Display.setEditable(false);
				big_Display.setText("Enter customer number: "+"\u2192"+"\n" +"press A when done\n"+"A=OK");
			for(int i=0;i<barr1.length;i++)
			{
				barr1[i]=new JButton(caption_Keypad[i]);
				keypad.add(barr1[i]);
				barr1[i].setFont(inputFont);;
				barr1[i].addActionListener(this);// listen to buttons and find action in this class
			}
			for(int i=0;i<barr2.length;i++)
			{
				barr2[i]=new JButton(caption_Options[i]);
				Options.add(barr2[i]).setFont(buttonFont);
				barr2[i].addActionListener(this);
			}
			try {
				oos=new ObjectOutputStream(new FileOutputStream("Accounts.dat"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		String x = e.getActionCommand();
		
		switch(x)
		{
		
		case "CE": input_Display.setText("");
		x="";
		default:
			input_Display.setText(input_Display.getText()+x);
		}
		
			
	}

}
