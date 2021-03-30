package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOikein() {
        
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(100);
        
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeKunRahaaTarpeeksi() {
        kortti.lataaRahaa(990);
        
        kortti.otaRahaa(500);
                
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuKunEiOleSaldoa() {
        kortti.otaRahaa(100);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRiittavastiSaldoa() {
        
        assertEquals(true, kortti.otaRahaa(10));
    }
}
