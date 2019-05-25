import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * @author JZibert
 */
public class WebShopApplication {
    static JFrame w;
    static GridBagLayout gbl;
    static GridBagConstraints gbc; 
    static JPanel toolbar;
    static ZM_Uporabnik_Prekinitev_Narocila zm = new ZM_Uporabnik_Prekinitev_Narocila();
    
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
            label.setText("Status: Preklican");
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
