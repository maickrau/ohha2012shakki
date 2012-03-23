abstract public class Nappula 
{
    /**
     * nappulan mahdolliset siirrot
     */
    boolean[][] mahdollisetSiirrot;
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
    abstract boolean[][] mahdollisetSiirrot(PeliLauta lauta);
    /**
     * Siirtää nappulaa laudalla
     * @param lauta
     * @param sijaintiX
     * @param sijaintiY 
     */
    void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
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
