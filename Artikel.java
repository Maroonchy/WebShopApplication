/***********************************************************************
 * Module:  Artikel.java
 * Author:  avgustin
 * Purpose: Defines the Class Artikel
 ***********************************************************************/

import java.util.*;

/** @pdOid e10a7afe-ff8c-466d-8d8e-f8a9bd942469 */
public class Artikel {
   /** @pdOid fcb38645-a78d-4c12-907e-96e3795c2259 */
   private String ime;
   /** @pdOid 77e99fdd-8c95-4581-a03e-39e2f6d0a8d0 */
   private String kategorija;
   /** @pdOid cc97ad5c-a7a4-43e1-a640-c54830592ce6 */
   private String opis;
   /** @pdOid 074c771c-a315-43ab-82e3-3f85ee3ecae0 */
   private float cena;
   /** @pdOid 315810c8-ab20-4c56-a4a8-5558a78954ae */
   private float popust;
   /** @pdOid e48f5c60-72a0-4b32-848c-c9a27a8e4904 */
   private int na_zalogi;

   Artikel(String ime, String kategorija, String opis, float cena, float popust, int na_zalogi){
      this.ime = ime;
      this.kategorija = kategorija;
      this.opis = opis;
      this.cena = cena;
      this.popust = popust;
      this.na_zalogi = na_zalogi;
   }

    public String getIme() {
        return ime;
    }

    public String getKategorija() {
        return kategorija;
    }

    public String getOpis() {
        return opis;
    }

    public float getCena() {
        return cena;
    }

    public float getPopust() {
        return popust;
    }

    public int getNa_zalogi() {
        return na_zalogi;
    }

   /** @pdRoleInfo migr=no name=Naroceni_Artikel assc=NaroceniArtikelArtikel coll=java.util.List impl=java.util.ArrayList mult=0..* side=A */
   public java.util.List<Naroceni_Artikel> naroceni_Artikel;
   
   
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
         newNaroceni_Artikel.setArtikel(this);      
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
            oldNaroceni_Artikel.setArtikel((Artikel)null);
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
            oldNaroceni_Artikel.setArtikel((Artikel)null);
         }
      }
   }

}