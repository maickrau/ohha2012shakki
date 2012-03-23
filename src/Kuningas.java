
public class Kuningas extends Nappula
{
    /**
     * jos nappula on liikkunut, tornitus ei ole mahdollista
     */
    boolean liikkunut;
    public Kuningas(boolean puoli)
    {
        super(puoli);
        liikkunut = false;
    }
    public Kuningas(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
        liikkunut = false;
    }
    @Override
    public void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        //tornitus
        if (sijaintiX == this.sijaintiX-2)
        {
            lauta.pakotaNappulaSiirtymaan(0, sijaintiY, sijaintiX-1, sijaintiY);
        }
        if (sijaintiX == this.sijaintiX+2)
        {
            lauta.pakotaNappulaSiirtymaan(7, sijaintiY, sijaintiX+1, sijaintiY);
        }
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = null;
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        lauta.nappulaTassa[this.sijaintiY][this.sijaintiX] = this;
        liikkunut = true;
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        tyhjaaMahdollisetSiirrot();
        for (int x = sijaintiX-1; x <= sijaintiX+1 && x < 8; x++)
        {
            if (x < 0)
            {
                x = 0;
            }
            for (int y = sijaintiY-1; y <= sijaintiY+1 && y < 8; y++)
            {
                if (y < 0)
                {
                    y = 0;
                }
                if (!lauta.onkoUhattu(puoli, x, y))
                {
                    mahdollisetSiirrot[y][x] = true;
                    if (lauta.nappulaTassa[y][x] != null)
                    {
                        if (lauta.nappulaTassa[y][x].puoli == puoli)
                        {
                            mahdollisetSiirrot[y][x] = false;
                        }
                    }
                }
            }
        }
        tarkistaTornitus(lauta);
        return mahdollisetSiirrot;
    }
    /**
     * tarkistaa onko tornitus mahdollista ja päivittää sen mahdollisetSiirrot[][] -taulukkoon
     */
    private void tarkistaTornitus(PeliLauta lauta)
    {
        if (!liikkunut)
        {
            boolean tornitusMahdollista = true;
            if (lauta.nappulaTassa[sijaintiY][0] != null)
            {
                if (lauta.nappulaTassa[sijaintiY][0] instanceof Torni)
                {
                    if (((Torni)lauta.nappulaTassa[sijaintiY][0]).liikkunut)
                    {
                        tornitusMahdollista = false;
                    }
                    for (int x = 1; x < sijaintiX; x++)
                    {
                        if (lauta.nappulaTassa[sijaintiY][x] != null)
                        {
                            tornitusMahdollista = false;
                        }
                        if (lauta.onkoUhattu(puoli, x, sijaintiY))
                        {
                            tornitusMahdollista = false;
                        }
                    }
                    if (tornitusMahdollista)
                    {
                        mahdollisetSiirrot[sijaintiY][sijaintiX-2] = true;
                    }
                }
            }
            tornitusMahdollista = true;
            if (lauta.nappulaTassa[sijaintiY][7] != null)
            {
                if (lauta.nappulaTassa[sijaintiY][7] instanceof Torni)
                {
                    if (((Torni)lauta.nappulaTassa[sijaintiY][7]).liikkunut)
                    {
                        tornitusMahdollista = false;
                    }
                    for (int x = sijaintiX+1; x < 8; x++)
                    {
                        if (lauta.nappulaTassa[sijaintiY][x] != null)
                        {
                            tornitusMahdollista = false;
                        }
                        if (lauta.onkoUhattu(puoli, x, sijaintiY))
                        {
                            tornitusMahdollista = false;
                        }
                    }
                    if (tornitusMahdollista)
                    {
                        mahdollisetSiirrot[sijaintiY][sijaintiX+2] = true;
                    }
                }
            }
        }
    }
}
