package bil372;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;


import javax.swing.text.JTextComponent;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TrenEkleme extends JFrame {

	private JPanel KoltukSay;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TrenEkleme() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 453);
		KoltukSay = new JPanel();
		KoltukSay.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(KoltukSay);
		KoltukSay.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(39, 257, 135, -56);
		KoltukSay.add(textArea);
		
		JLabel Type = new JLabel("Tren tipi");
		Type.setBounds(180, 175, 79, 19);
		KoltukSay.add(Type);
		
		JLabel lblNewLabel_4 = new JLabel("Koltuk");
		lblNewLabel_4.setBounds(314, 177, 59, 19);
		KoltukSay.add(lblNewLabel_4);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Kara Tren", "Hizli Tren", "Marmaray"}));
		comboBox_3.setBounds(180, 203, 79, 21);
		KoltukSay.add(comboBox_3);
		
		JButton btnNewButton = new JButton("EKLE"); //EKLE BUTONU 
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(336, 372, 85, 21);
		KoltukSay.add(btnNewButton);
		
		JSpinner koltukSpinner = new JSpinner();
		koltukSpinner.setModel(new SpinnerNumberModel(100, 100, 400, 1));
		koltukSpinner.setBounds(314, 204, 50, 20);
		KoltukSay.add(koltukSpinner);
		
		JSpinner siraSpinner = new JSpinner();
		siraSpinner.setModel(new SpinnerNumberModel(3, 3, 20, 1));
		siraSpinner.setBounds(418, 204, 41, 20);
		KoltukSay.add(siraSpinner);
		
		
		
		
		btnNewButton.addActionListener(new ActionListener() { // EKLE 
			public void actionPerformed(ActionEvent e) {
			   // trenNo = TrenNumarasi.getText();
				String trenTipi = (String) comboBox_3.getSelectedItem();
				int koltukSayisi=(int) koltukSpinner.getValue();
				int sira= (int) siraSpinner.getValue();
				
				int result = JOptionPane.showConfirmDialog(null ,"Tip:"+trenTipi+", Koltuk Sayisi: "+ koltukSayisi + ", Sira Genisligi:"+sira+" ozelliklerine sahip tren eklenmek icin onay bekleniyor.", "Onay gerekli.",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	
			            	tren t= new tren(trenTipi, koltukSayisi,sira);
	
			            }		
				
				
			}
		});  
		btnNewButton.setBounds(484, 377, 118, 43);
		KoltukSay.add(btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("Sıra genişliği");
		lblNewLabel.setBounds(415, 177, 66, 19);
		KoltukSay.add(lblNewLabel);
		
		/*JButton btnNewButton = new JButton("Listele");
		btnNewButton.setBounds(49, 198, 85, 21);
		contentPane.add(btnNewButton); */ 
		
		DefaultTableModel model = new DefaultTableModel();
		Object[] columns = {"Seyehat No.","Tarih", "Kalkis Saati", "Varis Saati"};
		Object[] rows = new Object[4];
		
		Button button = new Button("Listele");
		
		
		/*button.addActionListener(new ActionListener() {
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
		*/
		
	}
}

