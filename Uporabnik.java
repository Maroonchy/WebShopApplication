
import java.util.ArrayList;

/***********************************************************************
 * Module:  Uporabnik.java
 * Author:  avgustin
 * Purpose: Defines the Class Uporabnik
 ***********************************************************************/

/** @pdOid 9d81b253-3b76-4f70-9965-ff81cec4d1a9 */
public class Uporabnik {
   /** @pdOid 3f0496aa-a0db-45d6-9afc-cbdd64ee4b79 */
   private String ime;
   /** @pdOid 9f398ca5-2f61-4634-8d0b-cba6980ee1b7 */
   private String priimek;
   /** @pdOid 021685c4-dd53-4831-93b5-fcb5ba23d418 */
   private String naslov;
   /** @pdOid 38fc2045-8530-41af-ba94-f8595b074dcb */
   private String telefon;
   /** @pdOid 346623f5-0e48-4c6a-b7c2-26d3b815281f */
   private String email;
   
   /** @pdRoleInfo migr=no name=Narocilo assc=UporabnikNarocilo coll=java.util.List impl=java.util.ArrayList mult=0..* */
   public ArrayList<Narocilo> narocila;
   /** @pdRoleInfo migr=no name=K_Prekinitev_Narocila assc=Association_7 coll=java.util.Collection impl=java.util.HashSet mult=0..1 side=A */
   public K_Prekinitev_Narocila k_Prekinitev_Narocila;

   public Uporabnik(String ime, String priimek, String naslov, String telefon, String email) {
      this.ime = ime;
      this.priimek = priimek;
      this.naslov = naslov;
      this.telefon = telefon;
      this.email = email;
   }

   /** @pdOid dbafdcba-f774-43fc-9f3d-0239e8d774bb */
   public Narocilo[] VrniNarocila() {
      Narocilo[] tmp = (Narocilo[]) narocila.toArray(new Narocilo[narocila.size()]);
      if(tmp.length == 0)
         k_Prekinitev_Narocila.niNarocil();
      return tmp;
   }
   
   
   /** @pdGenerated default getter */
   public java.util.List<Narocilo> getNarocila() {
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
   public void setNarocila(java.util.List<Narocilo> newNarocilo) {
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
      {
         this.narocila.add(newNarocilo);
         newNarocilo.setUporabnik(this);      
      }
   }
   
   /** @pdGenerated default remove
     * @param oldNarocilo */
   public void removeNarocilo(Narocilo oldNarocilo) {
      if (oldNarocilo == null)
         return;
      if (this.narocila != null)
         if (this.narocila.contains(oldNarocilo))
         {
            this.narocila.remove(oldNarocilo);
            oldNarocilo.setUporabnik((Uporabnik)null);
         }
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllNarocilo() {
      if (narocila != null)
      {
         Narocilo oldNarocilo;
         for (java.util.Iterator iter = getIteratorNarocilo(); iter.hasNext();)
         {
            oldNarocilo = (Narocilo)iter.next();
            iter.remove();
            oldNarocilo.setUporabnik((Uporabnik)null);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public K_Prekinitev_Narocila getK_Prekinitev_Narocila() {
      return k_Prekinitev_Narocila;
   }
   
   /** @pdGenerated default parent setter
     * @param newK_Prekinitev_Narocila */
   public void setK_Prekinitev_Narocila(K_Prekinitev_Narocila newK_Prekinitev_Narocila) {
      if (this.k_Prekinitev_Narocila == null || !this.k_Prekinitev_Narocila.equals(newK_Prekinitev_Narocila))
      {
         if (this.k_Prekinitev_Narocila != null)
            this.k_Prekinitev_Narocila.setUporabnik(null);
         this.k_Prekinitev_Narocila = newK_Prekinitev_Narocila;
         if (this.k_Prekinitev_Narocila != null)
            this.k_Prekinitev_Narocila.setUporabnik(this);
      }
   }

}