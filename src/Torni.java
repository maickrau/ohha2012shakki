
public class Torni extends SuoraanMenevaNappula {
    /**
     * jos nappula on liikkunut, tornitus ei ole mahdollista
     */
    boolean liikkunut;
    public Torni(boolean puoli)
    {
        super(puoli);
        liikkunut = false;
    }
    public Torni(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
        liikkunut = false;
    }
    @Override
    public void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        liikkunut = true;
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = null;
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = this;
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        tyhjaaMahdollisetSiirrot();
        super.katsoSuunta(lauta, -1, 0);
        super.katsoSuunta(lauta, +1, 0);
        super.katsoSuunta(lauta, 0, -1);
        super.katsoSuunta(lauta, 0, +1);
        return mahdollisetSiirrot;
    }
}
