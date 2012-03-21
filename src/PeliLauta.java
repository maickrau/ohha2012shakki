
public class PeliLauta 
{
    Nappula[][] nappulaTassa;
    Nappula valkoinenKuningas;
    Nappula mustaKuningas;
    boolean kummanVuoro;
    public PeliLauta()
    {
        nappulaTassa = new Nappula[8][8];
    }
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
            nappulaTassa[loppuY][loppuX] = nappulaTassa[alkuY][alkuX];
            nappulaTassa[loppuY][loppuX].nappulaSiirtyi(this, loppuX, loppuY);
            nappulaTassa[alkuY][alkuX] = null;
            return true;
        }
        return false;
    }
    public void teeAlkuLauta()
    {
        
    }
    public void nappulaSoiNappulan(Nappula syoja, Nappula syoty)
    {
        
    }
    public boolean onkoUhattu(boolean puoli, int sijaintiX, int sijaintiY)
    {
        return false;
    }
}
