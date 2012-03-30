import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    PeliLauta lauta;
    JButton[][] ruudut;
    ActionListener[][] nykyListener;
    ImageIcon kuningas;
    public void ruutuaKlikattiin(int x, int y)
    {
        ruudut[y][x].setText("!!");
    }
    public void siirryTahan(int vanhaX, int vanhaY, int uusX, int uusY)
    {
        lauta.pelaaVuoro(vanhaX, vanhaY, uusX, uusY);
        naytaTavanLauta();
    }
    public void lataaKuvat()
    {
        kuningas = new ImageIcon("kuningas.png");
    }
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
                if (lauta.nappulaTassa[y][x] instanceof Kuningas)
                {
                    ruudut[y][x].setIcon(kuningas);
                    ruudut[y][x].setText("");
                }
            }
        }
    }
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
        Container lautaContaineri = teeLauta();
        JFrame napitFrame = new JFrame();
        Container napitContaineri = napitFrame.getContentPane();
        napitContaineri.setLayout(new GridLayout(3, 1));
        JButton lataaLauta = new JButton("Lataa");
        JButton tallennaLauta = new JButton("Tallenna");
        JButton lopeta = new JButton("Lopeta");
        lopeta.addActionListener(new SulkijaListener());
        napitContaineri.add(lataaLauta);
        napitContaineri.add(tallennaLauta);
        napitContaineri.add(lopeta);
        napitContaineri.setSize(200, 500);
        JFrame kokoFrame = new JFrame();
        Container kokoContaineri = kokoFrame.getContentPane();
        kokoContaineri.add(lautaContaineri, BorderLayout.WEST);
        kokoContaineri.add(napitContaineri, BorderLayout.EAST);
        kokoFrame.setSize(800, 500);
        kokoFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        kokoFrame.setVisible(true);
    }
    private Container teeLauta()
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
