package bil372;

public class tren {
	
	int trenNo;
	String trenTipi;
	int koltukSayisi;
	int siraGenisligi;
	
	public tren(String trenTipi, int koltukSayisi, int siraGenisligi) {
		this.trenNo = 1 + veritabani.max("tren", "trennumarasi");
		this.trenTipi = trenTipi;
		this.koltukSayisi = koltukSayisi;
		this.siraGenisligi = siraGenisligi;
		
		veritabani.guncelle("insert into tren values (" + trenNo + ", '" + trenTipi + "', " + koltukSayisi + ", "+siraGenisligi+" );");
	}
	
	
	public static int[] IleriSar(int gun, int ay, int yil, int saat, int dakika, int trenBakimSuresi) {
		
		int bakimdaGecenGun = 0;
		int bakimdaGecenAy = 0;
		int bakimdaGecenYil = 0;

		saat += trenBakimSuresi;
		
		if(saat > 23) {
			
			bakimdaGecenGun = saat/23;
			saat = saat % 24;
			
			gun += bakimdaGecenGun;
			
			if(gun > 31) {
				
				if(ay == 1 || ay == 3 || ay == 5 || ay == 7 || ay == 8 || ay == 10 || ay == 12 ) {
					
					bakimdaGecenAy = gun / 31;
					gun = gun % 32;
				
				}else if(gun == 2){	
					
					bakimdaGecenAy = gun / 28;
					gun = gun % 29;
					
				}else {
					
					bakimdaGecenAy = gun / 30;
					gun = gun % 31;
					
				}
				
				ay += bakimdaGecenAy;
			
				if(ay > 12) {
					
					bakimdaGecenYil = ay / 12;
					ay = ay % 12;
					
					yil += bakimdaGecenYil;
					
				}
			}
		}
		
		int[] tarihSaat = {gun, ay, yil, saat, dakika};
		
		return tarihSaat;
	}
	
public static int[] GeriyeSar(int gun, int ay, int yil, int saat, int dakika, int seferSuresi) {
				
		if(saat < seferSuresi) {
			
			saat -= seferSuresi;
			while(saat < 0) {
				saat += 24;
				gun--;
			}
						
			while(gun < 0) {
				
				if(ay == 1 || ay == 3 || ay == 5 || ay == 7 || ay == 8 || ay == 10 || ay == 12 ) {
					
					gun += 32;
				
				}else if(gun == 2){	
					
					gun += 29;
					
				}else {
					
					gun += 31;
					
				}
				
				gun += 30;
				ay--;
			}
			
			
			while(ay < 0) {
				ay += 13;
				yil--;
			}
			

		}
		
		int[] tarihSaat = {gun, ay, yil, saat, dakika};
		
		return tarihSaat;
	}

}
