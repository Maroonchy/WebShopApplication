/***********************************************************************
 * Module:  Narocilo.java
 * Author:  avgustin
 * Purpose: Defines the Class Narocilo
 ***********************************************************************/

import java.util.*;

/** @pdOid 1538442e-7b82-44d7-8c48-5593968e1d37 */
public class Narocilo {
   /** @pdOid 4ec2fa82-4686-448a-9228-c44cc6efd95c */
   private Date datum_narocila;
   /** @pdOid 59db3f38-dce1-4ca7-8c7b-f09f7bef3bbf */
   private String stanje_narocila;
   /** @pdOid f17ebf04-511a-4f12-98eb-d448bc20ce12 */
   private String nacin_dostave;
   /** @pdOid f47ae4ee-0eef-4a44-9b88-e209fff85512 */
   private String nacin_placila;
   /** @pdOid ff7ebb0f-2a49-44f7-a534-ad4dae0a9f36 */
   private float popust;
   /** @pdOid 223c6dca-9e2a-42b3-a142-1daaf28a51ae */
   private String razlog_preklica;
   
   /** @pdRoleInfo migr=no name=Naroceni_Artikel assc=NarociloNaroceniArtikel coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public ArrayList<Naroceni_Artikel> naroceni_Artikel;
   /** @pdRoleInfo migr=no name=Uporabnik assc=UporabnikNarocilo coll=java.util.Collection mult=1..1 side=A */
   public Uporabnik uporabnik;


   Narocilo(Date datum_narocila, String stanje_narocila, String nacin_dostave, String nacin_placila, float popust){
      this.datum_narocila = datum_narocila;
      this.stanje_narocila = stanje_narocila;
      this.nacin_dostave = nacin_dostave;
      this.nacin_placila = nacin_placila;
      this.popust = popust;
   }

   /** @pdOid 9a4f7ef5-32c5-45db-bf43-685198e4b37f */
   public Naroceni_Artikel[] vrniNaroceneArtikle() {
      return (Naroceni_Artikel[]) naroceni_Artikel.toArray(new Naroceni_Artikel[naroceni_Artikel.size()]);
   }
   
   /** @param status
    * @pdOid a4afd7e5-48a6-4a37-af43-661fe746ec57 */
   public boolean NastaviStatusNarocila(String status) {
      if(status.equals("v obdelavi") || status.equals("oddano") || status.equals("preklicano") || status.equals("koncano")) {
         this.stanje_narocila = status;
         return true;
      }
      return false;
   }


   // My getters
   public Date get_datum_narocila(){
      return datum_narocila;
   }
   public String get_stanje_narocila(){
      return stanje_narocila;
   }
   public String get_nacin_dostave(){
      return nacin_dostave;
   }
   public String get_nacin_placila(){
      return nacin_placila;
   }
   public float get_popust(){
      return popust;
   }

   
   
   /** @pdGenerated default getter */
   public java.util.List<Naroceni_Artikel> getNaroceni_Artikel() {
      if (naroceni_Artikel == null)
         naroceni_Artikel = new java.util.ArrayList<Naroceni_Artikel>();
      return naroceni_Artikel;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorNaroceni_Artikel() {
      if (naroceni_Artikel == null)
         naroceni_Artikel = new java.util.ArrayList<Naroceni_Artikel>();
      return naroceni_Artikel.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newNaroceni_Artikel */
   public void setNaroceni_Artikel(java.util.List<Naroceni_Artikel> newNaroceni_Artikel) {
      removeAllNaroceni_Artikel();
      for (java.util.Iterator iter = newNaroceni_Artikel.iterator(); iter.hasNext();)
         addNaroceni_Artikel((Naroceni_Artikel)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newNaroceni_Artikel */
   public void addNaroceni_Artikel(Naroceni_Artikel newNaroceni_Artikel) {
      if (newNaroceni_Artikel == null)
         return;
      if (this.naroceni_Artikel == null)
         this.naroceni_Artikel = new java.util.ArrayList<Naroceni_Artikel>();
      if (!this.naroceni_Artikel.contains(newNaroceni_Artikel))
      {
         this.naroceni_Artikel.add(newNaroceni_Artikel);
         newNaroceni_Artikel.setNarocilo(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldNaroceni_Artikel */
   public void removeNaroceni_Artikel(Naroceni_Artikel oldNaroceni_Artikel) {
      if (oldNaroceni_Artikel == null)
         return;
      if (this.naroceni_Artikel != null)
         if (this.naroceni_Artikel.contains(oldNaroceni_Artikel))
         {
            this.naroceni_Artikel.remove(oldNaroceni_Artikel);
            oldNaroceni_Artikel.setNarocilo((Narocilo)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllNaroceni_Artikel() {
      if (naroceni_Artikel != null)
      {
         Naroceni_Artikel oldNaroceni_Artikel;
         for (java.util.Iterator iter = getIteratorNaroceni_Artikel(); iter.hasNext();)
         {
            oldNaroceni_Artikel = (Naroceni_Artikel)iter.next();
            iter.remove();
            oldNaroceni_Artikel.setNarocilo((Narocilo)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Uporabnik getUporabnik() {
      return uporabnik;
   }
   
   /** @pdGenerated default parent setter
     * @param newUporabnik */
   public void setUporabnik(Uporabnik newUporabnik) {
      if (this.uporabnik == null || !this.uporabnik.equals(newUporabnik))
      {
         if (this.uporabnik != null)
         {
            Uporabnik oldUporabnik = this.uporabnik;
            this.uporabnik = null;
            oldUporabnik.removeNarocilo(this);
         }
         if (newUporabnik != null)
         {
            this.uporabnik = newUporabnik;
            this.uporabnik.addNarocilo(this);
         }
      }
   }

}