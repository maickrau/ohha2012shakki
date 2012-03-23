
public class Lahetti extends SuoraanMenevaNappula {
    public Lahetti(boolean puoli)
    {
        super(puoli);
    }
    public Lahetti(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        tyhjaaMahdollisetSiirrot();
        super.katsoSuunta(lauta, -1, -1);
        super.katsoSuunta(lauta, +1, -1);
        super.katsoSuunta(lauta, +1, +1);
        super.katsoSuunta(lauta, -1, +1);
        return mahdollisetSiirrot;
    }
}
