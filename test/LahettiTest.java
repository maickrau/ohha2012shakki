
import org.junit.*;
import static org.junit.Assert.*;


public class LahettiTest {
    
    PeliLauta lauta;
    Lahetti ekaLahetti;
    Lahetti tokaLahetti;
    Lahetti vihollisLahetti;
    
    public LahettiTest() {
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
        ekaLahetti = (Lahetti)lauta.asetaNappula(new Lahetti(true), 4, 3);
        tokaLahetti = (Lahetti)lauta.asetaNappula(new Lahetti(true), 5, 3);
        vihollisLahetti = (Lahetti)lauta.asetaNappula(new Lahetti(false), 5, 4);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void lahettiEiVoiSyodaItseaan()
    {
        assertFalse(ekaLahetti.mahdollisetSiirrot(lauta)[ekaLahetti.sijaintiY][ekaLahetti.sijaintiX]);
        assertFalse(tokaLahetti.mahdollisetSiirrot(lauta)[tokaLahetti.sijaintiY][tokaLahetti.sijaintiX]);
        assertFalse(vihollisLahetti.mahdollisetSiirrot(lauta)[vihollisLahetti.sijaintiY][vihollisLahetti.sijaintiX]);
    }
    
    @Test
    public void lahettiVoiSyodaVinoon()
    {
        assertTrue(ekaLahetti.mahdollisetSiirrot(lauta)[vihollisLahetti.sijaintiY][vihollisLahetti.sijaintiX]);
        assertTrue(vihollisLahetti.mahdollisetSiirrot(lauta)[ekaLahetti.sijaintiY][ekaLahetti.sijaintiX]);
    }
    
    @Test
    public void lahettiEiVoiSyodaSuoraan()
    {
        assertFalse(ekaLahetti.mahdollisetSiirrot(lauta)[tokaLahetti.sijaintiY][tokaLahetti.sijaintiX]);
        assertFalse(vihollisLahetti.mahdollisetSiirrot(lauta)[tokaLahetti.sijaintiY][tokaLahetti.sijaintiX]);
    }
    
    @Test
    public void lahettiEiVoiHypataNappulanYli()
    {
        assertFalse(ekaLahetti.mahdollisetSiirrot(lauta)[5][6]);
        assertFalse(vihollisLahetti.mahdollisetSiirrot(lauta)[2][3]);
    }
    
    @Test
    public void lahettiEiVoiSyodaOmaa()
    {
        //tapahtuu vain jos joku ylentää sotilaan lähetiksi (??)
        Lahetti testiLahetti = (Lahetti)lauta.asetaNappula(new Lahetti(true), 5, 2);
        assertFalse(ekaLahetti.mahdollisetSiirrot(lauta)[2][5]);
        assertFalse(testiLahetti.mahdollisetSiirrot(lauta)[3][4]);
    }
}
