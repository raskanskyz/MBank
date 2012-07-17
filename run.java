import swing.Login;
import swing.Main;
import Concrete.MBankST;

@SuppressWarnings("unused")
public class run {
	public static void main(String[] args) {
        System.out.println("Starting MBank...");
		MBankST.instance();
		Login log = new Login(new Main());

	}// main

}// class
