package bil372;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TrenSilme extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	String trenNumarasi = "-1";

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TrenSilme() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(28, 70, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tren Numarasi:");
		lblNewLabel.setBounds(28, 51, 96, 13);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(183, 63, 243, 131);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Trene ait planlı seferler:");
		lblNewLabel_1.setBounds(225, 51, 153, 13);
		contentPane.add(lblNewLabel_1);
		
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Tarih", "Kalkis Saati", "Seyehat Numarasi"};
		Object[] rows = new Object[3];
		
		Button button = new Button("Listele");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				trenNumarasi = textField.getText();
				String sorgu = "SELECT tyol.tarih, tyol.kalkissaati, tyol.seyehatnumarasi FROM trenyolculugu as tyol join tren as tr on tr.trennumarasi = tyol.atanantren WHERE tyol.tarih >= (select current_date) and tr.trennumarasi = "+trenNumarasi+" ;";
				
				model.setColumnCount(0);
				model.setRowCount(0);
				model.setColumnIdentifiers(columns);

				ResultSet rs = veritabani.calistir(sorgu);
				try {
					while(rs.next()) {
						
					rows[0] = rs.getString("tarih");
					rows[1] = rs.getString("kalkissaati");
					rows[2] = rs.getString("seyehatnumarasi");
					
					System.out.println(rows[0]+" - "+rows[1]+" - "+rows[2]);
					model.addRow(rows);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(model);
			}
		});
		button.setBounds(28, 95, 66, 21);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("Kaldir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rows[0] == null) {
					int result = JOptionPane.showConfirmDialog(null ,trenNumarasi+" numarali tren kaldirilmak icin onay bekliyor.", "Onay gerekli.",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				            if(result == JOptionPane.YES_OPTION){
				            	
								veritabani.guncelle("delete from tren where trennumarasi = "+trenNumarasi );

				            }		
				}else {
					int result = JOptionPane.showConfirmDialog(null ,trenNumarasi+" numarali trene ait planlanmis seferler bulunmaktadir.", "Gecersiz islem.",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(28, 196, 85, 21);
		contentPane.add(btnNewButton);
		
		
	}
}
