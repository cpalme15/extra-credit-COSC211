
public class Savings extends Account{
private double balance;
private double interest_Rate;
	public Savings() 
	{
		super();
		balance=0.0;
		interest_Rate=0.5;
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
	
	
	

}
