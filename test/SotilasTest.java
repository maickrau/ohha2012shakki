
import org.junit.*;
import static org.junit.Assert.*;

public class SotilasTest {
    
    PeliLauta lauta;
    Sotilas ekaSotilas;
    Sotilas tokaSotilas;
    Sotilas kolmasSotilas;
    Sotilas vihollisSotilas;
    Sotilas tokaVihollisSotilas;
    
    public SotilasTest() {
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
        ekaSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(true), 3, 3);
        tokaSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(true), 4, 3);
        kolmasSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(true), 2, 4);
        vihollisSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(false), 4, 4);
        tokaVihollisSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(false), 5, 4);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void sotilasVoiSyodaVinoon()
    {
        assertTrue(ekaSotilas.mahdollisetSiirrot(lauta)[4][4]);
        assertTrue(tokaVihollisSotilas.mahdollisetSiirrot(lauta)[3][4]);
    }
    
    @Test
    public void sotilasEiVoiSyodaEdesta()
    {
        assertFalse(tokaSotilas.mahdollisetSiirrot(lauta)[4][4]);
        assertFalse(vihollisSotilas.mahdollisetSiirrot(lauta)[3][4]);
    }
    
    @Test
    public void sotilasEiVoisyodaOmaa()
    {
        assertFalse(ekaSotilas.mahdollisetSiirrot(lauta)[kolmasSotilas.sijaintiY][kolmasSotilas.sijaintiX]);
    }
    
    @Test
    public void sotilasEiVoiSyodaItseaan()
    {
        assertFalse(ekaSotilas.mahdollisetSiirrot(lauta)[ekaSotilas.sijaintiY][ekaSotilas.sijaintiX]);
        assertFalse(tokaSotilas.mahdollisetSiirrot(lauta)[tokaSotilas.sijaintiY][tokaSotilas.sijaintiX]);
        assertFalse(kolmasSotilas.mahdollisetSiirrot(lauta)[kolmasSotilas.sijaintiY][kolmasSotilas.sijaintiX]);
        assertFalse(vihollisSotilas.mahdollisetSiirrot(lauta)[vihollisSotilas.sijaintiY][vihollisSotilas.sijaintiX]);
        assertFalse(tokaVihollisSotilas.mahdollisetSiirrot(lauta)[tokaVihollisSotilas.sijaintiY][tokaVihollisSotilas.sijaintiX]);
    }
    
    @Test
    public void sotilasVoiKulkeaEteenpain()
    {
        assertTrue(ekaSotilas.mahdollisetSiirrot(lauta)[ekaSotilas.sijaintiY+1][ekaSotilas.sijaintiX]);
        assertTrue(kolmasSotilas.mahdollisetSiirrot(lauta)[kolmasSotilas.sijaintiY+1][kolmasSotilas.sijaintiX]);
        assertTrue(tokaVihollisSotilas.mahdollisetSiirrot(lauta)[tokaVihollisSotilas.sijaintiY-1][tokaVihollisSotilas.sijaintiX]);
    }
    
    @Test
    public void sotilasEiVoiKulkeaTaaksepain()
    {
        assertFalse(ekaSotilas.mahdollisetSiirrot(lauta)[ekaSotilas.sijaintiY-1][ekaSotilas.sijaintiX]);
        assertFalse(tokaSotilas.mahdollisetSiirrot(lauta)[tokaSotilas.sijaintiY-1][tokaSotilas.sijaintiX]);
        assertFalse(kolmasSotilas.mahdollisetSiirrot(lauta)[kolmasSotilas.sijaintiY-1][kolmasSotilas.sijaintiX]);
        assertFalse(vihollisSotilas.mahdollisetSiirrot(lauta)[vihollisSotilas.sijaintiY+1][vihollisSotilas.sijaintiX]);
        assertFalse(tokaVihollisSotilas.mahdollisetSiirrot(lauta)[tokaVihollisSotilas.sijaintiY+1][tokaVihollisSotilas.sijaintiX]);
    }
}
