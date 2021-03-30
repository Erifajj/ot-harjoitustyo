import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {
    
    Maksukortti kortti;

    public MaksukorttiTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
    }
    
    @Test
    public void konstruktoriAsettaaSalsonOikein() {
        
        String vastaus = kortti.toString();
        
        assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        
        kortti.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        
        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSaldonArvoEiMuutaSaldoa() {
        kortti.lataaRahaa(-5);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void edullinenOnnistuuTasarahalla() {
        //Laitetaan kortille rahaa riittämään kolmen maukkaan ja viimeksi yhden edullisen lounaan verran
        kortti.lataaRahaa(4.5);
        
        //Otetaan kortilta kolme maukkaan lounaan verran rahaa jotta jää rahaa tasan edulliselle lounaalle
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        
        kortti.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
    
    @Test
    public void maukasOnnistuuTasarahalla() {
        //Laitetaan kortille rahaa riittämään neljän maukkaan lounaan verran
        kortti.lataaRahaa(6);
        
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
}