package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Action.Action;
import Concrete.MBankST;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	protected static final String swing = null;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	public Login(final JFrame parent) {

		setTitle("Login Screen");
		setResizable(false);
		setBounds(100, 100, 320, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);

		getContentPane().add(contentPanel, BorderLayout.CENTER);

		textField = new JTextField();
		textField.setBounds(98, 18, 188, 31);
		contentPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(98, 86, 188, 31);
		contentPanel.add(passwordField);

		final JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setBounds(12, 12, 123, 43);
		contentPanel.add(lblUsername);

		final JLabel label = new JLabel("Password:");
		label.setBounds(12, 80, 123, 43);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						Action act = MBankST.instance().login(
								textField.getText(),
								String.valueOf(passwordField.getPassword()));
						setVisible(false);

						if (act != null) {
							Main.setAction(act);
							setVisible(false);
						}

					}// actionPerformed

				});

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);

	}// CTOR

	protected static Container getPanel_1() {
		// TODO Auto-generated method stub
		return null;
	}

}// JDialog
