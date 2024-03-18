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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class SeferSilmeEkrani extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeferSilmeEkrani frame = new SeferSilmeEkrani();
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
	public SeferSilmeEkrani() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox kalkisSehri = new JComboBox();
		kalkisSehri.setBounds(10, 57, 81, 21);
		
		JComboBox varisSehri = new JComboBox();
		varisSehri.setBounds(101, 57, 81, 21);
		
		for(int i = 0; i < 81; i++) {
			kalkisSehri.addItem(veritabani.sehirler[i]);
			varisSehri.addItem(veritabani.sehirler[i]);
		}
		
		contentPane.add(kalkisSehri);
		contentPane.add(varisSehri);
		
		JSpinner seferGun = new JSpinner();
		seferGun.setModel(new SpinnerNumberModel(veritabani.GUN, veritabani.GUN, 31, 1));
		seferGun.setBounds(10, 113, 41, 20);
		contentPane.add(seferGun);
		
		JSpinner seferAy = new JSpinner();
		seferAy.setModel(new SpinnerNumberModel(veritabani.AY+1, veritabani.AY+1, 12, 1));
		seferAy.setBounds(50, 113, 41, 20);
		contentPane.add(seferAy);
		
		JSpinner seferYil = new JSpinner();
		seferYil.setModel(new SpinnerNumberModel(veritabani.YIL, veritabani.YIL, 2022, 1));
		seferYil.setBounds(91, 113, 52, 20);
		contentPane.add(seferYil);
		
		JSpinner seferGun_1 = new JSpinner();
		seferGun_1.setModel(new SpinnerNumberModel(31, 1, 31, 1));
		seferGun_1.setBounds(10, 174, 41, 20);
		contentPane.add(seferGun_1);
		
		JSpinner seferAy_1 = new JSpinner();
		seferAy_1.setModel(new SpinnerNumberModel(12, 1, 12, 1));
		seferAy_1.setBounds(50, 174, 41, 20);
		contentPane.add(seferAy_1);
		
		JSpinner seferYil_1 = new JSpinner();
		seferYil_1.setModel(new SpinnerNumberModel(2022, 1850, 2032, 1));
		seferYil_1.setBounds(91, 174, 52, 20);
		contentPane.add(seferYil_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 33, 331, 161);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);

		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Seyehat No.","Tarih" , "Kalkis Saati", "Varis Saati"};
		Object[] rows = new Object[4];
		
		Button button = new Button("Listele");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int gun = (int)seferGun.getValue();
				int ay = (int)seferAy.getValue();
				int yil = (int)seferYil.getValue();
				
				int gun_1 = (int)seferGun_1.getValue();
				int ay_1 = (int)seferAy_1.getValue();
				int yil_1 = (int)seferYil_1.getValue();
				
				String altTarih = yil + "-" + ay + "-" + gun;
				String ustTarih = yil_1 + "-" + ay_1 + "-" + gun_1;

	
				String sorgu = "SELECT tr.seyehatnumarasi as sno, tr.tarih, tr.kalkissaati as ksaat, tr.varissaati as vsaat FROM trenyolculugu as tr "
						+"join trenistasyonu as kalk on tr.kalkisistasyonu = kalk.istasyonnumarasi "
						+"join trenistasyonu as var on tr.varisistasyonu = var.istasyonnumarasi "
						+"where (tr.tarih between '"+altTarih+"' and '"+ustTarih+"') and kalk.bulunduguil = '" + kalkisSehri.getSelectedItem() +"' and var.bulunduguil = '" + varisSehri.getSelectedItem() + "';";
				
				model.setColumnCount(0);
				model.setRowCount(0);
				model.setColumnIdentifiers(columns);

				ResultSet rs = veritabani.calistir(sorgu);
				try {
					while(rs.next()) {
						
					rows[0] = rs.getString("sno");
					rows[1] = rs.getString("tarih");
					rows[2] = rs.getString("ksaat");
					rows[3] = rs.getString("vsaat");
					
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
		button.setBounds(50, 237, 66, 21);
		contentPane.add(button);
		
		Button button_1 = new Button("Seferi Sil");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String seyehatNo = (String) table.getValueAt(table.getSelectedRow(), 0);
				
				int result = JOptionPane.showConfirmDialog(null ,"Sefer Numarasi: "+ seyehatNo +" seferi iptal etmek için onay bekleniyor ", "Onay Gerekli.",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	
			            	String sorgu = "Select yolcu.yolcunumarasi, yolcu.adi, yolcu.soyadi, yolcu.telefonnumarasi FROM trenyolculugu "
			            			+ "JOIN bilet ON bilet.seyehatnumarasi = trenyolculugu.seyehatnumarasi "
			            			+ "JOIN yolcu ON bilet.yolcunumarasi = yolcu.yolcunumarasi "
			            			+ "WHERE trenyolculugu.seyehatnumarasi = " + seyehatNo;
			            	
			            	ResultSet rs = veritabani.calistir(sorgu);
							try {
								System.out.println("BILET İADESİ YAPILMASI GEREKEN YOLCULAR");
								while(rs.next()) {
									
								System.out.println(rs.getString("yolcunumarasi")+", "+rs.getString("adi")+", "+rs.getString("soyadi")+", "+rs.getString("telefonnumarasi"));
								
								veritabani.guncelle("delete from bilet where seyehatnumarasi = " + seyehatNo + " AND yolcunumarasi = "+rs.getString("yolcunumarasi")+" ;");
								
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			            	
							veritabani.guncelle("delete from trenyolculugu where seyehatnumarasi = " + seyehatNo);
	
			            }
			}
		});
		button_1.addActionListener(button.getActionListeners()[0]);
		button_1.setBounds(351, 228, 133, 63);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Kalkis Sehri");
		lblNewLabel.setBounds(10, 46, 81, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblVarisSehri = new JLabel("Varis Sehri");
		lblVarisSehri.setBounds(101, 46, 81, 13);
		contentPane.add(lblVarisSehri);
		
		JLabel lblTarihGgaayyyy = new JLabel("Tarih Alt Sinir GG/AA/YYYY");
		lblTarihGgaayyyy.setBounds(10, 102, 133, 13);
		contentPane.add(lblTarihGgaayyyy);
		
		JLabel lblTarihUstSinir = new JLabel("Tarih Ust Sinir GG/AA/YYYY");
		lblTarihUstSinir.setBounds(10, 165, 133, 13);
		contentPane.add(lblTarihUstSinir);
		
		JLabel lblNewLabel_1 = new JLabel("Seferler:");
		lblNewLabel_1.setBounds(248, 20, 45, 13);
		contentPane.add(lblNewLabel_1);
	}
}
