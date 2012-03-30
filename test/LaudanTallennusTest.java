
import org.junit.*;
import static org.junit.Assert.*;


public class LaudanTallennusTest {
    
    PeliLauta lauta;
    
    public LaudanTallennusTest() {
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
        lauta.teeAlkuLauta();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaKirjoitusJaLataus()
    {
        assertTrue(lauta.siirraNappulaa(0, 1, 0, 3));
        assertTrue(lauta.siirraNappulaa(3, 6, 3, 5));
        assertTrue(lauta.siirraNappulaa(0, 0, 0, 2));
        assertTrue(lauta.siirraNappulaa(4, 7, 3, 6));
        assertTrue(lauta.tallennaLauta("testilauta"));
        PeliLauta lauta2 = new PeliLauta();
        assertTrue(lauta2.lataaLauta("testilauta"));
        assertEquals(lauta.viimeSiirtoOliSotilaanTupla, lauta2.viimeSiirtoOliSotilaanTupla);
        assertEquals(lauta.kummanVuoro, lauta2.kummanVuoro);
        for (int y = 0; y < 8; y++)
        {
            for (int x = 0; x < 8; x++)
            {
                if (lauta.nappulaTassa[y][x] != null)
                {
                    assertTrue(lauta2.nappulaTassa[y][x] != null);
                    if (lauta.nappulaTassa[y][x] instanceof Kuningas)
                    {
                        assertTrue(lauta2.nappulaTassa[y][x] instanceof Kuningas);
                        assertEquals(((Kuningas)lauta.nappulaTassa[y][x]).liikkunut, ((Kuningas)lauta2.nappulaTassa[y][x]).liikkunut);
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Kuningatar)
                    {
                        assertTrue(lauta2.nappulaTassa[y][x] instanceof Kuningatar);
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Hevonen)
                    {
                        assertTrue(lauta2.nappulaTassa[y][x] instanceof Hevonen);
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Lahetti)
                    {
                        assertTrue(lauta2.nappulaTassa[y][x] instanceof Lahetti);
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Torni)
                    {
                        assertTrue(lauta2.nappulaTassa[y][x] instanceof Torni);
                        assertEquals(((Torni)lauta.nappulaTassa[y][x]).liikkunut, ((Torni)lauta2.nappulaTassa[y][x]).liikkunut);
                    }
                    if (lauta.nappulaTassa[y][x] instanceof Sotilas)
                    {
                        assertTrue(lauta2.nappulaTassa[y][x] instanceof Sotilas);
                    }
                }
                else
                {
                    assertTrue(lauta2.nappulaTassa[y][x] == null);
                }
            }
        }
    }
}
