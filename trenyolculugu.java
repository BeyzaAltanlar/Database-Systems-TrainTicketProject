package bil372;

public class trenyolculugu {
	
	int seyahatnumarasi;
	String kalkisistasyonu;
	String varisistasyonu;
	String atanantren;
	String toplamyolcusayisi;
	String tarih;
	String kalkissaati;
	String varissaati;
	
	trenyolculugu( String kalkisistasyonu, String varisistasyonu, String atanantren, String tarih, String kalkissaati , String kalkisdakika){
		//TODO
		this.seyahatnumarasi = 1 + veritabani.max("trenyolculugu", "seyehatnumarasi");
		
		int [] varisTahmini = tren.IleriSar(1, 1, 2000, (int)Integer.parseInt(kalkissaati), (int)Integer.parseInt(kalkisdakika), 3 + (int)(Math.random()*12));
		
		String tahminSaat = "";
		String tahminDakika = "";

		if(varisTahmini[3] < 10) {
			tahminSaat = "0"+varisTahmini[3];
		}else {
			tahminSaat = ""+varisTahmini[3];
		}
		
		if(varisTahmini[4] < 10) {
			tahminDakika = "0"+varisTahmini[4];
		}else {
			tahminDakika = ""+varisTahmini[4];
		}
		if(kalkissaati.length() == 1) {
			kalkissaati = "0"+kalkissaati;
		}
		if(kalkisdakika.length() == 1) {
			kalkisdakika = "0"+kalkisdakika;
		}
		
		this.varissaati = tahminSaat + ":" + tahminDakika;
		
		this.kalkisistasyonu = kalkisistasyonu;
		this.varisistasyonu = varisistasyonu;
		this.atanantren = atanantren;
		
		//TODO 
		this.toplamyolcusayisi = ""+300;
		
		this.tarih = tarih;
		this.kalkissaati = kalkissaati + ":" + kalkisdakika;
		
		trenyolculuguKaydet();
	}
	
	public void trenyolculuguKaydet() {
		String sorgu = "insert into trenyolculugu values( " + this.seyahatnumarasi + ", " + this.kalkisistasyonu + ", " 
				+ this.varisistasyonu + ", " + this.atanantren + ", " + this.toplamyolcusayisi + " , '" + this.tarih + "', '" + this.kalkissaati +"', '" + this.varissaati +"');";
		
		veritabani.guncelle(sorgu);
	}

}
