package bil372;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SeferEklemeEkranı extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public int trenBakimSuresi;
	public int seferSuresi;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeferEklemeEkranı frame = new SeferEklemeEkranı();
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
	public SeferEklemeEkranı() {
		
		trenBakimSuresi = 12;
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox kalkisSehri = new JComboBox();
		kalkisSehri.setBounds(10, 33, 81, 21);
		
		JComboBox varisSehri = new JComboBox();
		varisSehri.setBounds(101, 33, 81, 21);
		
		for(int i = 0; i < 81; i++) {
			kalkisSehri.addItem(veritabani.sehirler[i]);
			varisSehri.addItem(veritabani.sehirler[i]);
		}
		
		contentPane.add(kalkisSehri);
		contentPane.add(varisSehri);
		
		JSpinner seferGun = new JSpinner();
		seferGun.setModel(new SpinnerNumberModel(veritabani.GUN, veritabani.GUN, 31, 1));
		seferGun.setBounds(10, 140, 41, 20);
		contentPane.add(seferGun);
		
		JSpinner seferAy = new JSpinner();
		seferAy.setModel(new SpinnerNumberModel(veritabani.AY+1, veritabani.AY, 12, 1));
		seferAy.setBounds(50, 140, 41, 20);
		contentPane.add(seferAy);
		
		JSpinner seferYil = new JSpinner();
		seferYil.setModel(new SpinnerNumberModel(veritabani.YIL, veritabani.YIL, 2022, 1));
		seferYil.setBounds(90, 140, 52, 20);
		contentPane.add(seferYil);
		
		JSpinner seferSaat = new JSpinner();
		seferSaat.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		seferSaat.setBounds(10, 186, 41, 20);
		contentPane.add(seferSaat);
		
		JSpinner seferDakika = new JSpinner();
		seferDakika.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		seferDakika.setBounds(50, 186, 41, 20);
		contentPane.add(seferDakika);
		
		JComboBox kistasyon = new JComboBox();
		kistasyon.setBounds(10, 94, 81, 21);
		contentPane.add(kistasyon);
		
		JComboBox vistasyon = new JComboBox();
		vistasyon.setBounds(103, 94, 81, 21);
		contentPane.add(vistasyon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 44, 208, 110);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Tren No.", "Tren Tipi", "Koltuk Sayisi"};
		Object[] rows = new Object[3];
		
		Button button = new Button("Listele");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int gun = (int)seferGun.getValue();
				int ay = (int)seferAy.getValue();
				int yil = (int)seferYil.getValue();

				int saat = (int)seferSaat.getValue();
				int dakika = (int)seferDakika.getValue();
				
				int[] oncesi = tren.GeriyeSar(gun, ay, yil, saat, dakika, seferSuresi);
				int[] sonrasi = tren.IleriSar(gun, ay, yil, saat, dakika, trenBakimSuresi);
				
				String altTarih = oncesi[2] + "-" + oncesi[1] + "-" + oncesi[0];
				String ustTarih = sonrasi[2] + "-" + sonrasi[1] + "-" + sonrasi[0];
	
				String sorgu = "SELECT * FROM tren WHERE trennumarasi NOT IN (SELECT tr.atanantren FROM trenyolculugu as tr where tr.tarih between '"+altTarih+"' and '"+ustTarih+"');";
				
				model.setColumnCount(0);
				model.setRowCount(0);
				model.setColumnIdentifiers(columns);

				ResultSet rs = veritabani.calistir(sorgu);
				try {
					while(rs.next()) {
						
					rows[0] = rs.getString("trennumarasi");
					rows[1] = rs.getString("trentipi");
					rows[2] = rs.getString("koltuksayisi");
					
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
		button.setBounds(111, 185, 66, 21);
		contentPane.add(button);
		
		Button button_1 = new Button("Sefer Ekle");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sorgu = "select istasyonnumarasi from trenistasyonu where bulunduguil = '"+kalkisSehri.getSelectedItem()+"' and istasyonismi = '"+kistasyon.getSelectedItem()+"';";
				ResultSet rs = veritabani.calistir(sorgu);
				
				String sorgu2 = "select istasyonnumarasi from trenistasyonu where bulunduguil = '"+varisSehri.getSelectedItem()+"' and istasyonismi = '"+vistasyon.getSelectedItem()+"';";
				ResultSet rs2 = veritabani.calistir(sorgu2);
				
				String kalkisIstasyonu = "";
				String varisIstasyonu = "";

				try {
					rs.next();
					rs2.next();
					
					kalkisIstasyonu = rs.getString("istasyonnumarasi");
					varisIstasyonu = rs2.getString("istasyonnumarasi");
					
				} catch (SQLException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
				
				String trenNo = (String) table.getValueAt(table.getSelectedRow(), 0);
				String tarih = (int)seferYil.getValue() +"-"+ (int)seferAy.getValue() +"-"+ (int)seferGun.getValue();
				String saat = (int)seferSaat.getValue() + "";
				String dakika =	(int)seferDakika.getValue() + "";				
						
				int result = JOptionPane.showConfirmDialog(null ,"Kalkis Tarihi: "+tarih+", Kalkis Saati: " + saat+":"+dakika +" tarihli sefer eklenmek icin onay bekliyor.", "Onay Gerekli.",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	
							trenyolculugu yeniSefer = new trenyolculugu(kalkisIstasyonu, varisIstasyonu, trenNo, tarih, saat, dakika );
  	
			            }
			}
		});
		button_1.setBounds(290, 203, 66, 21);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Kalkis Sehri:");
		lblNewLabel.setBounds(10, 22, 81, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Varis Sehri:");
		lblNewLabel_1.setBounds(101, 22, 74, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tarih GG/AA/YYYY");
		lblNewLabel_2.setBounds(10, 128, 132, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Kalkis Saati:");
		lblNewLabel_3.setBounds(10, 173, 81, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Kalkis Istasyonu:");
		lblNewLabel_4.setBounds(10, 84, 81, 13);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Varis Istasyonu:");
		lblNewLabel_4_1.setBounds(103, 84, 81, 13);
		contentPane.add(lblNewLabel_4_1);
		
		Button button_2 = new Button("Istasyon Listele");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				kistasyon.removeAllItems();
				vistasyon.removeAllItems();
				
				String sorgu = "select istasyonismi from trenistasyonu where bulunduguil = '"+kalkisSehri.getSelectedItem()+"';";
				ResultSet rs = veritabani.calistir(sorgu);
				
				String sorgu2 = "select istasyonismi from trenistasyonu where bulunduguil = '"+varisSehri.getSelectedItem()+"';";
				ResultSet rs2 = veritabani.calistir(sorgu2);
				
				try {
					while(rs.next()) {
						kistasyon.addItem(rs.getString("istasyonismi"));
					}
					while(rs2.next()) {
						vistasyon.addItem(rs2.getString("istasyonismi"));
					}
				} catch (SQLException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				};
}
		});
		button_2.setBounds(10, 60, 167, 13);
		contentPane.add(button_2);
		
		JLabel lblNewLabel_5 = new JLabel("Uygun Trenler:");
		lblNewLabel_5.setBounds(218, 33, 208, 13);
		contentPane.add(lblNewLabel_5);
	}
}
