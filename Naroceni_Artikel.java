/***********************************************************************
 * Module:  Naroceni_Artikel.java
 * Author:  avgustin
 * Purpose: Defines the Class Naroceni_Artikel
 ***********************************************************************/

import java.util.*;

/** @pdOid 475419a2-4ca4-469e-83d0-c2901979c146 */
public class Naroceni_Artikel {
   /** @pdOid 3d12fe1e-c04b-4a87-891d-a2f8ab93793d */
   private float cena;
   /** @pdOid bb408099-16cd-4158-96f2-a363b2f249ad */
   private float popust;
   /** @pdOid effc7a5c-710d-4c63-b2e1-25051692ef42 */
   private int kolicina;
   
   /** @pdRoleInfo migr=no name=Artikel assc=NaroceniArtikelArtikel coll=java.util.Collection mult=1..1 */
   public Artikel artikel;
   /** @pdRoleInfo migr=no name=Narocilo assc=NarociloNaroceniArtikel coll=java.util.Collection mult=1..1 side=A */
   public Narocilo narocilo;
   
   /** @pdOid 25715114-1025-434f-83c3-dad9e3734c5b */
   public Artikel vrniArtikel() {
      return artikel;
   }

   Naroceni_Artikel(float cena, float popust, int kolicina){
      this.cena = cena;
      this.popust = popust;
      this.kolicina = kolicina;
   }

    public float getCena() {
        return cena;
    }

    public float getPopust() {
        return popust;
    }

    public int getKolicina() {
        return kolicina;
    }
   
   /** @pdGenerated default parent getter */
   public Artikel getArtikel() {
      return artikel;
   }
   
   /** @pdGenerated default parent setter
     * @param newArtikel */
   public void setArtikel(Artikel newArtikel) {
      if (this.artikel == null || !this.artikel.equals(newArtikel))
      {
         if (this.artikel != null)
         {
            Artikel oldArtikel = this.artikel;
            this.artikel = null;
            oldArtikel.removeNaroceni_Artikel(this);
         }
         if (newArtikel != null)
         {
            this.artikel = newArtikel;
            this.artikel.addNaroceni_Artikel(this);
         }
      }
   }
   /** @pdGenerated default parent getter */
   public Narocilo getNarocilo() {
      return narocilo;
   }
   
   /** @pdGenerated default parent setter
     * @param newNarocilo */
   public void setNarocilo(Narocilo newNarocilo) {
      if (this.narocilo == null || !this.narocilo.equals(newNarocilo))
      {
         if (this.narocilo != null)
         {
            Narocilo oldNarocilo = this.narocilo;
            this.narocilo = null;
            oldNarocilo.removeNaroceni_Artikel(this);
         }
         if (newNarocilo != null)
         {
            this.narocilo = newNarocilo;
            this.narocilo.addNaroceni_Artikel(this);
         }
      }
   }

}