
import org.junit.*;
import static org.junit.Assert.*;

public class TorniTest {
    
    PeliLauta lauta;
    Torni vasenTorni;
    Torni oikeaTorni;
    Torni vihollisTorni;
    
    public TorniTest() {
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
        vasenTorni = (Torni)lauta.asetaNappula(new Torni(true), 3, 3);
        oikeaTorni = (Torni)lauta.asetaNappula(new Torni(true), 5, 3);
        vihollisTorni = (Torni)lauta.asetaNappula(new Torni(false), 5, 4);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void torniEiVoiSyodaItseaan()
    {
        assertFalse(vasenTorni.mahdollisetSiirrot(lauta)[vasenTorni.sijaintiY][vasenTorni.sijaintiX]);
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[oikeaTorni.sijaintiY][oikeaTorni.sijaintiX]);
        assertFalse(vihollisTorni.mahdollisetSiirrot(lauta)[vihollisTorni.sijaintiY][vihollisTorni.sijaintiX]);
    }
    
    @Test
    public void oikeaTorniVoiSyodaVihollisen()
    {
        assertTrue(oikeaTorni.mahdollisetSiirrot(lauta)[vihollisTorni.sijaintiY][vihollisTorni.sijaintiX]);
    }
    
    @Test
    public void vasenTorniEiVoiSyodaVihollista()
    {
        assertFalse(vasenTorni.mahdollisetSiirrot(lauta)[vihollisTorni.sijaintiY][vihollisTorni.sijaintiX]);
    }
    
    @Test
    public void torniEiSyoOmanPuoleisiaTorneja()
    {
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[vasenTorni.sijaintiY][vasenTorni.sijaintiX]);
        assertFalse(vasenTorni.mahdollisetSiirrot(lauta)[oikeaTorni.sijaintiY][oikeaTorni.sijaintiX]);
    }
    
    @Test
    public void torniEiVoiHypataTorninYli()
    {
        assertFalse(vasenTorni.mahdollisetSiirrot(lauta)[3][6]);
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[3][2]);
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[6][5]);
        assertFalse(vihollisTorni.mahdollisetSiirrot(lauta)[2][5]);
    }
    
    @Test
    public void torniSyoVihollisenJaLiikkuu()
    {
        assertTrue(lauta.siirraNappulaa(5, 3, 5, 4));
        assertTrue(lauta.nappulaTassa[3][5] == null);
        assertTrue(lauta.nappulaTassa[4][5] == oikeaTorni);
        assertTrue(oikeaTorni.sijaintiX == 5);
        assertTrue(oikeaTorni.sijaintiY == 4);
        assertTrue(vihollisTorni.sijaintiX == -10);
        assertTrue(vihollisTorni.sijaintiY == -10);
    }
    
    @Test
    public void torniEiLiikuVinoon()
    {
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[oikeaTorni.sijaintiY+1][oikeaTorni.sijaintiX+1]);
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[oikeaTorni.sijaintiY-1][oikeaTorni.sijaintiX+1]);
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[oikeaTorni.sijaintiY-1][oikeaTorni.sijaintiX-1]);
        assertFalse(oikeaTorni.mahdollisetSiirrot(lauta)[oikeaTorni.sijaintiY+1][oikeaTorni.sijaintiX-1]);
    }
}
