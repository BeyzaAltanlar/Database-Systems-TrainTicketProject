package bil372;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class istasyonEkleme extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public istasyonEkleme() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elâzığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkâri", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"}));
		comboBox.setBounds(37, 73, 114, 19);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Şehir");
		lblNewLabel.setBounds(37, 36, 71, 30);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(186, 73, 88, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("İlçe");
		lblNewLabel_1.setBounds(186, 45, 63, 21);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(313, 73, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("İstasyon Adı");
		lblNewLabel_2.setBounds(314, 45, 78, 21);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("İstasyon Ekle");
		btnNewButton.setForeground(new Color(0, 64, 128));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setIcon(null);
		btnNewButton.setBounds(280, 200, 129, 30);
		contentPane.add(btnNewButton);
		
		
		btnNewButton.addActionListener(new ActionListener() { // Istasyon Ekle button 
			public void actionPerformed(ActionEvent e) {
			   
				String bulunduguil = (String) comboBox.getSelectedItem();
				String bulunduguilce= textField.getText();
				String istasyonismi=  textField_1.getText();
				
				int result = JOptionPane.showConfirmDialog(null ,""+bulunduguil+" sehrinin "+ bulunduguilce+" ilcesine tren gari eklemek icin onay bekleniyor.", "Onay gerekli.",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	
							trenistasyonu i= new trenistasyonu(bulunduguil, bulunduguilce, istasyonismi);
	
			            }				
			}
		});  
	}
}
