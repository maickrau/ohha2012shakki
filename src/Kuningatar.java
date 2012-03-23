public class Kuningatar extends SuoraanMenevaNappula {
    public Kuningatar(boolean puoli)
    {
        super(puoli);
    }
    public Kuningatar(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        tyhjaaMahdollisetSiirrot();
        for (int dx = -1; dx <= 1; dx++)
        {
            for (int dy = -1; dy <= 1; dy++)
            {
                if (dx*dx+dy*dy != 0)
                {
                    super.katsoSuunta(lauta, dx, dy);
                }
            }
        }
        return mahdollisetSiirrot;
    }
}
