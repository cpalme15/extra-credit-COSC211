import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		JFrame jiff=new JFrame("ATM");
		input ATM1=new input();
		jiff.getContentPane().add(ATM1);
		jiff.setSize(1500,500);
		jiff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jiff.setVisible(true);
		

	}

}
