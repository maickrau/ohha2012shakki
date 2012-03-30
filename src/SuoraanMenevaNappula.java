/**
 * Nappula joka voi liikkua suorassa viivassa eli torni, lähetti, kuningatar
 */
abstract public class SuoraanMenevaNappula extends Nappula {
    public SuoraanMenevaNappula(boolean puoli)
    {
        super(puoli);
    }
    public SuoraanMenevaNappula(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
    }
    /**
     * tarkistaa miten pitkälle voi siirtyä suoraa viivaa pitkin, päivittää mahdollisetSiirrot[][] -taulukkoon
     * @param lauta pelilauta
     * @param dx    suunta
     * @param dy 
     */
    public void katsoSuunta(PeliLauta lauta, int dx, int dy)
    {
        int x = sijaintiX+dx;
        int y = sijaintiY+dy;
        while (x >= 0 && x < 8 && y >= 0 && y < 8)
        {
            mahdollisetSiirrot[y][x] = true;
            if (lauta.nappulaTassa[y][x] != null)
            {
                if (lauta.nappulaTassa[y][x].puoli == puoli)
                {
                    mahdollisetSiirrot[y][x] = false;
                }
                return;
            }
            x += dx;
            y += dy;
        }
    }
}
