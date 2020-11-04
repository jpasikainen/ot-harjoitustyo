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
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadata() {
        kortti.lataaRahaa(100);
        kortti.lataaRahaa(1);
        assertEquals("saldo: 1.11", kortti.toString());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaRahaa() {
        kortti.lataaRahaa(-100);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void korttiOtaRahaaToimii() {
        kortti.otaRahaa(1);
        kortti.otaRahaa(3);
        assertEquals("saldo: 0.06", kortti.toString());
    }
    
    @Test
    public void korttiSaldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortinOtaRahaaPalauttaaOikeanTrue() {
        assertTrue(kortti.otaRahaa(9));
    }
    
    @Test
    public void kortinOtaRahaaPalauttaaOikeanFalse() {
        assertTrue(!kortti.otaRahaa(11));
    }
}
