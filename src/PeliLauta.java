public class PeliLauta 
{
    /**
     * onko ruudulla nappula, null on tyhjä ruutu
     * valkoinen aloittaa alariviltä (kuningas on nappula[0][4]
     * musta yläriviltä (kuningas on nappula[7][4]
     */
    Nappula[][] nappulaTassa;
    Nappula valkoisenKuningas;
    Nappula mustanKuningas;
    /**
     * true on valkoisen vuoro, false mustan
     */
    boolean kummanVuoro;
    public PeliLauta()
    {
        nappulaTassa = new Nappula[8][8];
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
        nappulaTassa[loppuY][loppuX].nappulaSiirtyi(this, loppuX, loppuY);
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
            return true;
        }
        return false;
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
        nappulaTassa[sijaintiX][sijaintiY] = tama;
        tama.sijaintiX = sijaintiX;
        tama.sijaintiY = sijaintiY;
        return tama;
    }
    private void nappulaSoiNappulan(Nappula syoja, Nappula syoty)
    {
        syoty.nappulaKuoli();
        //TODO?
    }
    public boolean onkoUhattu(boolean puoli, int sijaintiX, int sijaintiY)
    {
        //TODO
        return false;
    }
}
