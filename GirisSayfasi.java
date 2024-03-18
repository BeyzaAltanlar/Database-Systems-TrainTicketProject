package bil372;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GirisSayfasi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisSayfasi frame = new GirisSayfasi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GirisSayfasi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(152, 62, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(153, 113, 95, 19);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Kullanici Adi:");
		lblNewLabel.setBounds(152, 41, 96, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Sifre:");
		lblNewLabel_1.setBounds(152, 90, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Giris Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(veritabani.baglan(textField.getText(), passwordField.getText())) {
					try {
						form frame = new form();
						frame.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}else {
					System.out.println("HATALI GİRİS!");
				}
			}
		});
		btnNewButton.setBounds(152, 173, 96, 21);
		contentPane.add(btnNewButton);
	}
}
