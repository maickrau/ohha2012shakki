import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    PeliLauta lauta;
    JButton[][] ruudut;
    /**
     * ruudun nykyinen listener. kun ruutuun laitetaan uusi listener, vanha täytyy poistaa
     */
    ActionListener[][] nykyListener;
    /**
     * nappuloiden kuvat, [0] on valkoinen, [1] musta
     */
    ImageIcon[] kuningasKuva;
    ImageIcon[] kuningatarKuva;
    ImageIcon[] lahettiKuva;
    ImageIcon[] hevonenKuva;
    ImageIcon[] torniKuva;
    ImageIcon[] sotilasKuva;
    /**
     * Pelaa yhden vuoron
     * @param vanhaX  nappula joka siirtyy
     * @param vanhaY
     * @param uusX    kohde jonne nappula siirtyy
     * @param uusY 
     */
    public void siirryTahan(int vanhaX, int vanhaY, int uusX, int uusY)
    {
        lauta.tallennaLauta("temp.txt");
        lauta.pelaaVuoro(vanhaX, vanhaY, uusX, uusY);
        naytaTavanLauta();
        if (lauta.kuningasUhattu(!lauta.kummanVuoro))
        {
            viesti("Ei voi siirtää siihen, kuningas kuolee!");
            lauta.lataaLauta("temp.txt");
            naytaTavanLauta();
        }
        else
        {
            if (lauta.onkoMatti())
            {
                viesti("Matti!");
                peliLoppui();
            }
            else if (lauta.kuningasUhattu(lauta.kummanVuoro))
            {
                viesti("Shakki.");
            }
        }
    }
    public void peliLoppui()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                ruudut[y][x].setEnabled(false);
            }
        }
    }
    /**
     * antaa viestin uudessa ikkunassa
     * @param viesti 
     */
    public void viesti(String viesti)
    {
        JFrame viestiFrame = new JFrame();
        viestiFrame.setLayout(new BorderLayout());
        JLabel viestiTeksti = new JLabel();
        viestiFrame.setSize(400, 100);
        viestiTeksti.setText(viesti);
        viestiFrame.add(viestiTeksti, BorderLayout.CENTER);
        viestiFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        viestiFrame.setVisible(true);
    }
    public void lataaKuvat()
    {
        kuningasKuva = new ImageIcon[2];
        kuningatarKuva = new ImageIcon[2];
        lahettiKuva = new ImageIcon[2];
        hevonenKuva = new ImageIcon[2];
        torniKuva = new ImageIcon[2];
        sotilasKuva = new ImageIcon[2];
        kuningasKuva[0] = new ImageIcon("img/ValkoinenKuningas.png");
        kuningasKuva[1] = new ImageIcon("img/MustaKuningas.png");
        kuningatarKuva[0] = new ImageIcon("img/ValkoinenKuningatar.png");
        kuningatarKuva[1] = new ImageIcon("img/MustaKuningatar.png");
        lahettiKuva[0] = new ImageIcon("img/ValkoinenLahetti.png");
        lahettiKuva[1] = new ImageIcon("img/MustaLahetti.png");
        hevonenKuva[0] = new ImageIcon("img/ValkoinenHevonen.png");
        hevonenKuva[1] = new ImageIcon("img/MustaHevonen.png");
        torniKuva[0] = new ImageIcon("img/ValkoinenTorni.png");
        torniKuva[1] = new ImageIcon("img/MustaTorni.png");
        sotilasKuva[0] = new ImageIcon("img/ValkoinenSotilas.png");
        sotilasKuva[1] = new ImageIcon("img/MustaSotilas.png");
    }
    /**
     * vaihtaa moodiin jossa pelaaja valitsee nappulan jota siirretään
     */
    public void naytaTavanLauta()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if (lauta.nappulaTassa[y][x] != null && lauta.nappulaTassa[y][x].puoli == lauta.kummanVuoro)
                {
                    ruudut[y][x].setEnabled(true);
                    asetaRuudunListener(x, y, new NaytaSiirrotListener(this, x, y));
                }
                else
                {
                    ruudut[y][x].setEnabled(false);
                }
            }
        }
        paivitaKuvat();
    }
    public void paivitaKuvat()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if (lauta.nappulaTassa[y][x] == null)
                {
                    ruudut[y][x].setIcon(null);
                    ruudut[y][x].setText(""+(char)('A'+x)+(char)('1'+y));
                }
                else
                {
                    int puoli = 0;
                    if (!lauta.nappulaTassa[y][x].puoli)
                    {
                        puoli = 1;
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Kuningas)
                    {
                        ruudut[y][x].setIcon(kuningasKuva[puoli]);
                        ruudut[y][x].setText("");
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Kuningatar)
                    {
                        ruudut[y][x].setIcon(kuningatarKuva[puoli]);
                        ruudut[y][x].setText("");
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Lahetti)
                    {
                        ruudut[y][x].setIcon(lahettiKuva[puoli]);
                        ruudut[y][x].setText("");
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Hevonen)
                    {
                        ruudut[y][x].setIcon(hevonenKuva[puoli]);
                        ruudut[y][x].setText("");
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Torni)
                    {
                        ruudut[y][x].setIcon(torniKuva[puoli]);
                        ruudut[y][x].setText("");
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Sotilas)
                    {
                        ruudut[y][x].setIcon(sotilasKuva[puoli]);
                        ruudut[y][x].setText("");
                    }
                }
            }
        }
    }
    /**
     * nayttaa nappulan mahdolliset siirrot
     * @param kohdeX  nappula jonka siirtoja kysytään
     * @param kohdeY 
     */
    public void naytaSiirrot(int kohdeX, int kohdeY)
    {
        if (lauta.nappulaTassa[kohdeY][kohdeX] == null)
        {
            return;
        }
        boolean[][] voiSiirtya = lauta.nappulaTassa[kohdeY][kohdeX].mahdollisetSiirrot(lauta);
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if (!voiSiirtya[y][x])
                {
                    ruudut[y][x].setEnabled(false);
                }
                else
                {
                    ruudut[y][x].setEnabled(true);
                    asetaRuudunListener(x, y, new SiirryTahanListener(this, kohdeX, kohdeY, x, y));
                }
            }
        }
        ruudut[kohdeY][kohdeX].setEnabled(true);
        asetaRuudunListener(kohdeX, kohdeY, new NaytaTavanLautaListener(this));
    }
    public GUI()
    {
        lataaKuvat();
        lauta = new PeliLauta();
        lauta.teeAlkuLauta();
        Container lautaContaineri = teeLautaContainer();
        Container napitContaineri = teeNapitContainer();
        JFrame kokoFrame = new JFrame();
        Container kokoContaineri = kokoFrame.getContentPane();
        kokoContaineri.add(lautaContaineri, BorderLayout.WEST);
        kokoContaineri.add(napitContaineri, BorderLayout.EAST);
        kokoFrame.setSize(800, 500);
        kokoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        kokoFrame.setVisible(true);
    }
    /**
     * oikeassa reunassa olevat lata, tallenna, lopeta
     * @return 
     */
    private Container teeNapitContainer()
    {
        JFrame napitFrame = new JFrame();
        Container napitContaineri = napitFrame.getContentPane();
        napitContaineri.setLayout(new GridLayout(3, 1));
        JButton lataaLauta = new JButton("Lataa");
        JButton tallennaLauta = new JButton("Tallenna");
        JButton lopeta = new JButton("Lopeta");
        lataaLauta.addActionListener(new LataaPeliListener(this));
        tallennaLauta.addActionListener(new TallennaPeliListener(this));
        lopeta.addActionListener(new SulkijaListener());
        napitContaineri.add(lataaLauta);
        napitContaineri.add(tallennaLauta);
        napitContaineri.add(lopeta);
        napitContaineri.setSize(200, 500);
        return napitContaineri;
    }
    /**
     * lauta joka näkyy
     * @return 
     */
    private Container teeLautaContainer()
    {
        JFrame lautaFrame = new JFrame();
        nykyListener = new ActionListener[8][8];
        ruudut = new JButton[8][8];
        Container lautaContaineri = lautaFrame.getContentPane();
        lautaContaineri.setLayout(new GridLayout(8, 8));
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                ruudut[y][x] = new JButton(""+(char)('A'+x)+(char)('1'+y));
            }
        }
        naytaTavanLauta();
        for (int y = 7; y >= 0; y--)
        {
            for (int x = 0; x < 8; x++)
            {
                lautaContaineri.add(ruudut[y][x]);
            }
        }
        lautaFrame.setSize(600, 500);
        return lautaContaineri;
    }
    /**
     * asettaa ruudulle uuden listenerin
     * @param x    ruutu
     * @param y
     * @param uusi uusi listener
     */
    private void asetaRuudunListener(int x, int y, ActionListener uusi)
    {
        if (ruudut[y][x].getActionListeners() != null)
        {
            ruudut[y][x].removeActionListener(nykyListener[y][x]);
        }
        ruudut[y][x].addActionListener(uusi);
        nykyListener[y][x] = uusi;
    }
    public static void main(String[] args)
    {
        GUI gui = new GUI();
    }
}
