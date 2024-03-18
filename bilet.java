package bil372;

import java.sql.ResultSet;
import java.sql.SQLException;

public class bilet {
	
	yolcu biletsahibi;
	String sefer;
	String yolcutipi;
	String koltuknumarasi;
	String biletucreti;
	
	bilet(yolcu biletsahibi, String sefer, String yolcutipi, String koltuknumarasi){
		
ResultSet contains = veritabani.calistir("select * from bilet where seyehatnumarasi = "+sefer+" AND koltuknumarasi = '"+koltuknumarasi+"' ;");
		//TO-DO:
		this.biletucreti = ""+100;
		try {
			if(contains.next()) {
				System.out.println("DOLU KOLTUK");
			}else {
				veritabani.guncelle("insert into bilet values ( "+ biletsahibi.yolcunumarasi+", "+sefer+", '"+yolcutipi+"', '"+koltuknumarasi+"', '"+this.biletucreti+"');");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.biletsahibi = biletsahibi;
		this.sefer = sefer;
		this.yolcutipi = yolcutipi;
		this.koltuknumarasi = koltuknumarasi;
		
		
	}
	

}
