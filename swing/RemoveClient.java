package swing;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Action.adminAction;

public class RemoveClient extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField client_id;

	public boolean isNumeric(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			// s is not numeric
			return false;
		}
	}
	
	/**
	 * Create the panel.
	 */
	public RemoveClient() {
		setBounds(254, 12, 740, 510);
		setLayout(null);

		JLabel client_id_1 = new JLabel("Enter A client Id To Remove:");
		client_id_1.setBounds(31, 27, 296, 33);
		add(client_id_1);

		client_id = new JTextField();
		client_id.setBounds(360, 27, 370, 33);
		add(client_id);
		client_id.setColumns(10);

		JLabel lblNoteThatAfter = new JLabel("");
		lblNoteThatAfter.setBounds(0, 53, 370, 33);
		add(lblNoteThatAfter);

		JLabel label_1 = new JLabel("");
		label_1.setBounds(370, 53, 370, 33);
		add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(0, 106, 370, 33);
		add(label_2);

		JLabel label_3 = new JLabel("");
		label_3.setBounds(370, 106, 370, 33);
		add(label_3);

		JLabel label_4 = new JLabel("");
		label_4.setBounds(0, 159, 370, 33);
		add(label_4);

		JLabel label_5 = new JLabel("");
		label_5.setBounds(370, 159, 370, 33);
		add(label_5);

		JLabel label_6 = new JLabel("");
		label_6.setBounds(0, 212, 370, 33);
		add(label_6);

		JLabel label_7 = new JLabel("");
		label_7.setBounds(370, 212, 370, 33);
		add(label_7);

		JLabel label_8 = new JLabel("");
		label_8.setBounds(0, 265, 370, 33);
		add(label_8);

		JLabel label_9 = new JLabel("");
		label_9.setBounds(370, 265, 370, 33);
		add(label_9);

		JLabel label_10 = new JLabel("");
		label_10.setBounds(0, 318, 370, 33);
		add(label_10);

		JLabel label_11 = new JLabel("");
		label_11.setBounds(370, 318, 370, 33);
		add(label_11);

		JLabel label_12 = new JLabel("");
		label_12.setBounds(0, 371, 370, 33);
		add(label_12);

		JLabel label_13 = new JLabel("");
		label_13.setBounds(370, 371, 370, 33);
		add(label_13);

		JLabel label_14 = new JLabel("");
		label_14.setBounds(0, 424, 370, 33);
		add(label_14);

		JLabel label_15 = new JLabel("");
		label_15.setBounds(370, 424, 370, 33);
		add(label_15);

		JLabel label_16 = new JLabel("");
		label_16.setBounds(0, 477, 370, 33);
		add(label_16);

		JButton label_17 = new JButton("Review Client Details");
		label_17.setBounds(360, 468, 370, 33);

		label_17.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				adminAction action = (adminAction) Main.getAction();
			
				
				if (	client_id.getText().equals("") ||(isNumeric(client_id.getText()))==false
						||action.viewClientDetails(Long.parseLong(client_id.getText())).getClient_id()==null
						||action.viewClientDetails(Long.parseLong(client_id.getText())).getClient_id()==0
						)
						 {
					JOptionPane.showMessageDialog((Component) e.getSource(),
							"The Selected Client Does Not Exist In MBank."
									+ "\n Please Select A Valid Client Id.");
				} else {
					Main.getPanel_1().removeAll();
					Main.getPanel_1().setLayout(new GridLayout(1, 1));
					Main.getPanel_1().add(
							new ReviewRemovePanel(Long.parseLong(client_id
									.getText())));
					Main.getPanel_1().validate();
					Main.getPanel_1().repaint();
				}
			}
		});

		add(label_17);
	}

}
