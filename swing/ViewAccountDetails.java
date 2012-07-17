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

import dbManagers.AccountDBManager;
import dbManagers.AccountManager;

public class ViewAccountDetails extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ListTableModel model;
	AccountManager am = new AccountDBManager();
	ResultSet resultSet = am.getResultSet();
	private JTable table;
	private JTextField filterText;
	private int selectedRadio;
	/**
	 * Create the panel.
	 */
	public ViewAccountDetails() {
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
//If not shaded, match the table's background
c.setBackground(getBackground());
}
return c;
}
};
		
		scrollPane.setViewportView(table);
		 try {
				model = ListTableModel.createModelFromResultSet(resultSet);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				table.setModel(model);
				
				JPanel panel = new JPanel();
				panel.setBounds(12, 399, 716, 99);
				add(panel);
				panel.setLayout(new GridLayout(0, 2, 0, 0));
				
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(4, 0, 0, 0));
				
				
				JRadioButton rdbtnFilterAccountBy = new JRadioButton("Filter Account By \"account id\"");
				rdbtnFilterAccountBy.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectedRadio = 1;
						
					}
				});
				panel_1.add(rdbtnFilterAccountBy);
				
				JRadioButton rdbtnFilterAccountBy_1 = new JRadioButton("Filter Account By \"client id\"");
				rdbtnFilterAccountBy_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						selectedRadio = 2;
						
					}
				});
				panel_1.add(rdbtnFilterAccountBy_1);
				
				ButtonGroup radioGroup = new ButtonGroup();
				radioGroup.add(rdbtnFilterAccountBy);
				radioGroup.add(rdbtnFilterAccountBy_1);
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				panel_2.setLayout(new GridLayout(2, 0, 0, 20));
				
				filterText = new JTextField();
				panel_2.add(filterText);
				
				JButton btnClickToFilter = new JButton("Click To Filter Account List");
				btnClickToFilter.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
						table.setRowSorter(sorter);
						String text = filterText.getText();
						 sorter.setRowFilter(RowFilter.regexFilter(text));
						
						 if(selectedRadio ==1){
								RowFilter<? super TableModel, ? super Integer> rowFilter = RowFilter.regexFilter(filterText.getText(), 0);
								sorter.setRowFilter(rowFilter);
								 sorter.setModel(model);
						}
						else if(selectedRadio==2){
							RowFilter<? super TableModel, ? super Integer> rowFilter = RowFilter.regexFilter(filterText.getText(), 1);
							sorter.setRowFilter(rowFilter);
							 sorter.setModel(model);
						}
					}
				});
				panel_2.add(btnClickToFilter);
	}
}
