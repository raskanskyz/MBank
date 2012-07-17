package swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import dbManagers.ClientDBManager;
import dbManagers.ClientManager;

public class ViewClientDetails extends JPanel {
	ListTableModel model;
	ClientManager cm = new ClientDBManager();
	ResultSet resultSet = cm.getResultSet();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private int selectedRadio; 
	 

	
	/**
	 * Create the panel.
	 */
	public ViewClientDetails() {
		setBounds(254, 12, 740, 510);
		setLayout(null);
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 716, 375);
		add(scrollPane);
		
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
// If not shaded, match the table's background
c.setBackground(getBackground());
}
return c;
}
};
		
		
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 399, 716, 99);
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel radioPanel = new JPanel();
		panel.add(radioPanel);
		radioPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JRadioButton rdbtnFilterClientById = new JRadioButton("Filter Client By \"client id\"");
		
		radioPanel.add(rdbtnFilterClientById);
		
		JRadioButton rdbtnFilterClientByClientName = new JRadioButton("Filter Client By \"client name\"");
		rdbtnFilterClientByClientName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectedRadio = 2;
				
				}
		});
		radioPanel.add(rdbtnFilterClientByClientName);
		
		JRadioButton rdbtnFilterClientByType = new JRadioButton("Filter Client By \"type\"");
		rdbtnFilterClientByType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				selectedRadio = 3;
				
			}
		});
		radioPanel.add(rdbtnFilterClientByType);
		
		JPanel label_1 = new JPanel();
		final JTextField filterText = new JTextField();
		label_1.add(filterText);
		panel.add(label_1);
		label_1.setLayout(new GridLayout(2, 0, 0, 20));
		
		JButton submitButton = new JButton("Click To Filter Client List");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final TableRowSorter<ListTableModel> sorter = new TableRowSorter<ListTableModel>(model);
				table.setRowSorter(sorter);
				String text = filterText.getText();
				 sorter.setRowFilter(RowFilter.regexFilter(text));
				
				 if(selectedRadio==1){
					RowFilter<? super TableModel, ? super Integer> rowFilter = RowFilter.regexFilter(filterText.getText(), 0);
					sorter.setRowFilter(rowFilter);
					 sorter.setModel(model);
				}
				else if(selectedRadio==2){
					RowFilter<? super TableModel, ? super Integer> rowFilter = RowFilter.regexFilter(filterText.getText(), 1);
					sorter.setRowFilter(rowFilter);
					 sorter.setModel(model);
					
				}
				else if(selectedRadio ==3){
					RowFilter<? super TableModel, ? super Integer> rowFilter = RowFilter.regexFilter(filterText.getText(), 3);
					sorter.setRowFilter(rowFilter);
					 sorter.setModel(model);
				}
				
			}
		});
		label_1.add(submitButton);
		
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnFilterClientById);
		radioGroup.add(rdbtnFilterClientByClientName);
		radioGroup.add(rdbtnFilterClientByType);
		
		rdbtnFilterClientById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		selectedRadio = 1;
		         
			}
		});
		
		 try {
			model = ListTableModel.createModelFromResultSet(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			table.setModel(model);
	}
}
