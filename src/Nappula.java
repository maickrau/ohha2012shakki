abstract public class Nappula 
{
    public boolean puoli;
    public int sijaintiX;
    public int sijaintiY;
    public Nappula(boolean puoli, int sijaintiX, int sijaintiY)
    {
        this.puoli = puoli;
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
    }
    abstract boolean[][] mahdollisetSiirrot(PeliLauta lauta);
    abstract void nappulaSiirtyi(PeliLauta lauta, int sijantiX, int sijaintiY);
}
