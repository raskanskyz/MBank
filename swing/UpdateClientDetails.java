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
import dtos.ClientDto;

public class UpdateClientDetails extends JPanel {
	/**
	 * 
	 */

	public boolean isNumeric(String input) {
		try {
			Long.parseLong(input);
			return true;
		} catch (NumberFormatException e) {
			// s is not numeric
			return false;
		}
	}

	private static final long serialVersionUID = 1L;
	private JTextField clientLoaderField;
	adminAction action = (adminAction) Main.getAction();
	ClientDto dto;

	/**
	 * Create the panel.
	 */
	public UpdateClientDetails() {
		setBounds(254, 12, 740, 510);
		setLayout(null);

		JLabel lblPleaseEnterClient = new JLabel("Please Enter Client ID:");
		lblPleaseEnterClient.setBounds(10, 21, 320, 33);
		add(lblPleaseEnterClient);

		clientLoaderField = new JTextField();
		clientLoaderField.setBounds(360, 21, 370, 33);
		add(clientLoaderField);
		clientLoaderField.setColumns(10);

		JLabel label = new JLabel("");
		label.setBounds(0, 53, 370, 33);
		add(label);

		JButton clientLoader = new JButton("Click To Load Client Details");
		clientLoader.setBounds(360, 74, 370, 33);

		add(clientLoader);
		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setBounds(10, 127, 320, 33);
		add(lblClientName);
		final JTextField clientName = new JTextField("");
		clientName.setBounds(360, 127, 370, 33);
		add(clientName);
		JLabel lblClientPassword = new JLabel("Client Password:");
		lblClientPassword.setBounds(10, 180, 320, 33);
		add(lblClientPassword);
		final JTextField clientPassword = new JTextField("");
		clientPassword.setBounds(360, 180, 370, 33);
		add(clientPassword);

		JLabel lblClientAddress = new JLabel("Client Address:");
		lblClientAddress.setBounds(10, 233, 320, 33);
		add(lblClientAddress);

		final JTextField clientAddress = new JTextField("");
		clientAddress.setBounds(360, 233, 370, 33);
		add(clientAddress);

		JLabel lblClientEmail = new JLabel("Client Email:");
		lblClientEmail.setBounds(10, 286, 320, 33);
		add(lblClientEmail);

		final JTextField clientEmail = new JTextField("");
		clientEmail.setBounds(360, 286, 370, 33);
		add(clientEmail);

		JLabel lblClientPhone = new JLabel("Client Phone:");
		lblClientPhone.setBounds(10, 339, 320, 33);
		add(lblClientPhone);

		final JTextField clientPhone = new JTextField("");
		clientPhone.setBounds(360, 339, 370, 33);
		add(clientPhone);

		JLabel lblAddComment = new JLabel("Add Comment:");
		lblAddComment.setBounds(10, 392, 320, 33);
		add(lblAddComment);

		final JTextField addComment = new JTextField("");
		addComment.setBounds(360, 392, 370, 33);
		add(addComment);

		JLabel label_16 = new JLabel("");
		label_16.setBounds(0, 424, 370, 33);
		add(label_16);

		JLabel label_17 = new JLabel("");
		label_17.setBounds(370, 424, 370, 33);
		add(label_17);

		JLabel label_8 = new JLabel("");
		label_8.setBounds(0, 477, 370, 33);
		add(label_8);

		JButton label_9 = new JButton("Update Client Details");
		label_9.setBounds(360, 468, 370, 33);

		clientLoader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientLoaderField.getText().equals("")
						|| isNumeric(clientLoaderField.getText()) == false) {

					JOptionPane.showMessageDialog((Component) e.getSource(),
							"You Must Enter A Valid Client Id In Order To Load Details.");

				}
				dto = action.viewClientDetails(Long.parseLong(clientLoaderField
						.getText()));
				if (dto.getClient_id() == null
						|| Long.parseLong(clientLoaderField.getText()) == 0) {
					JOptionPane.showMessageDialog((Component) e.getSource(),
							"The Selected Client Does Not Exist In MBank."
									+ "\n Please Select A Valid Client Id.");
				} else {
					clientName.setText(dto.getClient_name());
					clientPassword.setText(dto.getPassword());
					clientAddress.setText(dto.getAddress());
					clientEmail.setText(dto.getEmail());
					clientPhone.setText(dto.getPhone());
					addComment.setText(dto.getComment());
				}
			}
		});

		label_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clientName.getText().equals("")
						|| clientPassword.getText().equals("")
						|| clientAddress.getText().equals("")
						|| clientEmail.getText().equals("")
						|| clientPhone.getText().equals("")) {
					JOptionPane.showMessageDialog((Component) e.getSource(),
							"All Fields Excluding 'comment' Must Not Be Empty."
									+ "\nPlease Occupy All Fields.");
				} else {
					int result = JOptionPane.showConfirmDialog(
							(Component) e.getSource(),
							"Are you sure you want to perform this action ?",
							"Confirmation Dialog", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.NO_OPTION) {
						Main.getPanel_1().removeAll();
						Main.getPanel_1().setLayout(new GridLayout(1, 1));
						Main.getPanel_1().add(new UpdateClientDetails());
						Main.getPanel_1().validate();
						Main.getPanel_1().repaint();

					} else if (result == JOptionPane.CLOSED_OPTION) {
						Main.getPanel_1().removeAll();
						Main.getPanel_1().setLayout(new GridLayout(1, 1));
						Main.getPanel_1().add(new UpdateClientDetails());
						Main.getPanel_1().validate();
						Main.getPanel_1().repaint();
					} else if (result == JOptionPane.YES_OPTION) {
						dto = action.viewClientDetails(Long
								.parseLong(clientLoaderField.getText()));
						dto.setClient_name(clientName.getText());
						dto.setPassword(clientPassword.getText());
						dto.setAddress(clientAddress.getText());
						dto.setEmail(clientEmail.getText());
						dto.setPhone(clientPhone.getText());
						dto.setComment(addComment.getText());
						action.updateClientDetails(dto);

						JOptionPane.showMessageDialog(
								(Component) e.getSource(),
								dto.getClient_name()
										+ "'s Details Were Successfully Updated.");
						
					}
				}

			}
		});
		add(label_9);
	}

}
