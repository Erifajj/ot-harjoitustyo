package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(0);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(paate!=null);      
    }
    
    @Test
    public void luotuOikein() {
        assertEquals(1000, paate.kassassaRahaa());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasOstosOnnistui() {
        int vaihtoraha = paate.syoMaukkaasti(1000);
        
        assertEquals(600, vaihtoraha);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }  
    
    @Test
    public void edullinenOstosOnnistui() {
        int vaihtoraha = paate.syoEdullisesti(1000);
        
        assertEquals(760, vaihtoraha);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    } 
    
    @Test
    public void maukasOstosEpaOnnistui() {
        int vaihtoraha = paate.syoMaukkaasti(390);
        
        assertEquals(390, vaihtoraha);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }  
    
    @Test
    public void edullinenOstosEpaOnnistui() {
        int vaihtoraha = paate.syoEdullisesti(230);
        
        assertEquals(230, vaihtoraha);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasOstosOnnistuiKortilla() {
        kortti.lataaRahaa(400);
        
        assertEquals(true, paate.syoMaukkaasti(kortti));
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    } 
    
    @Test
    public void edullinenOstosOnnistuiKortilla() {
        kortti.lataaRahaa(240);
        
        assertEquals(true, paate.syoEdullisesti(kortti));
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    } 
    
    @Test
    public void maukasOstosEpaOnnistuiKortilla() {
        kortti.lataaRahaa(390);
        
        assertEquals(false, paate.syoMaukkaasti(kortti));
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenOstosEpaOnnistuiKortilla() {
        kortti.lataaRahaa(230);
        
        assertEquals(false, paate.syoEdullisesti(kortti));
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateinenSummaEiMuutuKortillaOstaessa() {
        kortti.lataaRahaa(240);
        
        boolean ostos = paate.syoEdullisesti(kortti);
        
        assertEquals(1000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortilleTalletusOnnistui() {
        paate.lataaRahaaKortille(kortti, 1000);
        
        assertEquals(1000, kortti.saldo());
        assertEquals(2000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleTalletusEpaOnnistui() {
        paate.lataaRahaaKortille(kortti, -5);
        
        assertEquals(0, kortti.saldo());
        assertEquals(1000, paate.kassassaRahaa());
    }
}
