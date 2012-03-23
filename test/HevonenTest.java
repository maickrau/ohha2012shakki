
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
        assertTrue(ekaHeppa.sijaintiX == 2);
        assertTrue(ekaHeppa.sijaintiY == 5);
        assertTrue(lauta.nappulaTassa[3][3] == null);
        assertTrue(lauta.nappulaTassa[5][2] == ekaHeppa);
    }

    @Test
    public void syotyVihollinenOnKuollut()
    {
        assertTrue(lauta.siirraNappulaa(3, 3, 5, 2));
        assertTrue(vihollisHeppa.sijaintiX == -10);
        assertTrue(vihollisHeppa.sijaintiY == -10);
    }
}
