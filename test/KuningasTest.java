import org.junit.*;
import static org.junit.Assert.*;

public class KuningasTest {
    
    Kuningas kuningas;
    PeliLauta lauta;
    boolean[][] siirrot;
    
    public KuningasTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        lauta = new PeliLauta();
        kuningas = (Kuningas)lauta.asetaNappula(new Kuningas(true), 3, 5);
        siirrot = kuningas.mahdollisetSiirrot(lauta);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void liikkuuYhdenVasemmalle()
    {
        int alkuX = kuningas.sijaintiX;
        int alkuY = kuningas.sijaintiY;
        if (alkuX > 0)
        {
            assertTrue(lauta.siirraNappulaa(alkuX, alkuY, alkuX-1, alkuY));
            assertTrue(lauta.nappulaTassa[alkuY][alkuX] == null);
            assertTrue(kuningas.sijaintiY == alkuY);
            assertTrue(kuningas.sijaintiX == alkuX-1);
        }
        assertTrue(lauta.nappulaTassa[kuningas.sijaintiY][kuningas.sijaintiX] == kuningas);
    }
    
    @Test
    public void eiLiikuKahtaVasemmalle()
    {
        int alkuX = kuningas.sijaintiX;
        int alkuY = kuningas.sijaintiY;
        if (alkuX > 1)
        {
            assertFalse(lauta.siirraNappulaa(alkuX, alkuY, alkuX-2, alkuY));
            assertTrue(lauta.nappulaTassa[alkuY][alkuX] == kuningas);
        }
        assertTrue(lauta.nappulaTassa[kuningas.sijaintiY][kuningas.sijaintiX] == kuningas);
        assertTrue(kuningas.sijaintiY == alkuY);
        assertTrue(kuningas.sijaintiX == alkuX);
    }
    
    @Test
    public void voiLiikkuaYhdenRuudun()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if ((Math.abs(x-kuningas.sijaintiX) <= 1 && Math.abs(y-kuningas.sijaintiY) <= 1)
                   && (x != kuningas.sijaintiX || y != kuningas.sijaintiY))
                {
                    assertTrue(siirrot[y][x]);
                }
            }
        }
    }
    
    @Test
    public void eiVoiLiikkuaMontaRuutua()
    {
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if (Math.abs(x-kuningas.sijaintiX) > 1 && Math.abs(y-kuningas.sijaintiY) > 1)
                {
                    assertFalse(siirrot[y][x]);
                }
            }
        }
    }
    
    @Test
    public void eiVoiSyodaItseaan()
    {
        assertFalse(siirrot[kuningas.sijaintiY][kuningas.sijaintiX]);
    }
}
