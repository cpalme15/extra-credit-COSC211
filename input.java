import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.*;
public class input extends JPanel implements ActionListener{
	int selaccpin;
	public static Panel keypad=new Panel();
	private JButton[] barr1=new JButton[12];
	private JButton[] barr2=new JButton[3];
	private String[] caption_Keypad={"7","8","9","4","5","6","1","2","3","0",".","CE"};
	private String[] caption_Options={"A","B","C"};
	int selacccus;
	static boolean err=false;
	static double selaccbal;
	String accty;
	private static int Screennum=1;
	public Account acc1=new Account(123456,2246,new Checking(),new Savings());
	public Account acc2=new Account(654321,2563,new Checking(),new Savings());
	public Account acc3=new Account(135790,3456,new Checking(),new Savings());
	public static Account selected=new Account();
	public ObjectInputStream ois=null;//writing to binary files
	public ObjectOutputStream oos=null;
	
	public static Bank_Panel bp;
	public input()
	{
		bp=new Bank_Panel();
		this.setLayout(new BorderLayout());
		this.add(bp, BorderLayout.CENTER);
		for(int i=0;i<barr1.length;i++)
		{
			barr1[i]=new JButton(caption_Keypad[i]);
			keypad.add(barr1[i]);
			barr1[i].setFont(bp.inputFont);;
			barr1[i].addActionListener(this);// listen to buttons and find action in this class
		}
		for(int i=0;i<barr2.length;i++)
		{
			barr2[i]=new JButton(caption_Options[i]);
			bp.Options.add(barr2[i]).setFont(bp.buttonFont);
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
		case 1: bp.big_Display.setText("");
		bp.big_Display.setText("Enter customer number: "+"\u2192"+"\n" +"press A when done\n"+"A=OK\n"+"C= exit program completely");
		break;
		case 2:
		bp.big_Display.setText(" ");
		bp.big_Display.setText("Enter PIN "+"\u2192\n"+"A=OK\n"+"C= cancel");
		break;
		case 3: 
			bp.big_Display.setText("");
			bp.big_Display.setText("Select Account"+"\u2192\n"+"A=Checking\n"+"B=Savings\n"+"C=Exit");
			break;
		case 4:
			bp.big_Display.setText("");
			if(err==true){
			bp.big_Display.setText("Balance="+selaccbal+"\n"+"Enter amount of and select transaction\n"+"A=Withdraw\n"+"B=Deposit\n"+"C=Cancel\n"+"ERROR OVERDRAFT "
					+ "ENTER VALID VALUE AND TRY AGAIN, \n OVERDRAFT LIMIT IS $50");
			err=false;
			}
			else
			{
				bp.big_Display.setText("Balance="+selaccbal+"\n"+"Enter amount of and select transaction\n"+"A=Withdraw\n"+"B=Deposit\n"+"C=Cancel");
			}
			break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String x = e.getActionCommand();
		try {
			ois=new ObjectInputStream(new FileInputStream("Accounts.dat"));
			acc1=(Account)ois.readObject();
			acc2=(Account)ois.readObject();
			acc3=(Account)ois.readObject();
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
			switch(Screennum){
			case 1:
				try{if(acc1.getCusnum()==Integer.parseInt(bp.input_Display.getText())||acc2.getCusnum()==Integer.parseInt(bp.input_Display.getText())||acc3.getCusnum()==Integer.parseInt(bp.input_Display.getText()))
				{	selacccus=Integer.parseInt(bp.input_Display.getText());
					Screennum=2;
					Menu();
					clear();
					return;
				}
				else
				{
					JOptionPane jp =new JOptionPane();
					jp.showMessageDialog(bp,"Incorrect Customer Number try again or exit.");
				}
				
			   
			    }
				catch(Exception e2)
				{
					JOptionPane jp =new JOptionPane();
					jp.showMessageDialog(bp,"Please input a customer number or exit.");
				}
				 break;
			case 2:	
	try{	 if(acc1.getPin()==Integer.parseInt(bp.input_Display.getText())||acc2.getPin()==Integer.parseInt(bp.input_Display.getText())||acc3.getPin()==Integer.parseInt(bp.input_Display.getText()))
			{
			 	selaccpin=Integer.parseInt(bp.input_Display.getText());
				Screennum=3;
				Menu();
				clear();
				if(selaccpin==acc1.getPin())
				{
					selected=acc1;
				}
				else if(selaccpin==acc2.getPin())
				{
					selected=acc2;
				}
				else if(selaccpin==acc3.getPin())
				{
					selected=acc3;
				}
			}
	else{JOptionPane jp =new JOptionPane();
	jp.showMessageDialog(bp,"Incorrect customer pin please try again or exit.");}
			
	}
	catch(Exception e1)
	{
		JOptionPane jp =new JOptionPane();
		jp.showMessageDialog(bp,"Please input a pin or exit.");
	}
	break;
			case 3:
				accty="Checking";
				selaccbal=selected.getCheckacc().getBalance();
				Screennum=4;
				Menu();
			break;
			
			case 4:
				switch(accty)
				{
				case "Saving":
				  selected.getSavingacc().withdraw(Double.parseDouble(bp.input_Display.getText()));
				  selaccbal=selected.getSavingacc().getBalance();
				  Menu();
				  clear();
				break;
				case"Checking":
					selected.getCheckacc().withdraw(Double.parseDouble(bp.input_Display.getText()));
					selaccbal=selected.getCheckacc().getBalance();
						Menu();
					clear();
					break;
				}
			}
				break;
		case"C":
			switch(Screennum)
			{
			case 1: System.exit(0);break;
			case 2: Screennum=1;Menu();break;
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
				 selected.getSavingacc().deposit(Double.parseDouble(bp.input_Display.getText()));
				 selaccbal=selected.getSavingacc().getBalance();
				  Menu();
				  clear();
				break;
				case"Checking":
					selected.getCheckacc().deposit(Double.parseDouble(bp.input_Display.getText()));
					selaccbal=selected.getCheckacc().getBalance();
					Menu();
					clear();
				}
			}
			break;
		default:
			bp.input_Display.setText(bp.input_Display.getText()+x);
			break;
		}
		}
	public static void clear()
	{
		bp.input_Display.setText("");
		
	}
}
