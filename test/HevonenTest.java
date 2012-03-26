
import org.junit.*;
import static org.junit.Assert.*;

public class HevonenTest {
    
    PeliLauta lauta;
    Hevonen ekaHeppa;
    Hevonen tokaHeppa;
    Hevonen vihollisHeppa;
    
    public HevonenTest() {
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
        ekaHeppa = (Hevonen)lauta.asetaNappula(new Hevonen(true), 3, 3);
        tokaHeppa = (Hevonen)lauta.asetaNappula(new Hevonen(true), 5, 4);
        vihollisHeppa = (Hevonen)lauta.asetaNappula(new Hevonen(false), 5, 2);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void heppaEiVoiSyodaItseaan()
    {
        assertFalse(ekaHeppa.mahdollisetSiirrot(lauta)[ekaHeppa.sijaintiY][ekaHeppa.sijaintiX]);
        assertFalse(tokaHeppa.mahdollisetSiirrot(lauta)[tokaHeppa.sijaintiY][tokaHeppa.sijaintiX]);
        assertFalse(vihollisHeppa.mahdollisetSiirrot(lauta)[vihollisHeppa.sijaintiY][vihollisHeppa.sijaintiX]);
    }
    
    @Test
    public void heppaEiVoiSyodaOmaa()
    {
        assertFalse(ekaHeppa.mahdollisetSiirrot(lauta)[4][5]);
    }
    
    @Test
    public void heppaVoiSyodaVihollisen()
    {
        assertTrue(ekaHeppa.mahdollisetSiirrot(lauta)[2][5]);
    }
    
    @Test
    public void vihollisHeppaVoiSyodaEkan()
    {
        assertTrue(vihollisHeppa.mahdollisetSiirrot(lauta)[3][3]);
    }
    
    @Test
    public void vihollisHeppaEiVoiSyodaTokaa()
    {
        assertFalse(vihollisHeppa.mahdollisetSiirrot(lauta)[4][5]);
    }
    
    @Test
    public void heppaSyoVihollisenJaLiikkuu()
    {
        assertTrue(lauta.siirraNappulaa(3, 3, 5, 2));
        assertTrue(lauta.nappulaTassa[3][3] == null);
        assertTrue(lauta.nappulaTassa[2][5] == ekaHeppa);
        assertTrue(ekaHeppa.sijaintiX == 5);
        assertTrue(ekaHeppa.sijaintiY == 2);
    }

    @Test
    public void syotyVihollinenOnKuollut()
    {
        assertTrue(lauta.siirraNappulaa(3, 3, 5, 2));
        assertTrue(vihollisHeppa.sijaintiX == -10);
        assertTrue(vihollisHeppa.sijaintiY == -10);
    }
}
