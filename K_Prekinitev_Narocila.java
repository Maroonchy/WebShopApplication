/***********************************************************************
 * Module:  K_Prekinitev_Narocila.java
 * Author:  avgustin
 * Purpose: Defines the Class K_Prekinitev_Narocila
 ***********************************************************************/

import java.util.ArrayList;
import java.util.Date;

/** @pdOid ce3883e5-d7ca-4666-af6e-fe0179636066 */
public class K_Prekinitev_Narocila {
   /** @pdRoleInfo migr=no name=SV_Prekinitev_Narocila_Bancni_Sistem assc=Association_5 coll=java.util.Collection impl=java.util.HashSet mult=0..1 */
   public SV_Prekinitev_Narocila_Bancni_Sistem_SIM sV_Prekinitev_Narocila_Bancni_Sistem;
   /** @pdRoleInfo migr=no name=Narocilo assc=Association_6 coll=java.util.Collection impl=java.util.HashSet mult=0..* */
   public ArrayList<Narocilo> narocila;
   /** @pdRoleInfo migr=no name=Uporabnik assc=Association_7 coll=java.util.Collection impl=java.util.HashSet mult=1..1 */
   public Uporabnik uporabnik;
   /** @pdRoleInfo migr=no name=K_Posodobitev_Zaloge assc=Association_8 coll=java.util.Collection impl=java.util.HashSet mult=1..1 */
   public K_Posodobitev_Zaloge_SIM k_Posodobitev_Zaloge;
   /** @pdRoleInfo migr=no name=ZM_Uporabnik_Prekinitev_Narocila assc=Association_4 coll=java.util.Collection impl=java.util.HashSet mult=1..1 side=A */
   public ZM_Uporabnik_Prekinitev_Narocila zM_Uporabnik_Prekinitev_Narocila;

   private Narocilo izbranoNarocilo;
   
   /** @pdOid 01ff605d-adf7-4ced-9ee6-2f34ebf3d29b */
   public boolean PrekiniNarocilo(int indexNarocila){
       Narocilo narocilo = narocila.get(indexNarocila);
       // 12 VrniNaroceneArtikle
       Naroceni_Artikel naroceniArtikli[] = narocilo.vrniNaroceneArtikle();
       int n = naroceniArtikli.length;
       Artikel trenutniArtikli[] = new Artikel[n];
       // 13 Vrni Artikel
       for(int i = 0; i < n; i++)
           trenutniArtikli[i] = naroceniArtikli[i].vrniArtikel();

       // 14 PreveriStatusNarocila
       izbranoNarocilo = narocilo;
       if(PreveriStatusNarocila() == false)
           return false;

       // 15 NastaviStatusNarocila
       narocilo.NastaviStatusNarocila("preklicano");

       // 16 Pristejzalogi
       k_Posodobitev_Zaloge.PristejZalogi(naroceniArtikli);

       // 17 PreveriNacinPlacila
       if(PreveriNacinPlacila()){
           // 18 VrniDenar
           sV_Prekinitev_Narocila_Bancni_Sistem.VrniDenar(uporabnik, narocilo);
       }

      return true;
   }
   
   /** @pdOid cb7d34f7-01ad-4d78-b4b8-05f972cdba55 */
   public Narocilo[] VrniNarocila() {
      return uporabnik.VrniNarocila();
   }

   //Spremenit mors return type iz Narocilo na PodrobnoNarocilo
   /** @param indexNarocila
    * @pdOid 9c309625-a51c-4784-a7d4-76a34edf17ac */
   public PodrobnoNarocilo VrniNarocilo(int indexNarocila) {
      Narocilo narocilo = narocila.get(indexNarocila);
      Naroceni_Artikel naroceniArtikli[] = narocilo.vrniNaroceneArtikle();
      int n = naroceniArtikli.length;
      Artikel trenutniArtikli[] = new Artikel[n];
      for(int i = 0; i < n; i++)
          trenutniArtikli[i] = naroceniArtikli[i].vrniArtikel();
      Date date = k_Posodobitev_Zaloge.VrniDatumDostave(trenutniArtikli);
      PodrobnoNarocilo tmp = new PodrobnoNarocilo(narocilo, naroceniArtikli, trenutniArtikli, date);
      return tmp;
   }
   
   /** @pdOid 8ec41d16-fec7-4b0c-bf2f-78137d0ff72c */
   public boolean PreveriStatusNarocila() {
       return izbranoNarocilo.get_stanje_narocila().equals("v obdelavi");
   }
   
   /** @pdOid b380721d-37f4-49ee-a088-292ddb899a4f */
   public boolean PreveriNacinPlacila() {
       return izbranoNarocilo.get_nacin_placila().equals("kartica");
   }
   
   /** @pdOid eee77dd9-cbca-4552-8e28-9bf0ef4c25cb */
   public boolean niNarocil() {
      zM_Uporabnik_Prekinitev_Narocila.niNarocil();
      return false;
   }
   
   
   /** @pdGenerated default parent getter */
   public SV_Prekinitev_Narocila_Bancni_Sistem_SIM getSV_Prekinitev_Narocila_Bancni_Sistem() {
      return sV_Prekinitev_Narocila_Bancni_Sistem;
   }
   
   /** @pdGenerated default parent setter
     * @param newSV_Prekinitev_Narocila_Bancni_Sistem_SIM */
   public void setSV_Prekinitev_Narocila_Bancni_Sistem(SV_Prekinitev_Narocila_Bancni_Sistem_SIM newSV_Prekinitev_Narocila_Bancni_Sistem) {
      this.sV_Prekinitev_Narocila_Bancni_Sistem = newSV_Prekinitev_Narocila_Bancni_Sistem;
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Narocilo> getNarocila() {
      if (narocila == null)
         narocila = new java.util.ArrayList<Narocilo>();
      return narocila;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorNarocilo() {
      if (narocila == null)
         narocila = new java.util.ArrayList<Narocilo>();
      return narocila.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newNarocilo */
   public void setNarocila(java.util.Collection<Narocilo> newNarocilo) {
      removeAllNarocilo();
      for (java.util.Iterator iter = newNarocilo.iterator(); iter.hasNext();)
         addNarocilo((Narocilo)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newNarocilo */
   public void addNarocilo(Narocilo newNarocilo) {
      if (newNarocilo == null)
         return;
      if (this.narocila == null)
         this.narocila = new java.util.ArrayList<Narocilo>();
      if (!this.narocila.contains(newNarocilo))
         this.narocila.add(newNarocilo);
   }
   
   /** @pdGenerated default remove
     * @param oldNarocilo */
   public void removeNarocilo(Narocilo oldNarocilo) {
      if (oldNarocilo == null)
         return;
      if (this.narocila != null)
         if (this.narocila.contains(oldNarocilo))
            this.narocila.remove(oldNarocilo);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllNarocilo() {
      if (narocila != null)
         narocila.clear();
   }
   /** @pdGenerated default parent getter */
   public Uporabnik getUporabnik() {
      return uporabnik;
   }
   
   /** @pdGenerated default parent setter
     * @param newUporabnik */
   public void setUporabnik(Uporabnik newUporabnik) {
      this.uporabnik = newUporabnik;
   }
   /** @pdGenerated default parent getter */
   public K_Posodobitev_Zaloge_SIM getK_Posodobitev_Zaloge() {
      return k_Posodobitev_Zaloge;
   }
   
   /** @pdGenerated default parent setter
     * @param newK_Posodobitev_Zaloge_SIM */
   public void setK_Posodobitev_Zaloge(K_Posodobitev_Zaloge_SIM newK_Posodobitev_Zaloge) {
      this.k_Posodobitev_Zaloge = newK_Posodobitev_Zaloge;
   }
   /** @pdGenerated default parent getter */
   public ZM_Uporabnik_Prekinitev_Narocila getZM_Uporabnik_Prekinitev_Narocila() {
      return zM_Uporabnik_Prekinitev_Narocila;
   }
   
   /** @pdGenerated default parent setter
     * @param newZM_Uporabnik_Prekinitev_Narocila */
   public void setZM_Uporabnik_Prekinitev_Narocila(ZM_Uporabnik_Prekinitev_Narocila newZM_Uporabnik_Prekinitev_Narocila) {
      if (this.zM_Uporabnik_Prekinitev_Narocila == null || !this.zM_Uporabnik_Prekinitev_Narocila.equals(newZM_Uporabnik_Prekinitev_Narocila))
      {
         if (this.zM_Uporabnik_Prekinitev_Narocila != null)
            this.zM_Uporabnik_Prekinitev_Narocila.setK_Prekinitev_Narocila(null);
         this.zM_Uporabnik_Prekinitev_Narocila = newZM_Uporabnik_Prekinitev_Narocila;
         if (this.zM_Uporabnik_Prekinitev_Narocila != null)
            this.zM_Uporabnik_Prekinitev_Narocila.setK_Prekinitev_Narocila(this);
      }
   }

}