abstract public class Nappula 
{
    /**
     * nappulan mahdolliset siirrot
     */
    public boolean[][] mahdollisetSiirrot;
    /**
     * nappulan väri, true on valkoinen, false on musta
     */
    public boolean puoli;
    /**
     * nappulan sijainti laudalla
     */
    public int sijaintiX;
    public int sijaintiY;
    public Nappula(boolean puoli)
    {
        this(puoli, -1, -1);
    }
    public Nappula(boolean puoli, int sijaintiX, int sijaintiY)
    {
        this.puoli = puoli;
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        mahdollisetSiirrot = new boolean[8][8];
    }
    public void nappulaKuoli()
    {
        sijaintiX = -10;
        sijaintiY = -10;
    }
    /**
     * Laskee mahdollisetSiirrot[][] -taulukon ja palauttaa sen
     * @param lauta
     * @return      mahdollisetSiirrot[][] -taulukko
     */
    public abstract boolean[][] mahdollisetSiirrot(PeliLauta lauta);
    /**
     * laskee ruudut joita uhataan, sama kuin mahdollisetSiirrot paitsi kuninkaalla joka voi uhata
     * ruutuja ilman että voi siirtyä niihin (vihollinen uhkaa samoja ruutuja)
     * ja sotilas joka uhkaa vinoon
     * @param lauta
     * @return      uhatut ruudut
     */
    public boolean[][] uhatutRuudut(PeliLauta lauta)
    {
        return mahdollisetSiirrot(lauta);
    }
    /**
     * Siirtää nappulaa laudalla
     * @param lauta
     * @param sijaintiX
     * @param sijaintiY 
     */
    public void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = null;
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = this;
    }
    /**
     * Tyhjää mahdollisetSiirrot[][] -taulukon
     */
    void tyhjaaMahdollisetSiirrot()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                mahdollisetSiirrot[y][x] = false;
            }
        }
    }
}
