
public class Kuningas extends Nappula
{
    boolean siirtynyt;
    public Kuningas(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
        siirtynyt = false;
    }
    @Override
    public void nappulaSiirtyi(PeliLauta lauta, int sijaintiX, int sijaintiY)
    {
        this.sijaintiX = sijaintiX;
        this.sijaintiY = sijaintiY;
        siirtynyt = true;
    }
    @Override
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        boolean[][] tulos = new boolean[8][8];
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
                    tulos[y][x] = true;
                    if (lauta.nappulaTassa[y][x] != null)
                    {
                        if (lauta.nappulaTassa[y][x].puoli == puoli)
                        {
                            tulos[y][x] = false;
                        }
                    }
                }
            }
        }
        //tornitus
        return tulos;
    }
}
