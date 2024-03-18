package bil372;

public class trenistasyonu {
	
	int istasyonNumarasi;
	String bulunduguil;
	String bulunduguilce;
	String istasyonismi;
	
	trenistasyonu(String bulunduguil, String bulunduguilce, String istasyonismi){
		this.istasyonNumarasi = 1 + veritabani.max("trenistasyonu", "istasyonnumarasi");
		this.bulunduguil = bulunduguil;
		this.bulunduguilce = bulunduguilce;
		this.istasyonismi = istasyonismi;
		
		veritabani.guncelle("insert into trenistasyonu values ( "+istasyonNumarasi+", '" + bulunduguil + "', '"+ bulunduguilce  +"', '"+istasyonismi+"');");
	}
	
	

}
