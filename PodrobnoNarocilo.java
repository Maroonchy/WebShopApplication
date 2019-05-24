
import java.util.Date;

public class PodrobnoNarocilo {
    Narocilo narocilo;
    Naroceni_Artikel naroceniArtikli[];
    Artikel trenutniArtikli[];
    Date datumDostave;

    PodrobnoNarocilo(Narocilo narocilo, Naroceni_Artikel naroceniArtikli[], Artikel trenutniArtikli[], Date datumDostave){
        this.narocilo = narocilo;
        this.naroceniArtikli = naroceniArtikli;
        this.trenutniArtikli = trenutniArtikli;
        this.datumDostave = datumDostave;
		
		//cena, popust in količina iz seznama naročenih iz tenutnih pa ime in opis
    }
}
