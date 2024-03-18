package bil372;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;

public class BiletEklemeEkrani extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField isim;
	private JTextField soyisim;
	private JTextField tel;
	private JTextField mail;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiletEklemeEkrani frame = new BiletEklemeEkrani();
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
	public BiletEklemeEkrani() {

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Kalkış - Varış kutucuklarının olusturulması
		String sorgu = "select distinct bulunduguil from trenistasyonu";
		ResultSet rs = veritabani.calistir(sorgu);
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 40, 100, 21);
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(120, 40, 100, 21);
		try {
			while(rs.next()) {
			comboBox.addItem(rs.getString("bulunduguil"));
			comboBox_1.addItem(rs.getString("bulunduguil"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(comboBox);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 17, 100, 21);
		lblNewLabel.setText("Kalkis yeri:");
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kalkis yeri:");
		lblNewLabel_1.setBounds(120, 17, 100, 21);
		lblNewLabel_1.setText("Varis yeri:");
		contentPane.add(lblNewLabel_1);
		//Kalkış - Varış kutucuklarının olusturulması sonu
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(230, 40, 419, 186);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		JSpinner altGun = new JSpinner();
		altGun.setModel(new SpinnerNumberModel(veritabani.GUN, veritabani.GUN, 31, 1));
		altGun.setBounds(10, 94, 41, 20);
		contentPane.add(altGun);
		
		JSpinner altAy = new JSpinner();
		altAy.setModel(new SpinnerNumberModel(veritabani.AY+1, veritabani.AY, 12, 1));
		altAy.setBounds(50, 94, 41, 20);
		contentPane.add(altAy);
		
		JSpinner altYil = new JSpinner();
		altYil.setModel(new SpinnerNumberModel(veritabani.YIL, veritabani.YIL, 2032, 1));
		altYil.setBounds(90, 94, 55, 20);
		contentPane.add(altYil);
		
		JSpinner ustGun = new JSpinner();
		ustGun.setModel(new SpinnerNumberModel(31, 1, 31, 1));
		ustGun.setBounds(10, 172, 41, 21);
		contentPane.add(ustGun);
		
		JSpinner ustAy = new JSpinner();
		ustAy.setModel(new SpinnerNumberModel(12, 1, 12, 1));
		ustAy.setBounds(50, 173, 41, 20);
		contentPane.add(ustAy);
		
		JSpinner ustYil = new JSpinner();
		ustYil.setModel(new SpinnerNumberModel(2022, 2022, 2032, 1));
		ustYil.setBounds(90, 173, 55, 20);
		contentPane.add(ustYil);
		
		JLabel lblNewLabel_2 = new JLabel("En Erken Kalkis Tarihi GG/AA/YY:");
		lblNewLabel_2.setBounds(10, 71, 210, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("En Gec Kalkis Tarihi  GG/AA/YY:");
		lblNewLabel_2_1.setBounds(10, 153, 210, 21);
		contentPane.add(lblNewLabel_2_1);
				
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Seyehat No.","Tarih", "Kalkis Saati", "Varis Saati"};
		Object[] rows = new Object[4];
		
		Button button = new Button("Listele");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sorgu = "SELECT trenyolculugu.seyehatnumarasi AS snumara,\r\n"
						+ "    trenyolculugu.tarih,\r\n"
						+ "    trenyolculugu.kalkissaati,\r\n"
						+ "    trenyolculugu.varissaati\r\n"
						+ "   FROM trenyolculugu\r\n"
						+ "     JOIN trenistasyonu kalkis ON trenyolculugu.kalkisistasyonu = kalkis.istasyonnumarasi\r\n"
						+ "     JOIN trenistasyonu varis ON trenyolculugu.varisistasyonu = varis.istasyonnumarasi\r\n"
						+ "  WHERE kalkis.bulunduguil::text = '"+comboBox.getSelectedItem()+"'::text AND varis.bulunduguil::text = '"+comboBox_1.getSelectedItem()+"'::text\n"
						+" AND trenyolculugu.tarih between '"+altYil.getValue()+"-"+altAy.getValue()+"-"+altGun.getValue()+"' AND '"+ustYil.getValue()+"-"+ustAy.getValue()+"-"+ustGun.getValue()+"';";
				System.out.println(sorgu);
				
				model.setColumnCount(0);
				model.setRowCount(0);
				model.setColumnIdentifiers(columns);

				ResultSet rs = veritabani.calistir(sorgu);
				try {
					while(rs.next()) {
					rows[0] = rs.getString("snumara");
					rows[1] = rs.getString("tarih");
					rows[2] = rs.getString("kalkissaati");
					rows[3] = rs.getString("varissaati");
					System.out.println(rows[0]+" - "+rows[1]+" - "+rows[2]+" - "+rows[3]);
					model.addRow(rows);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				table.setModel(model);
				//System.out.println(table.getX());
			}
		});
		
		
		JComboBox bosKoltukListe = new JComboBox();
		bosKoltukListe.setBounds(139, 434, 96, 21);
		contentPane.add(bosKoltukListe);
		
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	int seyehatNo = Integer.parseInt((String)table.getValueAt(table.getSelectedRow(), 0));
		    	BosKoltuklar(seyehatNo, bosKoltukListe);
		    }
		});
		
		button.setBounds(79, 209, 66, 21);
		contentPane.add(button);
		
		isim = new JTextField();
		isim.setBounds(14, 314, 96, 19);
		contentPane.add(isim);
		isim.setColumns(10);
		
		soyisim = new JTextField();
		soyisim.setColumns(10);
		soyisim.setBounds(139, 314, 96, 19);
		contentPane.add(soyisim);
		
		tel = new JTextField();
		tel.setColumns(10);
		tel.setBounds(264, 314, 96, 19);
		contentPane.add(tel);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(383, 314, 96, 19);
		contentPane.add(mail);
		
		JComboBox Cinsiyeti = new JComboBox();
		Cinsiyeti.setBounds(517, 313, 85, 21);
		Cinsiyeti.addItem("F");
		Cinsiyeti.addItem("M");
		contentPane.add(Cinsiyeti);
		
		JComboBox tipi = new JComboBox();
		tipi.setBounds(264, 434, 98, 21);
		tipi.addItem("Business");
		tipi.addItem("Economic");
		contentPane.add(tipi);
		
		JLabel lblNewLabel_3 = new JLabel("İsim:");
		lblNewLabel_3.setBounds(10, 291, 100, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Soyisim:");
		lblNewLabel_3_1.setBounds(139, 295, 100, 21);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("GSM:");
		lblNewLabel_3_2.setBounds(264, 295, 100, 21);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Mail Adresi:");
		lblNewLabel_3_3.setBounds(383, 295, 100, 21);
		contentPane.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Cinsiyeti:");
		lblNewLabel_3_4.setBounds(517, 295, 100, 21);
		contentPane.add(lblNewLabel_3_4);
		
		
		JLabel lblNewLabel_3_6 = new JLabel("Koltuk Numarasi:");
		lblNewLabel_3_6.setBounds(135, 417, 100, 21);
		contentPane.add(lblNewLabel_3_6);
		
		JSpinner dgGun = new JSpinner();
		dgGun.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		dgGun.setBounds(10, 373, 41, 21);
		contentPane.add(dgGun);
		
		JSpinner dgAy = new JSpinner();
		dgAy.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		dgAy.setBounds(50, 373, 41, 21);
		contentPane.add(dgAy);
		
		JSpinner dgYil = new JSpinner();
		dgYil.setModel(new SpinnerNumberModel(2022, 1822, 2022, 1));
		dgYil.setBounds(90, 374, 55, 21);
		contentPane.add(dgYil);
		
		JLabel lblNewLabel_4 = new JLabel("Dogum Tarihi:");
		lblNewLabel_4.setBounds(10, 356, 81, 20);
		contentPane.add(lblNewLabel_4);
		
		
		JButton btnNewButton = new JButton("BİLET EKLE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dogumgunu = (int)dgYil.getValue()+"-"+(int)dgAy.getValue()+"-"+(int)dgGun.getValue();
				yolcu biletsahibi = new yolcu(isim.getText(), soyisim.getText(), tel.getText(), dogumgunu, mail.getText(), (String)Cinsiyeti.getSelectedItem());
				String seyehatNo = (String) table.getValueAt(table.getSelectedRow(), 0);
				
				int result = JOptionPane.showConfirmDialog(null ,"Seyehat Numarasi:"+seyehatNo+" Yolcu Bilgisi:"+biletsahibi+" bilet eklenmek icin onay bekliyor.", "Onay Gerekli.",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){			     
			            	
							bilet yeniBilet = new bilet(biletsahibi, seyehatNo, (String)tipi.getSelectedItem(), (String)bosKoltukListe.getSelectedItem());
  	
			            }				
			}
		});
		btnNewButton.setBounds(484, 377, 118, 43);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Uygun Seferler:");
		lblNewLabel_5.setBounds(230, 21, 318, 13);
		contentPane.add(lblNewLabel_5);
		
	}
	
	public String DoluKoltuklar(int seyehatNo) {
		String sonuc = "";
		String sorgu = "select koltuknumarasi from bilet where seyehatnumarasi = " + seyehatNo;
		ResultSet rs = veritabani.calistir(sorgu);
		try {
			while(rs.next()) {
			sonuc += rs.getString("koltuknumarasi") + ", ";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sonuc;
	}
	
	public void BosKoltuklar(int seyehatNo, JComboBox bosKoltukListe) {
		int koltukSayisi, siraGenisligi;
		bosKoltukListe.removeAllItems();
		String sorgu = "select koltuksayisi, siragenisligi from seferkoltukbilgileri where seyehatnumarasi ="+seyehatNo;
		String doluKoltuklar = DoluKoltuklar(seyehatNo);
		
		ResultSet rs = veritabani.calistir(sorgu);
		
		try {
			rs.next();
			koltukSayisi = rs.getInt("koltuksayisi");
			siraGenisligi = rs.getInt("siraGenisligi");
			
			for(int sayi = 1, siraNo = 1; sayi <= koltukSayisi ;) {
				
				for(char harf = 'A'; harf < 'A' + siraGenisligi; harf++) {
					
					if(sayi < koltukSayisi) {
						
						String koltuk = "" + siraNo + harf; 
						if(!doluKoltuklar.contains(koltuk)) {
							bosKoltukListe.addItem(koltuk);
						}
						
					}
					sayi++;
				}
				siraNo++;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
