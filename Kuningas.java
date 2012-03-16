
public class Kuningas extends Nappula
{
    public Kuningas(boolean puoli, int sijaintiX, int sijaintiY)
    {
        super(puoli, sijaintiX, sijaintiY);
    }
    public boolean[][] mahdollisetSiirrot(PeliLauta lauta)
    {
        boolean[][] tulos = new boolean[8][8];
        for (int x = sijaintiX-1; x < sijaintiX+1 && x < 8; x++)
        {
            if (x < 0)
            {
                x = 0;
            }
            for (int y = sijaintiY-1; y < sijaintiY+1 && y < 8; y++)
            {
                if (y < 0)
                {
                    y = 0;
                }
                if (!lauta.onkoUhattu(puoli, x, y))
                {
                    tulos[x][y] = true;
                }
            }
        }
        return tulos;
    }
}
