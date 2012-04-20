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
        if (lauta.kuningasUhattu(!lauta.kummanVuoro))
        {
            viesti("Ei voi siirtää siihen, kuningas kuolee!");
            lauta.lataaLauta("temp.txt");
        }
        if (!voikoYlentaa())
        {
            uusiVuoroAlkaa();
        }
    }
    /**
     * tarkistaa voiko sotilaita ylentää ja tekee ylennysdialogin jos voi
     * @return  true jos voi
     */
    private boolean voikoYlentaa()
    {
        for (int x = 0; x < 8; x++)
        {
            if (lauta.nappulaTassa[0][x] != null)
            {
                if (lauta.nappulaTassa[0][x] instanceof Sotilas)
                {
                    teeYlennysDialogi(x, 0, lauta.nappulaTassa[0][x].puoli);
                    return true;
                }
            }
            if (lauta.nappulaTassa[7][x] != null)
            {
                if (lauta.nappulaTassa[7][x] instanceof Sotilas)
                {
                    teeYlennysDialogi(x, 7, lauta.nappulaTassa[7][x].puoli);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * estää klikkaamasta nappuloita
     */
    private void pysaytaPeli()
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
     * tekee dialogin sotilaan ylennykselle
     * @param sijaintiX sotilaan sijainti
     * @param sijaintiY
     * @param puoli     sotilaan puoli
     */
    public void teeYlennysDialogi(int sijaintiX, int sijaintiY, boolean puoli)
    {
        int kuvanPuoli = 0;
        if (!puoli)
        {
            kuvanPuoli = 1;
        }
        JFrame ylennysFrame = new JFrame();
        ylennysFrame.setLayout(new BorderLayout());
        Container ylennysContainer = new Container();
        ylennysContainer.setLayout(new GridLayout(1, 4));
        ylennysContainer.setSize(400, 100);
        JLabel ylennysTeksti = new JLabel();
        JButton ylennaKuningattareksi = new JButton();
        ylennaKuningattareksi.setIcon(kuningatarKuva[kuvanPuoli]);
        ylennaKuningattareksi.setText("");
        ylennaKuningattareksi.addActionListener(new YlennysListener(this, sijaintiX, sijaintiY, 0, ylennysFrame));
        JButton ylennaLahetiksi = new JButton();
        ylennaLahetiksi.setIcon(lahettiKuva[kuvanPuoli]);
        ylennaLahetiksi.setText("");
        ylennaLahetiksi.addActionListener(new YlennysListener(this, sijaintiX, sijaintiY, 1, ylennysFrame));
        JButton ylennaHevoseksi = new JButton();
        ylennaHevoseksi.setIcon(hevonenKuva[kuvanPuoli]);
        ylennaHevoseksi.setText("");
        ylennaHevoseksi.addActionListener(new YlennysListener(this, sijaintiX, sijaintiY, 2, ylennysFrame));
        JButton ylennaTorniksi = new JButton();
        ylennaTorniksi.setIcon(torniKuva[kuvanPuoli]);
        ylennaTorniksi.setText("");
        ylennaTorniksi.addActionListener(new YlennysListener(this, sijaintiX, sijaintiY, 3, ylennysFrame));
        ylennysFrame.setSize(400, 150);
        ylennysTeksti.setText("Ylennä nappula");
        ylennysFrame.add(ylennysTeksti, BorderLayout.NORTH);
        ylennysContainer.add(ylennaKuningattareksi);
        ylennysContainer.add(ylennaLahetiksi);
        ylennysContainer.add(ylennaHevoseksi);
        ylennysContainer.add(ylennaTorniksi);
        ylennysFrame.add(ylennysContainer, BorderLayout.SOUTH);
        ylennysFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ylennysFrame.setVisible(true);
        ylennysFrame.addWindowListener(new YlennyksenSulkijanListener(this, sijaintiX, sijaintiY, puoli));
        paivitaKuvat();
        pysaytaPeli();
    }
    /**
     * antaa ennen siirtoa tarvittavat infot
     */
    private void uusiVuoroAlkaa()
    {
        lauta.laskeUhatutRuudut();
        naytaTavanLauta();
        if (lauta.onkoMatti())
        {
            viesti("Matti!");
            pysaytaPeli();
        }
        else if (lauta.kuningasUhattu(lauta.kummanVuoro))
        {
            viesti("Shakki.");
        }
    }
    /**
     * ylentää sotilaan
     * @param sijaintiX sotilaan sijainti
     * @param sijaintiY 
     * @param uusiNappula nappula johon ylennetään
     */
    public void ylennaNappula(int sijaintiX, int sijaintiY, int uusiNappula)
    {
        boolean puoli = lauta.nappulaTassa[sijaintiY][sijaintiX].puoli;
        lauta.nappulaTassa[sijaintiY][sijaintiX].nappulaKuoli(lauta);
        if (uusiNappula == 0)
        {
            lauta.asetaNappula(new Kuningatar(puoli), sijaintiX, sijaintiY);
        }
        else if (uusiNappula == 1)
        {
            lauta.asetaNappula(new Lahetti(puoli), sijaintiX, sijaintiY);
        }
        else if (uusiNappula == 2)
        {
            lauta.asetaNappula(new Hevonen(puoli), sijaintiX, sijaintiY);
        }
        else if (uusiNappula == 3)
        {
            lauta.asetaNappula(new Torni(puoli), sijaintiX, sijaintiY);
        }
        lauta.laskeUhatutRuudut();
        uusiVuoroAlkaa();
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
    private void lataaKuvat()
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
    private void paivitaKuvat()
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
     * oikeassa reunassa olevat lataa, tallenna, lopeta
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
     * vasemmalla oleva 8x8 ruudukko jossa nappulat näkyy
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
