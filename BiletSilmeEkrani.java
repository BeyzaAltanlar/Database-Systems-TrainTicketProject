package bil372;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class BiletSilmeEkrani extends JFrame {

	private JPanel contentPane;
	private JTextField yolcuAdi;
	private JTextField yolcuSoyadi;
	private JTextField yolcuGSM;
	private JTable table;
	private Button button_1;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiletSilmeEkrani frame = new BiletSilmeEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public BiletSilmeEkrani() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		yolcuAdi = new JTextField();
		yolcuAdi.setBounds(27, 26, 96, 19);
		contentPane.add(yolcuAdi);
		yolcuAdi.setColumns(10);
		
		yolcuSoyadi = new JTextField();
		yolcuSoyadi.setBounds(152, 26, 96, 19);
		contentPane.add(yolcuSoyadi);
		yolcuSoyadi.setColumns(10);
		
		yolcuGSM = new JTextField();
		yolcuGSM.setBounds(277, 26, 96, 19);
		contentPane.add(yolcuGSM);
		yolcuGSM.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 108, 723, 135);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Seyehat No.", "Kalkis Sehri", "Kalkis Istasyonu", "Varis Sehri", "Varis Istasyonu", "Tarih", "Kalkis Saati", "Yolcu No."};
		Object[] rows = new Object[8];
		
		Button button = new Button("ara");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sorgu = "select b.seyehatnumarasi as sno, kalkis.bulunduguil ksehri,\r\n"
						+ "kalkis.istasyonismi as kistasyonu, varis.bulunduguil as vsehri,\r\n"
						+ "varis.istasyonismi as vistasyonu,\r\n"
						+ "sefer.tarih as tarih, sefer.kalkissaati as saat, y.yolcunumarasi as yno from ((yolcu as y natural join bilet as b)\r\n"
						+ "               natural join trenyolculugu as sefer)\r\n"
						+ "               left join trenistasyonu as kalkis on sefer.kalkisistasyonu = kalkis.istasyonnumarasi\r\n"
						+ "               join trenistasyonu as varis on sefer.varisistasyonu = varis.istasyonnumarasi\r\n"
						+ "               where y.adi = '" + yolcuAdi.getText()+"' and y.soyadi = '" + yolcuSoyadi.getText() + "' and y.telefonnumarasi = '" + yolcuGSM.getText() +"';";
				
				model.setColumnCount(0);
				model.setRowCount(0);
				model.setColumnIdentifiers(columns);
				
				ResultSet rs = veritabani.calistir(sorgu);
				
				try {
					while(rs.next()) {
						rows[0] = rs.getString("sno");
						rows[1] = rs.getString("ksehri");
						rows[2] = rs.getString("kistasyonu");
						rows[3] = rs.getString("vsehri");
						rows[4] = rs.getString("vistasyonu");
						rows[5] = rs.getString("tarih");
						rows[6] = rs.getString("saat");
						rows[7] = rs.getString("yno");
						System.out.println(rows[0]+" - "+rows[1]+" - "+rows[2]+" - "+rows[3]+" - "+rows[4]+" - "+rows[5]+" - "+rows[6]+" - "+rows[7]);

						model.addRow(rows);

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(model);
			}
		});
		
		button.setBounds(169, 61, 66, 21);
		contentPane.add(button);
		
		button_1 = new Button("Bileti Sil");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String seyehatNo = (String) table.getValueAt(table.getSelectedRow(), 0);
            	String yolcuNo = (String) table.getValueAt(table.getSelectedRow(), 7);
            	
            	String sorgu = "delete from bilet where seyehatnumarasi = " + seyehatNo + " and yolcunumarasi = " + yolcuNo;
				int result = JOptionPane.showConfirmDialog(null ,"Seyehat Numarasi:"+seyehatNo+" Yolcu Numarasi:"+yolcuNo+" bilet silinmek icin onay bekliyor.", "Swing Tester",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){			     
			            	
			            	veritabani.guncelle(sorgu);  	
			            }
			}
		});
		button_1.addActionListener(button.getActionListeners()[0]);
		button_1.setBounds(322, 313, 66, 21);
		contentPane.add(button_1);
		
		lblNewLabel = new JLabel("Yolcu Adi:");
		lblNewLabel.setBounds(27, 10, 96, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Yolcu Soyadi:");
		lblNewLabel_1.setBounds(152, 10, 96, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Yolcu Telefon Numarasi:");
		lblNewLabel_2.setBounds(277, 10, 147, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Yolcuya Ait Biletler:");
		lblNewLabel_3.setBounds(27, 93, 319, 13);
		contentPane.add(lblNewLabel_3);
	
	}
}
