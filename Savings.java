import javax.swing.JOptionPane;

public class Savings extends Account implements AccountTemp{
private double balance;
private double interest_Rate;
private double overdraft_Max;
	public Savings() 
	{
		super();
		balance=0.0;
		interest_Rate=0.05;
		overdraft_Max=0.0;
	}
	public Savings(double d)
	{	super();
		balance=d;
		interest_Rate=0.05;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterest_Rate() {
		return interest_Rate;
	}
	public void setInterest_Rate(double interest_Rate) {
		this.interest_Rate = interest_Rate;
	}
	@Override
	public void deposit(double amt) 
	{
		balance=balance+amt;
		double Interest=amt*interest_Rate;
		balance+=Interest;
	}
	@Override
	public void withdraw(double amt) {
		double balanceTemp=balance;
		balance=balance-amt;
		if(balance<overdraft_Max)
		{balance=balanceTemp;
			JOptionPane jp =new JOptionPane();
			jp.showMessageDialog(input.bp,"Insufficient Funds");
		}
	else
		balance=balance;
	}
	
	
	

}
