/***********************************************************************
 * Module:  K_Posodobitev_Zaloge.java
 * Author:  avgustin
 * Purpose: Defines the Class K_Posodobitev_Zaloge
 ***********************************************************************/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/** @pdOid 4bce56ca-856f-4ee7-96c9-a5ace062c912 */
public class K_Posodobitev_Zaloge_SIM {
   /** @pdOid c4f3be1e-b00a-4af6-a59e-f8076bf7c60e */
   public boolean PristejZalogi(Naroceni_Artikel naroceniArtikli[]) {
      // tukaj bi se naroceni artikli pristeli nazaj v zalogo
      return true;
   }
   
   /** @pdOid 3f2a795c-6c6c-4fdc-be5d-df8fb55dae99 */
   public Date VrniDatumDostave(Artikel artikli[]){
      Date d1 = parseDate("2019-06-1");
      Date d2 = parseDate("2019-06-31");
      Date randomDate = new Date(ThreadLocalRandom.current().nextLong(d1.getTime(), d2.getTime()));
      return null;
   }

   public static Date parseDate(String date) {
      try {
         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
      } catch (ParseException e) {
         return null;
      }
   }

}