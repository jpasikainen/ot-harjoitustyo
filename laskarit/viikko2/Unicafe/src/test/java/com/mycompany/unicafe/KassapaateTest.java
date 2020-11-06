/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jpasikainen
 */
public class KassapaateTest {
    
    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(0);
        kassa = new Kassapaate();
    }
    
    @Test
    public void kassapaateLuontiOikeaRahamaara() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassapaateLuontiEiMyytyjaLounaita() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KateisostoEdullinenLounasTarpeeksiRahaa() {
        assertEquals(10, kassa.syoEdullisesti(250));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void KateisostoEdullinenLounasEiTarpeeksiRahaa() {
        assertEquals(200, kassa.syoEdullisesti(200));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void KateisostoMaukasLounasTarpeeksiRahaa() {
        assertEquals(10, kassa.syoMaukkaasti(410));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KateisostoMaukasLounasEiTarpeeksiRahaa() {
        assertEquals(200, kassa.syoMaukkaasti(200));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KorttiostoEdullinenLounasTarpeeksiRahaa() {
        kortti.lataaRahaa(250);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void KorttiostoEdullinenLounasEiTarpeeksiRahaa() {
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void KorttiostoMaukasLounasTarpeeksiRahaa() {
        kortti.lataaRahaa(410);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KorttiostoMaukasLounasEiTarpeeksiRahaa() {
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KortillaOstaessaKassanRahaEiMuutu() {
        kortti.lataaRahaa(1000);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void KortinLataus() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals("saldo: 10.00", kortti.toString());
        assertEquals(100000+1000, kassa.kassassaRahaa());
    }
    
    @Test
    public void KortinLatausIlmanRahaa() {
        kassa.lataaRahaaKortille(kortti, -100);
        assertEquals("saldo: 0.00", kortti.toString());
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
