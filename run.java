import swing.Login;
import swing.Main;
import Concrete.MBankST;

@SuppressWarnings("unused")
public class run {
	public static void main(String[] args) {
		MBankST.instance();
		Login log = new Login(new Main());

	}// main

}// class
