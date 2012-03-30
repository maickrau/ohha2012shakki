public class PeliLauta 
{
    /**
     * onko ruudulla nappula, null on tyhjä ruutu
     * valkoinen aloittaa alariviltä (kuningas on nappula[0][4]
     * musta yläriviltä (kuningas on nappula[7][4]
     */
    public Nappula[][] nappulaTassa;
    private Nappula valkoisenKuningas;
    private Nappula mustanKuningas;
    private boolean[][] mustaUhkaa;
    private boolean[][] valkoinenUhkaa;
    private boolean uhkauksetLaskettavaUudestaan;
    /**
     * true on valkoisen vuoro, false mustan
     */
    public boolean kummanVuoro;
    /**
     * sotilas aloitti tuplasiirrolla, en passant mahdollista sarakkeella n
     */
    public int viimeSiirtoOliSotilaanTupla;
    public PeliLauta()
    {
        nappulaTassa = new Nappula[8][8];
        mustaUhkaa = new boolean[8][8];
        valkoinenUhkaa = new boolean[8][8];
        uhkauksetLaskettavaUudestaan = true;
    }
    /**
     * Pakottaa nappulaa siirtymään riippumatta voisiko se normaalisti siirtyä
     * @param alkuX   alkusijainti
     * @param alkuY
     * @param loppuX  loppusijainti
     * @param loppuY 
     */
    public void pakotaNappulaSiirtymaan(int alkuX, int alkuY, int loppuX, int loppuY)
    {
        nappulaTassa[alkuY][alkuX].nappulaSiirtyi(this, loppuX, loppuY);
        uhkauksetLaskettavaUudestaan = true;
    }
    /**
     * Tarkistaa voiko nappula siirtyä, jos voi niin siirtää sen
     * @param alkuX  alkusijainti
     * @param alkuY
     * @param loppuX loppusijainti
     * @param loppuY
     * @return       true jos siirtyi, false jos ei
     */
    public boolean siirraNappulaa(int alkuX, int alkuY, int loppuX, int loppuY)
    {
        if (nappulaTassa[alkuY][alkuX] == null)
        {
            return false;
        }
        if (nappulaTassa[alkuY][alkuX].mahdollisetSiirrot(this)[loppuY][loppuX])
        {
            if (nappulaTassa[loppuY][loppuX] != null)
            {
                nappulaSoiNappulan(nappulaTassa[alkuY][alkuX], nappulaTassa[loppuY][loppuX]);
            }
            nappulaTassa[alkuY][alkuX].nappulaSiirtyi(this, loppuX, loppuY);
            uhkauksetLaskettavaUudestaan = true;
            return true;
        }
        return false;
    }
    /**
     * Pelaaja siirtää nappulaa, voi siirtää vain omalla vuorollaan
     * @param alkuX   alkusijainti
     * @param alkuY
     * @param loppuX  loppusijainti
     * @param loppuY
     * @return        true jos siirto onnistui, false jos ei
     */
    public boolean pelaaVuoro(int alkuX, int alkuY, int loppuX, int loppuY)
    {
        if (nappulaTassa[alkuY][alkuX] == null)
        {
            return false;
        }
        if (nappulaTassa[alkuY][alkuX].puoli != kummanVuoro)
        {
            return false;
        }
        if (!siirraNappulaa(alkuX, alkuY, loppuX, loppuY))
        {
            return false;
        }
        viimeSiirtoOliSotilaanTupla = -1;
        if (nappulaTassa[loppuX][loppuY] instanceof Sotilas)
        {
            if (alkuY-loppuY == -2 || alkuY-loppuY == 2)
            {
                viimeSiirtoOliSotilaanTupla = alkuX;
            }
        }
        kummanVuoro = !kummanVuoro;
        return true;
    }
    /**
     * asettaa nappulat aloituspaikoilleen ja antaa vuoron valkoiselle
     */
    public void teeAlkuLauta()
    {
        valkoisenKuningas = (Kuningas)asetaNappula(new Kuningas(true), 4, 0);
        mustanKuningas = (Kuningas)asetaNappula(new Kuningas(false), 4, 7);
        asetaNappula(new Torni(true), 0, 0);
        asetaNappula(new Hevonen(true), 1, 0);
        asetaNappula(new Lahetti(true), 2, 0);
        asetaNappula(new Kuningatar(true), 3, 0);
        asetaNappula(new Lahetti(true), 5, 0);
        asetaNappula(new Hevonen(true), 6, 0);
        asetaNappula(new Torni(true), 7, 0);
        asetaNappula(new Torni(false), 0, 7);
        asetaNappula(new Hevonen(false), 1, 7);
        asetaNappula(new Lahetti(false), 2, 7);
        asetaNappula(new Kuningatar(false), 3, 7);
        asetaNappula(new Lahetti(false), 5, 7);
        asetaNappula(new Hevonen(false), 6, 7);
        asetaNappula(new Torni(false), 7, 7);
        for (int x = 0; x < 8; x++)
        {
            asetaNappula(new Sotilas(true), x, 1);
            asetaNappula(new Sotilas(false), x, 6);
        }
        kummanVuoro = true; //valkoisen vuoro
        laskeUhatutRuudut();
    }
    /**
     * asettaa nappulan laudalle ja palauttaa viitteen juuri asetetulle nappulalle
     * @param tama       nappula joka asetetaan
     * @param sijaintiX  kohderuutu
     * @param sijaintiY
     * @return           nappula joka asetettiin
     */
    public Nappula asetaNappula(Nappula tama, int sijaintiX, int sijaintiY)
    {
        nappulaTassa[sijaintiY][sijaintiX] = tama;
        tama.sijaintiX = sijaintiX;
        tama.sijaintiY = sijaintiY;
        return tama;
    }
    public void nappulaSoiNappulan(Nappula syoja, Nappula syoty)
    {
        syoty.nappulaKuoli();
        //TODO?
    }
    /**
     * onko ruutu uhattu
     * @param puoli      puoli jota uhataan, puoli == true(valkoinen) => palauttaa uhkaako musta ruutua
     * @param sijaintiX  ruudun sijainti
     * @param sijaintiY
     * @return 
     */
    public boolean onkoUhattu(boolean puoli, int sijaintiX, int sijaintiY)
    {
        if (uhkauksetLaskettavaUudestaan)
        {
            laskeUhatutRuudut();
        }
        if (puoli)
        {
            return mustaUhkaa[sijaintiY][sijaintiX];
        }
        else
        {
            return valkoinenUhkaa[sijaintiY][sijaintiX];
        }
    }
    /**
     * laskee mitä ruutuja musta ja valkoinen uhkaavat
     */
    private void laskeUhatutRuudut()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                mustaUhkaa[y][x] = false;
                valkoinenUhkaa[y][x] = false;
            }
        }
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if (nappulaTassa[y][x] != null)
                {
                    boolean[][] uhkaa = nappulaTassa[y][x].uhatutRuudut(this);
                    for (int yy = 0; yy < 8; yy++)
                    {
                        for (int xx = 0; xx < 8; xx++)
                        {
                            if (uhkaa[yy][xx])
                            {
                                if (nappulaTassa[y][x].puoli)
                                {
                                    valkoinenUhkaa[yy][xx] = true;
                                }
                                else
                                {
                                    mustaUhkaa[yy][xx] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        uhkauksetLaskettavaUudestaan = false;
    }
}
