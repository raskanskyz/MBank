package swing;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Action.Action;
import java.awt.Label;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Action action;
	private static JPanel panel_1;


	

	public static void setAction(Action act) {
		action = act;
	}
	
	public static Action getAction(){
		
		return Main.action;
	}

	

	public Main() {
		setResizable(false);
		setTitle("Welcome To MBank Administration");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 550);
		
		final JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(254, 12, 740, 516);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		Label label = new Label("Welcome To MBank Admin System");
		label.setFont(new Font("Dialog", Font.PLAIN, 24));
		label.setAlignment(Label.CENTER);
		label.setBounds(73, 56, 584, 362);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 12, 233, 470);
		panel.add(panel_2);
		
		final JButton btnAddNewClient_1 = new JButton("Add New Client And Account");
		btnAddNewClient_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnAddNewClient_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//		btnAddNewClient_1.setBackground();
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("Add New Client To MBank");
				panel_1.add(new AddNewClientAndAccount());
				panel.validate();
				panel.repaint();
				
				
				
			}
			
		}
		);
		
		JButton btnUpdateClientDetails = new JButton("Update Client Details");
		btnUpdateClientDetails.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnUpdateClientDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("Update Client Details");
				panel_1.add(new UpdateClientDetails());
				panel.validate();
				panel.repaint();
				
			}
		});
		
		JButton btnRemoveClientAnd = new JButton("Remove Client And Account");
		btnRemoveClientAnd.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnRemoveClientAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("Remove Client");
				panel_1.add(new RemoveClient());
				panel.validate();
				panel.repaint();
			}
		});
		
		JButton btnViewClientDetails = new JButton("View Client Details");
		btnViewClientDetails.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		btnViewClientDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("MBank Client List");
				panel_1.add(new ViewClientDetails());
				panel.validate();
				panel.repaint();
				
			}
		});
		
		JButton button_3 = new JButton("View Account Details");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("MBank Account List");
				panel_1.add(new ViewAccountDetails());
				panel.validate();
				panel.repaint();
				
			}
		});
		button_3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		
		JButton btnViewClientDeposits = new JButton("View Client Deposits");
		btnViewClientDeposits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("MBank Deposit List");
				panel_1.add(new ViewClientDeposits());
				panel.validate();
				panel.repaint();
				
			}
		});
		btnViewClientDeposits.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		
		JButton btnViewClientActivities = new JButton("View Client Activities");
		btnViewClientActivities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("Client Activities");
				panel_1.add(new ViewClientActivities());
				panel.validate();
				panel.repaint();
				
			}
		});
		btnViewClientActivities.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		
		JButton btnUpdateSystemProperty = new JButton("View & Update System Property");
		btnUpdateSystemProperty.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 11));
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		panel_2.add(btnAddNewClient_1);
		panel_2.add(btnUpdateClientDetails);
		panel_2.add(btnRemoveClientAnd);
		panel_2.add(btnViewClientDetails);
		panel_2.add(button_3);
		panel_2.add(btnViewClientDeposits);
		panel_2.add(btnViewClientActivities);
		panel_2.add(btnUpdateSystemProperty);
		btnUpdateSystemProperty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_1.removeAll();
				panel_1.setLayout(new GridLayout(1,1));
				setTitle("MBank Properties");
				panel_1.add(new UpdateSystemProperty());
				panel.validate();
				panel.repaint();
				
			}
		});
		
		
	}
	public static JPanel getPanel_1() {
		return panel_1;
	}
}
