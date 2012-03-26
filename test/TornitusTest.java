
import org.junit.*;
import static org.junit.Assert.*;

public class TornitusTest {
    
    PeliLauta lauta;
    Kuningas kuningas;
    Torni vasenTorni;
    Torni oikeaTorni;
    
    public TornitusTest() {
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
    
    @Test
    public void tornitusVasemmallaTornilla()
    {
        assertTrue(lauta.siirraNappulaa(4, 0, 2, 0));
        assertTrue(lauta.nappulaTassa[0][2] == kuningas);
        assertTrue(kuningas.sijaintiX == 2);
        assertTrue(lauta.nappulaTassa[0][3] == vasenTorni);
        assertTrue(vasenTorni.sijaintiX == 3);
    }
    
    @Test
    public void tornitusOikeallaTornilla()
    {
        assertTrue(lauta.siirraNappulaa(4, 0, 6, 0));
        assertTrue(lauta.nappulaTassa[0][6] == kuningas);
        assertTrue(kuningas.sijaintiX == 6);
        assertTrue(lauta.nappulaTassa[0][5] == oikeaTorni);
        assertTrue(oikeaTorni.sijaintiX == 5);
    }
    
    @Test
    public void eiVoiTornittaaOlemattomallaTornilla()
    {
        assertTrue(lauta.siirraNappulaa(0, 0, 0, 1));
        assertTrue(lauta.siirraNappulaa(7, 0, 7, 1));
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][2]);
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][6]);
    }
    
    @Test
    public void eiVoiTornittaaHevosella()
    {
        assertTrue(lauta.siirraNappulaa(0, 0, 0, 1));
        assertTrue(lauta.siirraNappulaa(7, 0, 7, 1));
        lauta.asetaNappula(new Hevonen(true), 0, 0);
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][2]);
        lauta.asetaNappula(new Hevonen(true), 7, 0);
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][6]);
    }
    
    @Test
    public void eiVoiTornittaaVihollisenTornilla()
    {
        assertTrue(lauta.siirraNappulaa(0, 0, 0, 1));
        assertTrue(lauta.siirraNappulaa(7, 0, 7, 1));
        lauta.asetaNappula(new Torni(false), 0, 0);
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][2]);
        lauta.asetaNappula(new Torni(false), 7, 0);
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][6]);
    }
    
    @Test
    public void eiVoiTornittaaLiikkuneellaTornilla()
    {
        assertTrue(lauta.siirraNappulaa(0, 0, 0, 1));
        assertTrue(lauta.siirraNappulaa(7, 0, 7, 1));
        assertTrue(lauta.siirraNappulaa(0, 1, 0, 0));
        assertTrue(lauta.siirraNappulaa(7, 1, 7, 0));
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][2]);
        assertFalse(kuningas.mahdollisetSiirrot(lauta)[0][6]);
    }
    /*
    @Test
    public void eiVoiTornittaaKunKuningasUhattu()
    {
        //TODO
    }
    @Test
    public void eiVoiTornittaaKunReittiUhattu()
    {
        //TODO
    }
    */
}
