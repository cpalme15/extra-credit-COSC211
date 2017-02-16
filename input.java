import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
public class input extends JPanel implements ActionListener{
	public static Panel keypad=new Panel();
	public static Panel Inputkey=new Panel();
	public static Panel Display=new Panel();
	public static Panel Options=new Panel();
	public static Panel keynInput=new Panel();
	public static JTextField Top_area=new JTextField();
	public static JTextField bottom_Area=new JTextField();
	public static JTextField empty=new JTextField(10);
	public static JTextField input_Display=new JTextField(10);
	public static  JTextArea big_Display=new JTextArea(10,0);
	int selaccpin;
	int selacccus;
	static boolean err=false;
	static double selaccbal;
	String accty;
	private JButton[] barr1=new JButton[12];
	private JButton[] barr2=new JButton[3];
	private String[] caption_Keypad={"7","8","9","4","5","6","1","2","3","0",".","CE"};
	private String[] caption_Options={"A","B","C"};
	private static int Screennum=1;
	public Checking checkacc1=new Checking(300.00);
	public Checking checkacc2=new Checking(400.00);
	public Checking checkacc3=new Checking(50.00);
	public Savings savingacc1=new Savings(200.00);
	public Savings savingacc2=new Savings(75.00);
	public Savings savingacc3=new Savings(25.00);
	public Account acc1=new Account(123456,2246,checkacc1,savingacc1);
	public Account acc2=new Account(654321,2563,checkacc2,savingacc2);
	public Account acc3=new Account(135790,3456,checkacc3,savingacc3);
	public static Account curr1=new Account();
	public static Account curr2=new Account();
	public static Account curr3=new Account();
	public static Account selected=new Account();
	public ObjectInputStream ois=null;//writing to binary files
	public ObjectOutputStream oos=null;
	public input()
	{super();// to call the JPanel
		Font buttonFont = input_Display.getFont().deriveFont(Font.PLAIN, 30f);
		Font inputFont=input_Display.getFont().deriveFont(Font.PLAIN,25f);
		Font Displayfont=big_Display.getFont().deriveFont(Font.PLAIN,35f);
		input_Display.setFont(inputFont);
		Top_area.setFont(inputFont);
		bottom_Area.setFont(inputFont);
		big_Display.setFont(Displayfont);
		this.setLayout(new BorderLayout());// setting it to border layout
		Options.setLayout(new GridLayout(3,1));
		keynInput.setLayout(new BorderLayout());
		Inputkey.setLayout(new GridLayout(1,1));
		Display.setLayout( new GridLayout(1,1));
		keynInput.add(input_Display,BorderLayout.NORTH);
		keypad.setLayout(new GridLayout(4,3));
		this.add(keynInput,BorderLayout.LINE_START);
		keynInput.add(keypad,BorderLayout.CENTER);
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
		Display.add(big_Display);
		this.add(Options,BorderLayout.LINE_END);
		JScrollPane scroll = new JScrollPane(big_Display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Display.add(scroll);
		scroll.setSize(10,scroll.getHeight());
		input_Display.setBackground(Color.WHITE);;
		big_Display.setEditable(false);
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
				oos.writeObject(acc1);
				oos.writeObject(acc2);
				oos.writeObject(acc3);
			oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		Menu();
	}

	public static void Menu()
	{	
		switch(Screennum)
		{
		case 1: big_Display.setText("");
		big_Display.setText("Enter customer number: "+"\u2192"+"\n" +"press A when done\n"+"A=OK");
		break;
		case 2:
		big_Display.setText(" ");
		big_Display.setText("Enter PIN "+"\u2192\n"+"A=OK");
		break;
		case 3: 
			big_Display.setText("");
			big_Display.setText("Select Account"+"\u2192\n"+"A=Checking\n"+"B=Savings\n"+"C=Exit");
			break;
		case 4:
			big_Display.setText("");
			if(err==true){
			big_Display.setText("Balance="+selaccbal+"\n"+"Enter amount of and select transaction\n"+"A=Withdraw\n"+"B=Deposit\n"+"C=Cancel\n"+"ERROR OVERDRAFT "
					+ "ENTER VALID VALUE AND TRY AGAIN, \n OVERDRAFT LIMIT IS $50");
			err=false;
			}
			else
			{
				big_Display.setText("Balance="+selaccbal+"\n"+"Enter amount of and select transaction\n"+"A=Withdraw\n"+"B=Deposit\n"+"C=Cancel");
			}
			break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String x = e.getActionCommand();
		try {
			ois=new ObjectInputStream(new FileInputStream("Accounts.dat"));
			curr1=(Account)ois.readObject();
			curr2=(Account)ois.readObject();
			curr3=(Account)ois.readObject();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch(x){
		case "CE":
			clear();
			break;
		case "A":
			if(Screennum==1)
			{
				if(curr1.getCusnum()==Integer.parseInt(input_Display.getText())||curr2.getCusnum()==Integer.parseInt(input_Display.getText())||curr3.getCusnum()==Integer.parseInt(input_Display.getText()))
				{	selacccus=Integer.parseInt(input_Display.getText());
					Screennum=2;
					Menu();
					clear();
					return;
				}
				return;
			}
			else if(Screennum==2){	
		 if(curr1.getPin()==Integer.parseInt(input_Display.getText())||curr2.getPin()==Integer.parseInt(input_Display.getText())||curr3.getPin()==Integer.parseInt(input_Display.getText()))
			{
			 	selaccpin=Integer.parseInt(input_Display.getText());
				Screennum=3;
				Menu();
				clear();
				if(selaccpin==curr1.getPin())
				{
					selected=curr1;
				}
				else if(selaccpin==curr2.getPin())
				{
					selected=curr2;
				}
				else if(selaccpin==curr3.getPin())
				{
					selected=curr3;
				}
			}
			}
			else if(Screennum==3)
			{
				accty="Checking";
				selaccbal=selected.getCheckacc().getBalance();
				Screennum=4;
				Menu();
			}
			else if(Screennum==4)
			{
				switch(accty)
				{
				case "Saving":
				  selected.getSavingacc().setBalance(selected.getSavingacc().getBalance()-Double.parseDouble(input_Display.getText()));
				  selaccbal=selected.getSavingacc().getBalance();
				  Menu();
				  clear();
				break;
				case"Checking":
					double origbal;
					
					origbal=selected.getCheckacc().getBalance();
					selected.getCheckacc().setBalance(origbal-Double.parseDouble(input_Display.getText()));
					if(selected.getCheckacc().getBalance()<=selected.getCheckacc().getOverdraft_Max())
					{
						selected.getCheckacc().setBalance(origbal);
						err=true;
					}
					else{
					selaccbal=selected.getCheckacc().getBalance();
					}
					
					Menu();
					clear();
					break;
				}
			}
				break;
		case"C":
			switch(Screennum)
			{
			case 3: 
			Screennum=1;
			Menu();
			break;
			case 4:
				Screennum=3;
				Menu();
				break;
			}
			break;
		case"B":
			if(Screennum==3)
			{
				accty="Saving";
				selaccbal=selected.getSavingacc().getBalance();
				Screennum=4;
				Menu();
			}
			else if(Screennum==4)
			{
				switch(accty){
			case "Saving":
				double interest;
				
				  selected.getSavingacc().setBalance(selected.getSavingacc().getBalance()+Double.parseDouble(input_Display.getText()));
				  interest=(selected.getSavingacc().getBalance()*selected.getSavingacc().getInterest_Rate());
				  selected.getSavingacc().setBalance(selected.getSavingacc().getBalance()+interest);
				  
				  selaccbal=selected.getSavingacc().getBalance();
				  Menu();
				  clear();
				break;
				case"Checking":
					selected.getCheckacc().setBalance(selected.getCheckacc().getBalance()+Double.parseDouble(input_Display.getText()));
					selaccbal=selected.getCheckacc().getBalance();
					Menu();
					clear();
				}
			}
			break;
		default:
			input_Display.setText(input_Display.getText()+x);
			break;
		}
		}
	public static void clear()
	{
		input_Display.setText(empty.getText());
		
	}
}
