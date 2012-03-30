
import org.junit.*;
import static org.junit.Assert.*;

public class EnPassantTest {
    
    PeliLauta lauta;
    Sotilas ekaSotilas;
    Sotilas vihollisSotilas;
    Sotilas tokaVihollisSotilas;
    Sotilas tokaSotilas;
    Sotilas kolmasSotilas;
    Sotilas kolmasVihollisSotilas;
    public EnPassantTest() {
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
        ekaSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(true), 2, 4);
        vihollisSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(false), 3, 6);
        tokaVihollisSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(false), 1, 6);
        tokaSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(true), 3, 1);
        kolmasSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(true), 5, 1);
        kolmasVihollisSotilas = (Sotilas)lauta.asetaNappula(new Sotilas(false), 4, 3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaKuudellaNappulalla()
    {
        lauta.kummanVuoro = false;
        assertTrue(lauta.pelaaVuoro(3, 6, 3, 4));
        assertTrue(ekaSotilas.mahdollisetSiirrot(lauta)[5][3]);
        assertTrue(lauta.pelaaVuoro(3, 1, 3, 3));
        assertTrue(kolmasVihollisSotilas.mahdollisetSiirrot(lauta)[2][3]);
        assertTrue(lauta.pelaaVuoro(1, 6, 1, 4));
        assertFalse(ekaSotilas.mahdollisetSiirrot(lauta)[5][3]);
        assertTrue(ekaSotilas.mahdollisetSiirrot(lauta)[5][1]);
        assertTrue(lauta.pelaaVuoro(5, 1, 5, 3));
        assertFalse(kolmasVihollisSotilas.mahdollisetSiirrot(lauta)[2][3]);
        assertTrue(kolmasVihollisSotilas.mahdollisetSiirrot(lauta)[2][5]);
        assertTrue(lauta.pelaaVuoro(4, 3, 5, 3));
        assertTrue(tokaSotilas.sijaintiY == -10);
        assertTrue(tokaSotilas.sijaintiX == -10);
        assertFalse(ekaSotilas.mahdollisetSiirrot(lauta)[5][1]);
    }
    
    @Test
    public void eiVoiSyodaTornia()
    {
        vihollisSotilas.nappulaKuoli();
        lauta.kummanVuoro = false;
        lauta.asetaNappula(new Torni(false), 1, 6);
        assertTrue(lauta.pelaaVuoro(1, 6, 1, 4));
        assertFalse(ekaSotilas.mahdollisetSiirrot(lauta)[5][1]);
    }
}
