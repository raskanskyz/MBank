package swing;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Action.adminAction;
import dtos.ClientDto;

public class AddNewClientAndAccount extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField clientName;
	private JTextField clientEmail;
	private JTextField clientPhone;
	private JLabel lblClientEmail;
	private JTextField comments;
	private JTextField initialDeposit;
	private JPasswordField clientPassword;
	private JTextField clientAddress;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;

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
	public AddNewClientAndAccount() {
		setBounds(254, 12, 740, 510);
		setLayout(null);

		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setBounds(53, 23, 295, 33);
		add(lblClientName);
		clientName = new JTextField();
		clientName.setBounds(358, 23, 370, 33);
		add(clientName);
		clientName.setColumns(10);

		JLabel lblClientPassword = new JLabel("Client Password:");
		lblClientPassword.setBounds(53, 76, 295, 33);
		add(lblClientPassword);

		clientPassword = new JPasswordField();
		clientPassword.setBounds(358, 76, 370, 33);
		clientPassword.setColumns(10);
		add(clientPassword);

		JLabel lblClientAddress = new JLabel("Client Address:");
		lblClientAddress.setBounds(53, 129, 295, 33);
		add(lblClientAddress);

		clientAddress = new JTextField();
		clientAddress.setBounds(358, 129, 370, 33);
		clientAddress.setColumns(10);
		add(clientAddress);

		lblClientEmail = new JLabel("Client Email:");
		lblClientEmail.setBounds(53, 182, 295, 33);
		add(lblClientEmail);

		clientEmail = new JTextField();
		clientEmail.setBounds(358, 182, 370, 33);
		clientEmail.setColumns(10);
		add(clientEmail);

		JLabel lblClientPhone = new JLabel("Client Phone:");
		lblClientPhone.setBounds(53, 235, 295, 33);
		add(lblClientPhone);

		clientPhone = new JTextField();
		clientPhone.setBounds(358, 235, 370, 33);
		clientPhone.setColumns(10);
		add(clientPhone);

		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(53, 288, 295, 33);
		add(lblComments);

		comments = new JTextField();
		comments.setBounds(358, 288, 370, 33);
		comments.setColumns(10);
		add(comments);

		JLabel lblInitialDeposit = new JLabel("Initial Deposit:");
		lblInitialDeposit.setBounds(53, 341, 295, 33);
		add(lblInitialDeposit);

		initialDeposit = new JTextField();
		initialDeposit.setBounds(358, 341, 370, 33);
		initialDeposit.setColumns(10);
		add(initialDeposit);

		label = new JLabel("");
		label.setBounds(0, 371, 370, 33);
		add(label);

		label_1 = new JLabel("");
		label_1.setBounds(370, 371, 370, 33);
		add(label_1);

		label_2 = new JLabel("");
		label_2.setBounds(0, 424, 370, 33);
		add(label_2);

		label_3 = new JLabel("");
		label_3.setBounds(370, 424, 370, 33);
		add(label_3);

		label_4 = new JLabel("");
		label_4.setBounds(0, 477, 370, 33);
		add(label_4);

		JButton btnAddClient = new JButton("Add Client");
		btnAddClient.setBounds(358, 466, 370, 33);
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((clientName.getText().equals("")
						|| clientPassword.getPassword().equals("")
						|| clientAddress.getText().equals("")
						|| clientEmail.getText().equals("")
						|| clientPhone.getText().equals("")))) {
					JOptionPane.showMessageDialog((Component) e.getSource(),
							"Fields Cannot Be Empty!", "Error Adding Client",
							JOptionPane.ERROR_MESSAGE);

				}// <---------------------------------checks if fields are empty

				else if (isNumeric(initialDeposit.getText()) == false
						|| Double.parseDouble(initialDeposit.getText()) == 0) {
					JOptionPane.showMessageDialog((Component) e.getSource(),
							"Initial Deposit Must Be Numeric And Not 0 !",
							"Error Adding Client", JOptionPane.ERROR_MESSAGE);
				}// <-----------------------------Varify that initial deposit is
					// Numeric

				else {
					int result = JOptionPane.showConfirmDialog(
							(Component) e.getSource(),
							"Are you sure you want to perform this action ?",
							"Confirmation Dialog", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.NO_OPTION) {
						Main.getPanel_1().removeAll();
						Main.getPanel_1().setLayout(new GridLayout(1, 1));
						Main.getPanel_1().add(new AddNewClientAndAccount());
						Main.getPanel_1().validate();
						Main.getPanel_1().repaint();

					} else if (result == JOptionPane.CLOSED_OPTION) {
						Main.getPanel_1().removeAll();
						Main.getPanel_1().setLayout(new GridLayout(1, 1));
						Main.getPanel_1().add(new AddNewClientAndAccount());
						Main.getPanel_1().validate();
						Main.getPanel_1().repaint();

					} else if (result == JOptionPane.YES_OPTION) {

						adminAction action = (adminAction) Main.getAction();
						ClientDto dto = new ClientDto();
						dto.setClient_name(clientName.getText());
						dto.setPassword(String.valueOf(clientPassword
								.getPassword()));
						dto.setAddress(clientAddress.getText());
						dto.setEmail(clientEmail.getText());
						dto.setPhone(clientPhone.getText());
						dto.setComment(comments.getText());

						if (action.addNewClient(dto,
								Double.parseDouble(initialDeposit.getText())) == true) {
							JOptionPane.showMessageDialog(
									(Component) e.getSource(),
									dto.getClient_name()
											+ " Was Successfully Added To MBank");
						}

						else {
							JOptionPane.showMessageDialog(
									(Component) e.getSource(),
									"Client Could Not Be Added To MBank, Note That Some Fields Are Unique e.g Password,Phone."
											+ "\n Also Varify That You Entered An Initial Deposit Amount.",
									"Error Adding Client",
									JOptionPane.ERROR_MESSAGE);

						}

					}

				}
			}
		});
		add(btnAddClient);
		setVisible(true);

	}
}
