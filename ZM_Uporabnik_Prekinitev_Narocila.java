/***********************************************************************
 * Module:  ZM_Uporabnik_Prekinitev_Narocila.java
 * Author:  avgustin
 * Purpose: Defines the Class ZM_Uporabnik_Prekinitev_Narocila
 ***********************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/** @pdOid 42b20a86-807a-4f7b-bf24-8c79ab966ef3 */
public class ZM_Uporabnik_Prekinitev_Narocila {
    static JFrame w;
    static GridBagLayout gbl;
    static GridBagConstraints gbc; 
    static JPanel toolbar;
    static ZM_Uporabnik_Prekinitev_Narocila zm = new ZM_Uporabnik_Prekinitev_Narocila();
    
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

      K_Posodobitev_Zaloge_SIM k_posodobitev_zaloge = new K_Posodobitev_Zaloge_SIM();

      SV_Prekinitev_Narocila_Bancni_Sistem_SIM sv_prekinitev_narocila_bancni_sistem = new SV_Prekinitev_Narocila_Bancni_Sistem_SIM();

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

    public static void main(String[] args) {
        initFrame();
        initToolbar();
        initBody();
        w.pack();
        w.setSize(1000, 500);
    }
    
    static private void initFrame()
    {
        zm.generirajPodatke();
        w = new JFrame("WebShop Trgovina");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(1000, 500);
        w.setVisible(true);
    }
    
    static private void initToolbar()
    {
        toolbar = new JPanel();
        toolbar.setSize(200, 100);
        toolbar.setPreferredSize(new Dimension(200, 70));
        toolbar.setBackground(Color.black);;
        toolbar.setLayout(new BorderLayout());
        
        BufferedImage icon = null;
        try
        {
            icon = ImageIO.read(new File("logo.png"));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
        JLabel iCIcon = new JLabel(new ImageIcon(icon));
        iCIcon.setPreferredSize(new Dimension(220, 50));
        toolbar.add(iCIcon, BorderLayout.WEST);
        
        Image user = null;
        try
        {
            user = ImageIO.read(new File("user.png"));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        user = user.getScaledInstance(25, 25, 0);
        
        JLabel iCUser = new JLabel(new ImageIcon(user));
        iCUser.setPreferredSize(new Dimension(50, 50));
        JPanel userInfo = new JPanel();
        userInfo.setOpaque(false);
        JLabel username = new JLabel(zm.k_Prekinitev_Narocila.uporabnik.getIme() + " " + zm.k_Prekinitev_Narocila.uporabnik.getPriimek());
        username.setFont(new Font("Berlin Sans", Font.BOLD, 20));
        username.setForeground(Color.white);
        userInfo.add(username, BorderLayout.WEST);
        userInfo.add(iCUser, BorderLayout.EAST);
        toolbar.add(userInfo, BorderLayout.EAST);
        
        w.add(toolbar, BorderLayout.NORTH);
    }
    
    private static void initBody()
    {
        JPanel body = new JPanel();
        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        body.setLayout(gbl);
        
        add(addEmpty(10, 20), body, 0, 0);
        
        JLabel oLabel = new JLabel("NAROCILA");
        oLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 35));
        oLabel.setPreferredSize(new Dimension(175, 50));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        add(oLabel, body, 0, 1);
        
        gbc.gridwidth = 1;
        add(addEmpty(100, 10), body, 0, 2);
        
        JPanel orders = new JPanel();
        orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
        
        JScrollPane scrollbar = new JScrollPane(orders);
        scrollbar.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
        scrollbar.setPreferredSize(new Dimension(100, 50));
        scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   
        scrollbar.getVerticalScrollBar().setUnitIncrement(12);
        
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollbar, body, 1, 2);
        
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(addEmpty(100, 10), body, 2, 2);
        
        w.add(body);
        w.add(addEmpty(10, 100), BorderLayout.SOUTH);
        
        fillOrders(orders);
    }
    
    static void fillOrders(JComponent container)
    {
        String[][] narocila = new String[0][];
        try
        {
            narocila = zm.PrikaziNarocila();
        }
        catch(Exception ex)
        {
            JLabel warn = new JLabel(zm.niNarocil());
            warn.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
            container.add(warn);
        }
        //String[][] narocila = {{"#1", "20.1.2019", "Neobdelano", "Posta", "Kartica", "345€"}, {"#2", "15.1.2019", "Dostavljeno", "Osebni prevzem", "Gotovina", "212€"}};
        //String[][] narocilaPodrobnostii = {  {"Grafična Kartica GTX 2080", "1", "1000€", "10%", "900€"},
                                            //{"Intel i9 9900k", "1", "560€", "0%", "560€"},
                                            //{"ASUS Prime A1", "1", "200€", "15%", "170€"}};
        ArrayList<String[][]> narocilaPodrobnosti = new ArrayList<>();
        //narocilaPodrobnosti.add(narocilaPodrobnostii);
        //narocilaPodrobnosti.add(narocilaPodrobnostii);

        int i = 0;
        
        for(String[] narocilo : narocila)
        {
            Color color;
            
            if(i % 2 == 0)
            {
                color = new Color(190, 190, 190);
            }
            else
            {
                color = null;
            }
            i++;
            
            JComponent empty = addEmpty(2, 10);
            empty.setBackground(color);
            
            JPanel order = new JPanel();
            order.setBackground(color);
            order.setLayout(gbl);
            JLabel id = new JLabel(narocilo[0]);
            id.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
            JLabel date = new JLabel(narocilo[1]);
            date.setFont(new Font("Berlin Sans FB", Font.PLAIN, 22));
            JLabel status = new JLabel("Status: " + narocilo[2]);
            status.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
            JLabel delivery = new JLabel("Dostava: " + narocilo[3]);
            delivery.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
            JLabel payment = new JLabel("Nacin placila: " + narocilo[4]);
            payment.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
            JLabel price = new JLabel(narocilo[5] + "€");
            price.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
            JLabel cursedi = new JLabel(Integer.toString(i-1));
            
            JPanel first = new JPanel();
            first.setBackground(color);
            first.setLayout(new BorderLayout());
            first.add(id, BorderLayout.WEST);
            first.add(date, BorderLayout.EAST);
            
            JPanel last = new JPanel();
            last.setBackground(color);
            last.setLayout(new BorderLayout());
            last.add(payment, BorderLayout.WEST);
            last.add(price, BorderLayout.EAST);
            
            JButton details = new JButton("Podrobnosti");
            details.setBackground(Color.white);
            JButton cancel = new JButton("Preklic narocila");
            cancel.setBackground(Color.white);
            JPanel controls = new JPanel();
            controls.setLayout(new BoxLayout(controls, BoxLayout.X_AXIS));
            controls.setPreferredSize(new Dimension(100, 50));
            controls.setBackground(color);
            controls.add(details);
            controls.add(empty);
            if(narocilo[2].equals("V obdelavi"))
            {
                controls.add(cancel);
                
                cancel.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        cancelOrder(status, cancel, cursedi.getText());
                    }
                });
            }
            
            JLabel orderInfo = new JLabel();
            orderInfo.setVisible(false);
            
            /*String temp = "<html><style>td, th {border: 1px solid #dddddd; text-align: left; padding: 8px;}</style><table><tr><th><b>Artikel</b></th><th><b>Količina</b></th><th><b>Cena/Kos</b></th><th><b>Popust</b></th><th><b>Cena</b></th></tr>";
            for(String[] narociloInfo : narocilaPodrobnosti.get(i - 1))
            {
                temp += "<tr><td>" + narociloInfo[0] + "</td><td>" + narociloInfo[1] + "</td><td>" + narociloInfo[2] + "</td><td>" + narociloInfo[3] + "</td><td>" + narociloInfo[4] + "</td></tr>";
            }
            temp += "</table>";
            orderInfo.setText(temp);*/           
            
            details.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if(orderInfo.isVisible())
                    {
                        orderInfo.setVisible(false);
                        details.setText("Podrobnosti");
                    }
                    else
                    {
                        String temp = "<html><style>td, th {border: 1px solid #dddddd; text-align: left; padding: 8px;}</style><table><tr><th><b>Artikel</b></th><th><b>Količina</b></th><th><b>Cena/Kos</b></th><th><b>Popust</b></th><th><b>Cena</b></th></tr>";
                        String[][] narociloInfo = zm.PrikaziIzbranoNarocilo(Integer.parseInt(cursedi.getText()));
                        for(String[] artikelInfo : narociloInfo)
                        {
                            temp += "<tr><td>" + artikelInfo[0] + "</td><td>" + artikelInfo[1] + "</td><td>" + artikelInfo[2] + "€" + "</td><td>" + artikelInfo[3] + "%" + "</td><td>" + artikelInfo[4] + "€" + "</td></tr>";
                        }
                        temp += "</table>";
                        orderInfo.setText(temp);
                        orderInfo.setVisible(true);
                        details.setText("Skrij podrobnosti");
                    }
                }
            });
            
            gbc.weightx = 1;
            gbc.weighty = 0;
            
            add(first, order, 1, 0);
            add(status, order, 1, 1);
            add(delivery, order, 1, 2);
            add(last, order, 1, 3);
            add(empty, order, 1, 4);
            add(controls, order, 1, 5);
            add(empty, order, 1, 6);
            add(orderInfo, order, 1, 7);
            
            container.add(order);
        }
    }
    
    private static void cancelOrder(JLabel label, JButton cancel, String cursedi)
    {
        UIManager.put("OptionPane.okButtonText", "Preklici");
        UIManager.put("OptionPane.cancelButtonText", "Nazaj");
        
        JButton confirm = new JButton("Preklici");
        JButton back = new JButton("Nazaj");
        int response = JOptionPane.showConfirmDialog(w, "Ali ste prepricani da zelite preklicati narocilo?", "Preklic Narocila", JOptionPane.OK_CANCEL_OPTION);
        
        if(response == 0)
        {
            zm.PrekiniNarocilo(Integer.parseInt(cursedi));
            label.setText("Status: Preklicano");
            cancel.setVisible(false);
        }
    }
    
    private static void add(JComponent c, JComponent p, int x, int y)
    {
        gbc.gridx = x;
        gbc.gridy = y;
        
        gbl.setConstraints(c, gbc);
        p.add(c);
    }
    
    private static JComponent addEmpty(int x, int y)
    {
        JPanel empty = new JPanel();
        empty.setSize(x, y);
        empty.setPreferredSize(new Dimension(x, y));
        return empty;
    }
}
