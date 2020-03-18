package com.mycompany.unicafe;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOnOikeinAlussa() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldoOikeinKunKortilleLadataanRahaa() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    
    @Test
    public void saldoOikeinKunOtetaanRahaaJosRahaaTarpeeksi() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosOtetaanJaRahaaTarpeeksi() {
        assertTrue(kortti.otaRahaa(500));
    }
    
    @Test
    public void saldoaOikeinKunOtetaanRahaaJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaFalseJosOtetaanJaRahaaEiOleTarpeeksi() {
        assertFalse(kortti.otaRahaa(1500));
    }
    
    @Test
    public void saldoMetodiPalauttaaOikeinAlussa() {
        assertTrue(kortti.saldo()==1000);
    }
    
    @Test
    public void saldoMetodiPalauttaaOikeinAlussaJosOtettuRahaaOnnistuneesti() {
        kortti.otaRahaa(500);
        assertTrue(kortti.saldo()==500);
    }
    
    @Test
    public void saldoMetodiPalauttaaOikeinAlussaJosOtettuRahaaEpaonnistuneesti() {
        kortti.otaRahaa(1500);
        assertTrue(kortti.saldo()==1000);
    }
}
