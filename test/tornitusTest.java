
import org.junit.*;
import static org.junit.Assert.*;

public class tornitusTest {
    
    PeliLauta lauta;
    Kuningas kuningas;
    Torni vasenTorni;
    Torni oikeaTorni;
    
    public tornitusTest() {
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
        kuningas = (Kuningas)lauta.asetaNappula(new Kuningas(true), 4, 0);
        vasenTorni = (Torni)lauta.asetaNappula(new Torni(true), 0, 0);
        oikeaTorni = (Torni)lauta.asetaNappula(new Torni(true), 7, 0);
    }
    
    @After
    public void tearDown() {
    }
}
