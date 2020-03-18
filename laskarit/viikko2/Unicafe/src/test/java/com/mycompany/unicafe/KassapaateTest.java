package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    
    @Test
    public void luotuKassapaateOnOlemassa(){
        assertTrue(kassapaate!=null);
    }
    
    @Test
    public void luodussaKassapaatteessaRahaaOikein(){
        assertTrue(kassapaate.kassassaRahaa()==100000);
    }
    
    @Test
    public void luodussaKassapaatteessaMyytyjaEdullisiaLounaitaOikein(){
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==0);
    }
    
    @Test
    public void luodussaKassapaatteessaMyytyjaMaukkaitaLounaitaOikein(){
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void kateisostoToimiiJosMaksuRiittavaMyytyjenLounaidenMaaraOikein(){
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==1);
    }
    
    @Test
    public void kateisostoToimiiJosMaksuRiittavaKassapaateSaldoOikein(){
        kassapaate.syoEdullisesti(300);
        assertTrue(kassapaate.kassassaRahaa()==100240);
    }
    
    @Test
    public void kateisostoToimiiJosMaksuRiittavaVaihtorahaOikeinEdulliset(){
        assertTrue(kassapaate.syoEdullisesti(300)==60);
    }
    
    @Test
    public void kateisostoToimiiJosMaksuRiittavaVaihtorahaOikeinMaukkaat(){
        assertTrue(kassapaate.syoMaukkaasti(500)==100);
    }
    
    @Test
    public void kateisostoEiToimiJosMaksuEiOleRiittavaKassapaateSaldoOikein() {
        kassapaate.syoMaukkaasti(200);
        assertTrue(kassapaate.kassassaRahaa()==100000);
    }
    
    @Test
    public void kateisostoEiToimiJosMaksuEiOleRiittavaJaVaihtorahaOikeinEdulliset() {
        assertTrue(kassapaate.syoEdullisesti(200)==200);
    }
    
    @Test
    public void kateisostoEiToimiJosMaksuEiOleRiittavaJaVaihtorahaOikeinMaukkaat() {
        assertTrue(kassapaate.syoMaukkaasti(200)==200);
    }
    
    @Test
    public void kateisostoEiToimiJosMaksuEiOleRiittavaLounaitaMyytyMaaraOikein() {
        kassapaate.syoMaukkaasti(200);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void korttiostoToimiiiMetodiPalauttaaTrueJosKortillaSaldoaEdulliset(){
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoToimiiiKortinSaldoOikeinOstonJalkeenEdulliset(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==760);
    }
    
    @Test
    public void korttiostoToimiiiMyytyjenLounaidenMaaraOikeinEdulliset(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty() == 1);
    }
    
    @Test
    public void korttiostoToimiiiMetodiPalauttaaTrueJosKortillaSaldoaMaukkaat(){
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void korttiostoToimiiiKortinSaldoOikeinOstonJalkeenMaukkaat(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==600);
    }
    
    @Test
    public void korttiostoToimiiiMyytyjenLounaidenMaaraOikeinMaukkaat(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void korttiostoEiToimiJosEiTarpeeksiSaldoaSaldoOikeinEdulliset(){
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kortti.saldo()==200);
    }
    
    @Test
    public void korttiostoEiToimiJosEiTarpeeksiSaldoaMyytyjenLounaidenMaaraOikeinEdulliset(){
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.edullisiaLounaitaMyyty()==0);
    }
    
    @Test
    public void korttiostoEiToimiJosEiTarpeeksiSaldoaMetodiPalauttaaFalseEdulliset(){
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoEiToimiJosEiTarpeeksiSaldoaSaldoOikeinMaukkaat(){
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kortti.saldo()==200);
    }
    
    @Test
    public void korttiostoEiToimiJosEiTarpeeksiSaldoaMyytyjenLounaidenMaaraOikeinMaukkaat(){
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.maukkaitaLounaitaMyyty()==0);
    }
    
    @Test
    public void korttiostoEiToimiJosEiTarpeeksiSaldoaMetodiPalauttaaFalseMaukkaat(){
        Maksukortti kortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuMaksaessaKortillaOnnistuneestiEdulliset(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoEdullisesti(kortti);
        assertTrue(kassapaate.kassassaRahaa()==100000);
    }
    
    @Test
    public void kassassaOlevaRahamaaraEiMuutuMaksaessaKortillaOnnistuneestiMaukkaasti(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.syoMaukkaasti(kortti);
        assertTrue(kassapaate.kassassaRahaa()==100000);
    }
    
    @Test
    public void kassapaatteenSaldoKasvaaJosKortilleLadataanRahaa(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(kortti, 1000);
        assertTrue(kassapaate.kassassaRahaa()==101000);
    }
    
    @Test
    public void kassapaatteenSaldoEiKasvaJosKortilleLadataanRahaaNegatiivinenMaara(){
        Maksukortti kortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(kortti, -1);
        assertTrue(kassapaate.kassassaRahaa()==100000);
    }
}
