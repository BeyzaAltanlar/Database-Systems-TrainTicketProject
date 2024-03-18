package bil372;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class IstasyonKaldir extends JFrame {

	private JPanel contentPane;
	private JTable table;

	String istasyonSehri = "";
	String istasyonIsmi = "";
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public IstasyonKaldir() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox kistasyon = new JComboBox();
		kistasyon.setBounds(10, 118, 157, 21);
		contentPane.add(kistasyon);
		
		JComboBox kalkisSehri = new JComboBox();
		kalkisSehri.setBounds(10, 33, 157, 21);
		
		for(int i = 0; i < 81; i++) {
			kalkisSehri.addItem(veritabani.sehirler[i]);
		}
		contentPane.add(kalkisSehri);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 33, 182, 193);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		
		Button button_2 = new Button("Istasyon Listele");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				kistasyon.removeAllItems();
				
				String sorgu = "select istasyonismi from trenistasyonu where bulunduguil = '"+kalkisSehri.getSelectedItem()+"';";
				ResultSet rs = veritabani.calistir(sorgu);
				
				try {
					while(rs.next()) {
						kistasyon.addItem(rs.getString("istasyonismi"));
					}
				} catch (SQLException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				};
}
		});
		button_2.setBounds(0, 60, 167, 21);
		contentPane.add(button_2);
		
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Tarih", "Kalkis Saati", "Seyehat Numarasi"};
		Object[] rows = new Object[3];
		
		Button button = new Button("Planlı Seferleri Listele");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				istasyonIsmi = (String)kistasyon.getSelectedItem();
				istasyonSehri = (String)kalkisSehri.getSelectedItem();
				
				String sorgu = "SELECT tyol.tarih, tyol.kalkissaati, tyol.seyehatnumarasi FROM trenyolculugu as tyol join trenistasyonu as kalk on kalk.istasyonnumarasi = tyol.kalkisistasyonu join trenistasyonu as var on var.istasyonnumarasi = tyol.varisistasyonu "
						+ "WHERE tyol.tarih >= (select current_date) and (kalk.istasyonismi = '" + kistasyon.getSelectedItem() + "' OR var.istasyonismi = '" + kistasyon.getSelectedItem() +"' ) ;";
				
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
		button.setBounds(10, 150, 157, 21);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("İstasyona Ait Planlı Seferler:");
		lblNewLabel.setBounds(244, 20, 45, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("İstasyonu Kaldir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rows[0] == null) {
					int result = JOptionPane.showConfirmDialog(null ,istasyonIsmi+" isimli istasyon kaldirilmak icin onay bekliyor.", "Onay gerekli.",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				            if(result == JOptionPane.YES_OPTION){
				            	
								veritabani.guncelle("delete from trenistasyonu where bulunduguil = '"+istasyonSehri+"' AND istasyonismi = '" +istasyonIsmi+"' ;" );

				            }		
				}else {
					int result = JOptionPane.showConfirmDialog(null ,istasyonIsmi+" isimli istasyona ait planlanmis seferler bulunmaktadir.", "Gecersiz islem.",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(10, 204, 131, 49);
		contentPane.add(btnNewButton);
		
	}

}
