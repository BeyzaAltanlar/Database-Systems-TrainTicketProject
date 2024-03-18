package bil372;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class form extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form frame = new form();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	public form() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Bilet Ekle");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BiletEklemeEkrani frame = new BiletEklemeEkrani();
					frame.setVisible(true);
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 10, 240, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Bilet İptali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BiletSilmeEkrani frame = new BiletSilmeEkrani();
					frame.setVisible(true);
				} catch (Exception y) {
					y.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(250, 10, 240, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sefer Ekle");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SeferEklemeEkranı frame = new SeferEklemeEkranı();
					frame.setVisible(true);
				} catch (Exception z) {
					z.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(0, 89, 240, 60);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Sefer İptali");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SeferSilmeEkrani frame = new SeferSilmeEkrani();
					frame.setVisible(true);
				} catch (Exception x) {
					x.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(250, 89, 240, 60);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Tren Ekle");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TrenEkleme frame = new TrenEkleme();
					frame.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(0, 170, 240, 60);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Tren Kaldır");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						TrenSilme frame = new TrenSilme();
						frame.setVisible(true);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				
			}
		});
		btnNewButton_5.setBounds(250, 170, 240, 60);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Istasyon Ekle");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					istasyonEkleme frame = new istasyonEkleme();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_6.setBounds(0, 240, 240, 60);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("İstasyon Kaldir");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IstasyonKaldir frame = new IstasyonKaldir();
					frame.setVisible(true);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnNewButton_7.setBounds(250, 240, 240, 60);
		contentPane.add(btnNewButton_7);		
		
	}
}
