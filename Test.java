import javax.swing.JFrame;
import javax.swing.UIManager;

public class Test {

	public static void main(String[] args) {
		JFrame jiff=new JFrame("ATM");
		input ip=new input();
		jiff.getContentPane().add(ip);
		jiff.setSize(1500,500);
		jiff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jiff.setVisible(true);
	
		

	}

}
