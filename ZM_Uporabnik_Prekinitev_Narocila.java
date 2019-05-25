/***********************************************************************
 * Module:  ZM_Uporabnik_Prekinitev_Narocila.java
 * Author:  avgustin
 * Purpose: Defines the Class ZM_Uporabnik_Prekinitev_Narocila
 ***********************************************************************/

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** @pdOid 42b20a86-807a-4f7b-bf24-8c79ab966ef3 */
public class ZM_Uporabnik_Prekinitev_Narocila {
   /** @pdRoleInfo migr=no name=K_Prekinitev_Narocila assc=Association_4 coll=java.util.Collection impl=java.util.HashSet mult=1..1 */
   public K_Prekinitev_Narocila k_Prekinitev_Narocila;

   /** @pdOid f524b8c6-764a-430e-a95e-ef20ba6267f7 */
   public String[][] PrikaziNarocila() {
      // prikazi narocila na Swingu
      Narocilo[] narocila = k_Prekinitev_Narocila.VrniNarocila();
	  String[][] string2D = new String[narocila.length][];
      for(int i = 0; i < narocila.length; i++){
         Date datum_narocila = narocila[i].get_datum_narocila();
         String stanje_narocila = narocila[i].get_stanje_narocila();
         String nacin_dostave = narocila[i].get_nacin_dostave();
         String nacin_placila = narocila[i].get_nacin_placila();
         float popust = narocila[i].get_popust();
         DateFormat df = new SimpleDateFormat("dd. MM. yyyy");
         float cena = 0;
      
         for(String[] artikel : PrikaziIzbranoNarocilo(i))
         {
            cena += Float.parseFloat(artikel[4]);
         }
         
         String[] narocilo = { "Narocilo #" + Integer.toString(i + 1), df.format(datum_narocila), stanje_narocila, nacin_dostave, nacin_placila, Float.toString(cena) };
         string2D[i] = narocilo;
      }
      return string2D;
   }

   /** @param indexNarocila
    * @pdOid c96b17ab-03d5-4ff0-9bd8-95b4f638e3cd */
   public String[][] PrikaziIzbranoNarocilo(int indexNarocila) {
      PodrobnoNarocilo podrobnoNarocilo = k_Prekinitev_Narocila.VrniNarocilo(indexNarocila);
      Narocilo narocilo = podrobnoNarocilo.narocilo;

      Date datum_narocila = narocilo.get_datum_narocila();
      String stanje_narocila = narocilo.get_stanje_narocila();
      String nacin_dostave = narocilo.get_nacin_dostave();
      String nacin_placila = narocilo.get_nacin_placila();
      float popust = narocilo.get_popust();
      
      Naroceni_Artikel naroceniArtikli[] = podrobnoNarocilo.naroceniArtikli;
      Artikel trenutniArtikli[] = podrobnoNarocilo.trenutniArtikli;
      String[][] orderInfo = new String[naroceniArtikli.length][];
      
      for(int i = 0; i < naroceniArtikli.length; i++){
         float cena_artikla = naroceniArtikli[i].getCena();
         float popust_artikla =  naroceniArtikli[i].getPopust();
         int kolicina_artikla = naroceniArtikli[i].getKolicina();
         String ime_artikla = trenutniArtikli[i].getIme();
         String kategorija_artikla = trenutniArtikli[i].getKategorija();
         String opis_artikla = trenutniArtikli[i].getOpis();
         
         String[] itemInfo = { ime_artikla, Integer.toString(kolicina_artikla), Float.toString(cena_artikla), Float.toString(popust_artikla), Float.toString(cena_artikla * (1 - popust_artikla/100) * kolicina_artikla) };
         orderInfo[i] = itemInfo;
      }
      return orderInfo;
   }
   
   /** @param indexNarocila
    * @pdOid 8a0dc8c6-c05f-432f-a1d3-7425ea984ef9 */
   public boolean PrekiniNarocilo(int indexNarocila) {
      return k_Prekinitev_Narocila.PrekiniNarocilo(indexNarocila);
   }
   
   /** @param besedilo
    * @pdOid 1d0acfa5-5d24-4e64-ac5f-ff1790c8fd60 */
   public String PrikaziObvestilo(String besedilo) {
      return besedilo;
   }
   
   /** @pdOid b4497ab1-22ca-41a5-8ed7-67c122572830 */
   public String niNarocil() {
      return PrikaziObvestilo("Ni obstojecih narocil!");
   }

   /** @pdGenerated default parent getter */
   public K_Prekinitev_Narocila getK_Prekinitev_Narocila() {
      return k_Prekinitev_Narocila;
   }

   public void generirajPodatke(){
      Artikel artikel_1 = new Artikel("Nvidia GTX 970", "Grafična kartica", "Zelo dobra grafična kartica.", 350.0f, 10, 4 );
      Artikel artikel_2 = new Artikel("Intel i7-4790K Core", "Procesor", "Super a ze malo star procesor.", 270.0f, 30, 1 );
      Artikel artikel_3 = new Artikel("BarraCuda Hard Drive 1T", "Trdi disk", "These 7,200 RPM drives are available  with data rates up to 160 MB/s for fast file transfers.", 120, 4, 10 );
      Artikel artikel_4 = new Artikel("Sennheiser HD 598", "Slušalke", "Premium, audiophile-grade over-ear, open back headphones", 290.0f, 0, 2 );
      Artikel artikel_5 = new Artikel("Logitech G502 Proteus Spectrum", "Miška", "gralna miška Logitech G502 Proteus Spectrum vam ponuja maksimalno natančnost sledenja z ekskluzivno tehnologijo Logitech G Delta Zero™ optičnega senzorja se lahko zanesete na natančno obnašanje miške. Na voljo imate 5 nastavitev od 200 – 12,000 DPI.", 49.99f, 7, 3 );
      // narocilo_1
      Naroceni_Artikel nArtikel_1 = new Naroceni_Artikel(350, 5, 1);
      Naroceni_Artikel nArtikel_2 = new Naroceni_Artikel(270, 20, 1);
      Naroceni_Artikel nArtikel_3 = new Naroceni_Artikel(120, 10, 2);
      // narocilo_2
      Naroceni_Artikel nArtikel_4 = new Naroceni_Artikel(290, 0, 1);
      Naroceni_Artikel nArtikel_5 = new Naroceni_Artikel(55, 10, 1);
      // narocilo_3
      Naroceni_Artikel nArtikel_6 = new Naroceni_Artikel(270, 7, 1);
      Naroceni_Artikel nArtikel_7 = new Naroceni_Artikel(120, 4, 1);
      Naroceni_Artikel nArtikel_8 = new Naroceni_Artikel(280, 0, 1);



      Date datum_narocila_1 = parseDate("2019-05-23");
      Narocilo narocilo_1 = new Narocilo(datum_narocila_1, "V obdelavi", "Posta", "Kartica", 3);
      Date datum_narocila_2 = parseDate("2019-05-20");
      Narocilo narocilo_2 = new Narocilo(datum_narocila_2, "Oddano", "Posta", "Gotovina", 6);
      Date datum_narocila_3 = parseDate("2019-04-28");
      Narocilo narocilo_3 = new Narocilo(datum_narocila_3, "Koncano", "Osebni Prevzem", "Gotovina", 4);

      Uporabnik uporabnik = new Uporabnik("Janez", "Novak", "Litijska cesta 49, 1000 Ljubljana", "+386 40 141 652", "janez.novak@gmail.com");

      K_Posodobitev_Zaloge k_posodobitev_zaloge = new K_Posodobitev_Zaloge();

      SV_Prekinitev_Narocila_Bancni_Sistem sv_prekinitev_narocila_bancni_sistem = new SV_Prekinitev_Narocila_Bancni_Sistem();

      // se povezave naredi zdej med temi objekti!!!

      artikel_1.addNaroceni_Artikel(nArtikel_1);
      artikel_2.addNaroceni_Artikel(nArtikel_2);
      artikel_3.addNaroceni_Artikel(nArtikel_3);
      artikel_4.addNaroceni_Artikel(nArtikel_4);
      artikel_5.addNaroceni_Artikel(nArtikel_5);
      artikel_2.addNaroceni_Artikel(nArtikel_6);
      artikel_3.addNaroceni_Artikel(nArtikel_7);
      artikel_4.addNaroceni_Artikel(nArtikel_8);

      nArtikel_1.setArtikel(artikel_1);
      nArtikel_2.setArtikel(artikel_2);
      nArtikel_3.setArtikel(artikel_3);
      nArtikel_4.setArtikel(artikel_4);
      nArtikel_5.setArtikel(artikel_5);
      nArtikel_6.setArtikel(artikel_2);
      nArtikel_7.setArtikel(artikel_3);
      nArtikel_8.setArtikel(artikel_4);


      nArtikel_1.setNarocilo(narocilo_1);
      nArtikel_2.setNarocilo(narocilo_1);
      nArtikel_3.setNarocilo(narocilo_1);
      nArtikel_4.setNarocilo(narocilo_2);
      nArtikel_5.setNarocilo(narocilo_2);
      nArtikel_6.setNarocilo(narocilo_3);
      nArtikel_7.setNarocilo(narocilo_3);
      nArtikel_8.setNarocilo(narocilo_3);

      narocilo_1.addNaroceni_Artikel(nArtikel_1);
      narocilo_1.addNaroceni_Artikel(nArtikel_2);
      narocilo_1.addNaroceni_Artikel(nArtikel_3);
      narocilo_2.addNaroceni_Artikel(nArtikel_4);
      narocilo_2.addNaroceni_Artikel(nArtikel_5);
      narocilo_3.addNaroceni_Artikel(nArtikel_6);
      narocilo_3.addNaroceni_Artikel(nArtikel_7);
      narocilo_3.addNaroceni_Artikel(nArtikel_8);

      narocilo_1.setUporabnik(uporabnik);
      narocilo_2.setUporabnik(uporabnik);
      narocilo_3.setUporabnik(uporabnik);

      uporabnik.addNarocilo(narocilo_1);
      uporabnik.addNarocilo(narocilo_2);
      uporabnik.addNarocilo(narocilo_3);

      uporabnik.setK_Prekinitev_Narocila(k_Prekinitev_Narocila);
      
      k_Prekinitev_Narocila = new K_Prekinitev_Narocila();
      k_Prekinitev_Narocila.setK_Posodobitev_Zaloge(k_posodobitev_zaloge);
      k_Prekinitev_Narocila.setNarocila(uporabnik.narocila);
      k_Prekinitev_Narocila.setSV_Prekinitev_Narocila_Bancni_Sistem(sv_prekinitev_narocila_bancni_sistem);
      k_Prekinitev_Narocila.setUporabnik(uporabnik);
      k_Prekinitev_Narocila.setZM_Uporabnik_Prekinitev_Narocila(this);
   }

   public static Date parseDate(String date) {
      try {
         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
      } catch (ParseException e) {
         return null;
      }
   }
   
   /** @pdGenerated default parent setter
     * @param newK_Prekinitev_Narocila */
   public void setK_Prekinitev_Narocila(K_Prekinitev_Narocila newK_Prekinitev_Narocila) {
      if (this.k_Prekinitev_Narocila == null || !this.k_Prekinitev_Narocila.equals(newK_Prekinitev_Narocila))
      {
         if (this.k_Prekinitev_Narocila != null)
            this.k_Prekinitev_Narocila.setZM_Uporabnik_Prekinitev_Narocila(null);
         this.k_Prekinitev_Narocila = newK_Prekinitev_Narocila;
         if (this.k_Prekinitev_Narocila != null)
            this.k_Prekinitev_Narocila.setZM_Uporabnik_Prekinitev_Narocila(this);
      }
   }

}