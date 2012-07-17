package swing;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dbManagers.ClientDBManager;
import dbManagers.ClientManager;
import dtos.ClientDto;
import Action.adminAction;

public class ReviewRemovePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ReviewRemovePanel(final long client_id) {

		ClientManager cm = new ClientDBManager();
		final ClientDto dto = cm.selectClient(client_id);

		setBounds(254, 12, 740, 510);
		setLayout(new GridLayout(0, 2, 0, 20));

		JLabel clientName = new JLabel("Client Name:");
		add(clientName);
		JLabel label_1 = new JLabel(dto.getClient_name());
		add(label_1);

		JLabel clientPassword = new JLabel("Client Password:");
		add(clientPassword);

		JLabel label_3 = new JLabel(dto.getPassword());
		add(label_3);

		JLabel clientType = new JLabel("Client Type:");
		add(clientType);

		JLabel label_5 = new JLabel(dto.getType());
		add(label_5);

		JLabel lblClientAddress = new JLabel("Client Address:");
		add(lblClientAddress);

		JLabel label_7 = new JLabel(dto.getAddress());
		add(label_7);

		JLabel lblClientEmail = new JLabel("Client Email:");
		add(lblClientEmail);

		JLabel label_9 = new JLabel(dto.getEmail());
		add(label_9);

		JLabel lblClientPhone = new JLabel("Client Phone:");
		add(lblClientPhone);

		JLabel label_11 = new JLabel(dto.getPhone());
		add(label_11);

		JLabel lblComments = new JLabel("Comments:");
		add(lblComments);

		JLabel label_13 = new JLabel(dto.getComment());
		add(label_13);

		JLabel label_14 = new JLabel("");
		add(label_14);

		JLabel label_15 = new JLabel("");
		add(label_15);

		JLabel label_16 = new JLabel("");
		add(label_16);

		JLabel label_17 = new JLabel("");
		add(label_17);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Main.getPanel_1().removeAll();
				Main.getPanel_1().setLayout(new GridLayout(1, 1));
				Main.getPanel_1().add(new RemoveClient());
				Main.getPanel_1().validate();
				Main.getPanel_1().repaint();

			}
		});
		add(btnNewButton);

		JButton label_19 = new JButton("Remove Client");
		label_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(
						(Component) e.getSource(),
						"Are you sure you want to perform this action ?",
						"Confirmation Dialog", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.NO_OPTION) {
					Main.getPanel_1().removeAll();
					Main.getPanel_1().setLayout(new GridLayout(1, 1));
					Main.getPanel_1().add(new RemoveClient());
					Main.getPanel_1().validate();
					Main.getPanel_1().repaint();
				}//NO option
				else if (result == JOptionPane.CLOSED_OPTION) {
					Main.getPanel_1().removeAll();
					Main.getPanel_1().setLayout(new GridLayout(1, 1));
					Main.getPanel_1().add(new RemoveClient());
					Main.getPanel_1().validate();
					Main.getPanel_1().repaint();
				} //Closed option
				else if (result == JOptionPane.YES_OPTION) {
					adminAction a = (adminAction) Main.getAction();
					a.removeClient(dto);
					JOptionPane.showMessageDialog(
							(Component) e.getSource(),
							(dto.getClient_name() + "(id=" + client_id + ") Has Been Removed Successfully."));
				}// YES option
			}

		});
		add(label_19);
	}

}
