
public class Hevonen extends Nappula {
    public Hevonen(boolean puoli)
    {
        super(puoli);
    }
    public Hevonen(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        tyhjaaMahdollisetSiirrot();
        voikoHypataTahan(lauta, sijaintiX-2, sijaintiY-1);
        voikoHypataTahan(lauta, sijaintiX-2, sijaintiY+1);
        voikoHypataTahan(lauta, sijaintiX-1, sijaintiY-2);
        voikoHypataTahan(lauta, sijaintiX-1, sijaintiY+2);
        voikoHypataTahan(lauta, sijaintiX+1, sijaintiY-2);
        voikoHypataTahan(lauta, sijaintiX+1, sijaintiY+2);
        voikoHypataTahan(lauta, sijaintiX+2, sijaintiY-1);
        voikoHypataTahan(lauta, sijaintiX+2, sijaintiY+1);
        return mahdollisetSiirrot;
    }
    /**
     * voiko hevonen hypätä kohderuutuun, päivittää tuloksen mahdollisetSiirrot[][] -taulukkoon
     * @param lauta     pelilauta
     * @param sijaintiX kohderuutu
     * @param sijaintiY 
     */
    private void voikoHypataTahan(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        if (sijaintiX < 0 || sijaintiX >= 8)
        {
            return;
        }
        if (sijaintiY < 0 || sijaintiY >= 8)
        {
            return;
        }
        if (lauta.nappulaTassa[sijaintiY][sijaintiX] == null)
        {
            mahdollisetSiirrot[sijaintiY][sijaintiX] = true;
            return;
        }
        if (lauta.nappulaTassa[sijaintiY][sijaintiX].puoli != this.puoli)
        {
            mahdollisetSiirrot[sijaintiY][sijaintiX] = true;
            return;
        }
        mahdollisetSiirrot[sijaintiY][sijaintiX] = false;
    }
}
