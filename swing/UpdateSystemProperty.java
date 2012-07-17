package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import Action.adminAction;
import dbManagers.PropertiesDBManager;
import dbManagers.PropertiesManager;
import dtos.PropertiesDto;

public class UpdateSystemProperty extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	ListTableModel model;
	PropertiesManager pm = new PropertiesDBManager();
	ResultSet resultSet = pm.getResultSet();
	PropertiesDto pdto = new PropertiesDto();

	/**
	 * Create the panel.
	 */
	public UpdateSystemProperty() {

		setBounds(254, 12, 740, 510);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 79, 716, 278);
		add(scrollPane);
		JTableHeader header = new JTableHeader();
		header.setTable(table);

		table = new JTable(){
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer,
                    int rowIndex, int vColIndex) {
Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
c.setBackground(new Color(211,211,211));
} else {
//If not shaded, match the table's background
c.setBackground(getBackground());
}
return c;
}
};
		table.setCellSelectionEnabled(true);

		JPanel panel = new JPanel();
		panel.setBounds(12, 462, 716, 36);
		add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel label = new JLabel("");
		JButton label_1 = new JButton("Update System Properties");

		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		panel.add(label);
		panel.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 12, 716, 36);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblSelectAProperty = new JLabel(
				"Select A Property Value And Insert New Value Below:");
		panel_1.add(lblSelectAProperty);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 369, 716, 36);
		add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		final JLabel lblSelectAProperty_1 = new JLabel("Select A Property To Edit.");
		panel_2.add(lblSelectAProperty_1);

		final TextField newInput = new TextField("");

		newInput.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_2.add(newInput);

		try {
			 model = ListTableModel.createModelFromResultSet(resultSet);
			table.setModel(model);

			table.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.getClickCount() == 1) {
						JTable target = (JTable) e.getSource();
						int row = target.getSelectedRow();
						int column = target.getSelectedColumn();
						String data = (String) target.getValueAt(row, column);

						if (column == 0) {
							lblSelectAProperty_1.setText("Set new Value For "
									+ (String) target.getValueAt(row, column)
									+ ":");
							pdto.setProp_key(data);

						}

					}
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(newInput.getText().equals("")){
				JOptionPane.showMessageDialog((Component) e.getSource(),
						"You Must Enter A New Value To In Order To Update A Property.");
			}
			else{
				pdto.setProp_value(newInput.getText());
				adminAction action = (adminAction) Main.getAction();
				action.updateSystemProperty(pdto);
				
				Main.getPanel_1().removeAll();
				Main.getPanel_1().setLayout(new GridLayout(1,1));
				Main.getPanel_1().add(new UpdateSystemProperty());
				Main.getPanel_1().validate();
				Main.getPanel_1().repaint();
			}
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
	}
}
