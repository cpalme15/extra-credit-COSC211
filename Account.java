import java.io.Serializable;

public class Account implements Serializable {
int cusnum;
int pin;
double balance;
Savings savingacc;
Checking checkacc;
public Account() {
	super();
	
}
public Account( int cusnum, int pin, Checking checkacc,Savings savingacc) {
	super();
	this.cusnum = cusnum;
	this.pin = pin;
	this.balance=balance;
	this.checkacc=checkacc;
	this.savingacc=savingacc;
	
	
}
public int getCusnum() {
	return cusnum;
}
public void setCusnum(int cusnum) {
	this.cusnum = cusnum;
}
public int getPin() {
	return pin;
}
public void setPin(int pin) {
	this.pin = pin;
}
public Savings getSavingacc() {
	return savingacc;
}
public void setSavingacc(Savings savingacc) {
	this.savingacc = savingacc;
}
public Checking getCheckacc() {
	return checkacc;
}
public void setCheckacc(Checking checkacc) {
	this.checkacc = checkacc;
		}
}

