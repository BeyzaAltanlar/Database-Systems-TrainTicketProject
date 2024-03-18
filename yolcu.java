package bil372;

import java.sql.ResultSet;
import java.sql.SQLException;

public class yolcu {
	
	int yolcunumarasi;
	String adi;
	String soyadi;
	String telefonnumarasi;
	String dogumtarihi;
	String mailadresi;
	String cinsiyeti;
	
	yolcu(String adi, String soyadi, String telefonnumarasi, String dogumtarihi, String mailadresi, String cinsiyeti){
		
		this.adi = adi;
		this.soyadi = soyadi;
		this.telefonnumarasi = telefonnumarasi;
		this.dogumtarihi = dogumtarihi;
		this.mailadresi = mailadresi;
		this.cinsiyeti = cinsiyeti;	
		
		ResultSet contains = veritabani.calistir("select * from yolcu where adi = '"+adi+"' AND soyadi = '"+soyadi+"' AND telefonnumarasi = '"+telefonnumarasi+"' AND dogumtarihi = '"+dogumtarihi+"';");
		
		try {
			if(contains.next()) {
				System.out.println("YİS");
				this.yolcunumarasi = contains.getInt("yolcunumarasi");
			}else {
				int sayi = (int)(Math.random()*10000);
				this.yolcunumarasi = 1 + veritabani.max("yolcu", "yolcunumarasi");
				veritabani.guncelle("insert into yolcu values ( "+ yolcunumarasi+", '"+adi+"', '"+soyadi+"', "+telefonnumarasi+", '"+dogumtarihi+"', '"+mailadresi+"', '"+cinsiyeti+"');");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}

}
