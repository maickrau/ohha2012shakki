public class Sotilas extends Nappula {
    private boolean[][] uhatutRuudut;
    public Sotilas(boolean puoli)
    {
        super(puoli);
        uhatutRuudut = new boolean[8][8];
    }
    public Sotilas(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
        uhatutRuudut = new boolean[8][8];
    }
    @Override
    public void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = null;
        if (Math.abs(this.sijaintiX-sijaintiX) == 1 && Math.abs(this.sijaintiY-sijaintiY) == 1)
        {
            //oliko en passant
            if (lauta.nappulaTassa[sijaintiY][sijaintiX] == null)
            {
                if (lauta.nappulaTassa[this.sijaintiY][sijaintiX] != null)
                {
                    if (lauta.nappulaTassa[this.sijaintiY][sijaintiX] instanceof Sotilas)
                    {
                        if (lauta.nappulaTassa[this.sijaintiY][sijaintiX].puoli != this.puoli)
                        {
                            lauta.nappulaSoiNappulan(this, lauta.nappulaTassa[this.sijaintiY][sijaintiX]);
                        }
                    }
                }
            }
        }
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
        //ekalla siirrolla 2 ruutua
        if ((sijaintiY == 1 && puoli) || (sijaintiY == 6 && !puoli))
        {
            if (lauta.nappulaTassa[sijaintiY+2*suunta][sijaintiX] == null && lauta.nappulaTassa[sijaintiY+suunta][sijaintiX] == null)
            {
                mahdollisetSiirrot[sijaintiY+2*suunta][sijaintiX] = true;
            }
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
            //en passant
            if (lauta.nappulaTassa[sijaintiY][sijaintiX-1] != null)
            {
                if (lauta.nappulaTassa[sijaintiY][sijaintiX-1] instanceof Sotilas)
                {
                    if (lauta.viimeSiirtoOliSotilaanTupla == sijaintiX-1)
                    {
                        if (lauta.nappulaTassa[sijaintiY][sijaintiX-1].puoli != this.puoli)
                        {
                            if ((puoli && sijaintiY == 4) || (!puoli && sijaintiY == 3))
                            {
                                mahdollisetSiirrot[sijaintiY+suunta][sijaintiX-1] = true;
                            }
                        }
                    }
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
            //en passant
            if (lauta.nappulaTassa[sijaintiY][sijaintiX+1] != null)
            {
                if (lauta.nappulaTassa[sijaintiY][sijaintiX+1] instanceof Sotilas)
                {
                    if (lauta.viimeSiirtoOliSotilaanTupla == sijaintiX+1)
                    {
                        if (lauta.nappulaTassa[sijaintiY][sijaintiX+1].puoli != this.puoli)
                        {
                            if ((puoli && sijaintiY == 4) || (!puoli && sijaintiY == 3))
                            {
                                mahdollisetSiirrot[sijaintiY+suunta][sijaintiX+1] = true;
                            }
                        }
                    }
                }
            }
        }
        return mahdollisetSiirrot;
    }
    @Override
    public boolean[][] uhatutRuudut(PeliLauta lauta)
    {
        mahdollisetSiirrot(lauta);
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                uhatutRuudut[y][x] = mahdollisetSiirrot[y][x];
            }
        }
        int suunta = 1;
        if (!puoli)
        {
            suunta = -1;
        }
        if (sijaintiX > 0)
        {
            uhatutRuudut[sijaintiY+suunta][sijaintiX-1] = true;
        }
        if (sijaintiX < 7)
        {
            uhatutRuudut[sijaintiY+suunta][sijaintiX+1] = true;
        }
        return uhatutRuudut;
    }
}
