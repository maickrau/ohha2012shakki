public class Sotilas extends Nappula {
    public Sotilas(boolean puoli)
    {
        super(puoli);
    }
    public Sotilas(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
    }
    @Override
    public void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = null;
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = this;
        if ((this.sijaintiY == 7 && puoli) || (this.sijaintiY == 0 && !puoli))
        {
            //TODO: ylennys
        }
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        tyhjaaMahdollisetSiirrot();
        int suunta = 1;
        if (!puoli)
        {
            suunta = -1;
        }
        if (lauta.nappulaTassa[sijaintiY+suunta][sijaintiX] == null)
        {
            mahdollisetSiirrot[sijaintiY+suunta][sijaintiX] = true;
        }
        if (sijaintiX > 0)
        {
            if (lauta.nappulaTassa[sijaintiY+suunta][sijaintiX-1] != null)
            {
                if (lauta.nappulaTassa[sijaintiY+suunta][sijaintiX-1].puoli != this.puoli)
                {
                    mahdollisetSiirrot[sijaintiY+suunta][sijaintiX-1] = true;
                }
            }
        }
        if (sijaintiX < 7)
        {
            if (lauta.nappulaTassa[sijaintiY+suunta][sijaintiX+1] != null)
            {
                if (lauta.nappulaTassa[sijaintiY+suunta][sijaintiX+1].puoli != this.puoli)
                {
                    mahdollisetSiirrot[sijaintiY+suunta][sijaintiX+1] = true;
                }
            }
        }
        //TODO: en passant
        return mahdollisetSiirrot;
    }
}
