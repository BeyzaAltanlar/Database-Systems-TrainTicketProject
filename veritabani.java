package bil372;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class veritabani {
	static String url = "jdbc:postgresql://localhost:5432/deneme";
	static Connection conn = null;
	
	static Calendar calendar = Calendar.getInstance();
	
	static int GUN = calendar.get(Calendar.DATE);
	static int AY = calendar.get(Calendar.MONTH);
	static int YIL = calendar.get(Calendar.YEAR);
	
	public static String[] sehirler = {"Adana", "Adiyaman", "Afyon", "Agri", "Amasya", "Ankara",
			"Antalya", "Artvin", "Aydin", "Balikesir", "Bilecik", "Bingol", "Bitlis", "Bolu",
			"Burdur", "Bursa", "Canakkale", "Cankiri", "Corum", "Denizli", "Diyarbakir", "Edirne",
			"Elazig", "Erzincan", "Erzurum", "Eskisehir", "Gaziantep", "Giresun", "Gumushane", "Hakkari",
			"Hatay", "Isparta", "Mersin", "Istanbul", "Izmir", "Kars", "Kastamonu", "Kayseri",
			"Kirklareli", "Kirsehir", "Kocaeli", "Konya", "Kutahya", "Malatya", "Manisa", "Kahramanmaras",
			"Mardin", "Mugla", "Mus", "Nevsehir", "Nigde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt",
			"Sinop", "Sivas", "Tekirdag", "Tokat", "Trabzon", "Tunceli", "Sanliurfa", "Usak", "Van", "Yozgat",
			"zonguldak", "aksaray", "bayburt", "karaman", "kirikkale", "batman", "sirnak", "bartin", "ardahan",
			"Igdir", "Yalova", "Karabuk", "Kilis", "Osmaniye", "Duzce"};
	
	static boolean baglan(String id, String sifre) {
		
		try {
			conn = DriverManager.getConnection(url, id, sifre);
			System.out.println("Baglanti gerceklesti");
			return true;
		} catch (SQLException e) {
			System.out.println("Connection error!");
			return false;
		}
	}
	
	static ResultSet calistir(String query) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			System.out.println("Query error!");
			System.out.println(query);

			return null;
		}
		
	}
	
	static void guncelle(String query) {
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			
		} catch (SQLException e) {
			System.out.println("Update error!");
			System.out.println(query);

		}
		
	}
	
	static int max(String table, String column) {
		int sonuc = 0;
		String sorgu = "SELECT MAX("+column+") as max FROM "+ table + " ;";
		ResultSet rs = veritabani.calistir(sorgu);
		try {
			while(rs.next()) {
				
			sonuc = (int)Integer.parseInt(rs.getString("max"));
			
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sonuc;
	}
	
	
		
}
