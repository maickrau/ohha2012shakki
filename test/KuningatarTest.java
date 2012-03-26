
import org.junit.*;
import static org.junit.Assert.*;

public class KuningatarTest {
    
    PeliLauta lauta;
    Kuningatar ekaKuningatar;
    Kuningatar vihollisKuningatar;
    
    public KuningatarTest() {
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
        ekaKuningatar = (Kuningatar)lauta.asetaNappula(new Kuningatar(true), 4, 3);
        vihollisKuningatar = (Kuningatar)lauta.asetaNappula(new Kuningatar(false), 5, 3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kuningatarEiVoiSyodaItseaan()
    {
        assertFalse(ekaKuningatar.mahdollisetSiirrot(lauta)[ekaKuningatar.sijaintiY][ekaKuningatar.sijaintiX]);
        assertFalse(vihollisKuningatar.mahdollisetSiirrot(lauta)[vihollisKuningatar.sijaintiY][vihollisKuningatar.sijaintiX]);
    }
    
    @Test
    public void kuningatarEiVoiHypataNappulanYli()
    {
        assertFalse(ekaKuningatar.mahdollisetSiirrot(lauta)[3][6]);
        assertFalse(vihollisKuningatar.mahdollisetSiirrot(lauta)[3][3]);
    }
    
    @Test
    public void kuningatarEiVoiSyodaOmaaHeppaa()
    {
        Hevonen testiHevonen = (Hevonen)lauta.asetaNappula(new Hevonen(true), 4, 4);
        assertFalse(ekaKuningatar.mahdollisetSiirrot(lauta)[4][4]);
    }
    
    @Test
    public void kuningatarVoiMennaSuoraanJaVinoon()
    {
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[0][4]);
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[7][4]);
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[3][0]);
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[0][1]);
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[6][7]);
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[7][0]);
        assertTrue(ekaKuningatar.mahdollisetSiirrot(lauta)[0][7]);
    }
    
    @Test
    public void kuningatarEiVoiMennaKuinHeppa()
    {
        assertFalse(ekaKuningatar.mahdollisetSiirrot(lauta)[5][5]);
    }
}
