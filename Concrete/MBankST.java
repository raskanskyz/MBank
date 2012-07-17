package Concrete;

import java.awt.Component;
import java.util.*;

import javax.swing.JOptionPane;

import swing.Main;
import Thread.depositThread;
import Action.*;
import dbManagers.ClientDBManager;
import dbManagers.ClientManager;
import dbManagers.PropertiesDBManager;
import dbManagers.PropertiesManager;
import dtos.ClientDto;

public class MBankST {
	public static Map<String, String> m = null;
	private static MBankST mbst = null; // 1
	private Component parent;
	private Component frame;

	// -----------------------------------------------
	private MBankST() { // 2
		System.out.println("MBankST.CTOR()>>");
		PropertiesManager pm = new PropertiesDBManager();
		Map<String, String> m = pm.selectAllProperties();
		MBankST.m = m;
		depositThread thread = new depositThread();
		thread.start();
	}// CTOR

	// -----------------------------------------------

	public static MBankST instance() { // 3
		if (mbst == null) {
			mbst = new MBankST();
		}// if
		return mbst;
	}// instance

	public Action login(long username, String password) {

		ClientManager cm = new ClientDBManager();
		ClientDto dto = new ClientDto();
		dto.setClient_id(cm.selectClient(username).getClient_id());
		dto.setPassword(cm.selectClient(username).getPassword());
		dto.setClient_name(cm.selectClient(username).getClient_name());

		if (username == (dto.getClient_id())
				&& password.equals(dto.getPassword())) {
			System.out.println("Welcome " + dto.getClient_name() + "!");
			Action a = new clientAction();
			return a;

		}// if-client
		else {

			System.err
					.println("There Was A Problem Logging In, "
							+ "\nPlease Varify User Name And Password And Try Again !!");

			return null;

		}// inner else

	}// login()

	public Action login(String username, String password) {

		if (username.equals( m.get("admin_username".trim()))
				&& password.equals( m.get("admin_password".trim()))) {
			Action a = new adminAction();
			System.out.println("***ADMIN DETECTED***");

			Main frame = new Main();
			frame.setVisible(true);
			return a;

		}// if-admin
		else {

			JOptionPane.showMessageDialog(parent,
					"Invalid user name or password", "Error Message",
					JOptionPane.ERROR_MESSAGE);
			frame.setVisible(true);

			System.err
					.println("There Was A Problem Logging In, "
							+ "\nPlease Varify User Name And Password And Try Again !!");
			return null;
		}// else
	}// Login
}// MBankST

