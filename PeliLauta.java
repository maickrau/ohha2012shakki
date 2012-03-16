
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
        if (nappulaTassa[alkuX][alkuY] == null)
        {
            return false;
        }
        if (nappulaTassa[alkuX][alkuY].mahdollisetSiirrot(this)[loppuX][loppuY])
        {
            if (nappulaTassa[loppuX][loppuY] != null)
            {
                nappulaSoiNappulan(nappulaTassa[alkuX][alkuY], nappulaTassa[loppuX][loppuY]);
            }
            nappulaTassa[loppuX][loppuY] = nappulaTassa[alkuX][alkuY];
            nappulaTassa[alkuX][alkuY] = null;
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
