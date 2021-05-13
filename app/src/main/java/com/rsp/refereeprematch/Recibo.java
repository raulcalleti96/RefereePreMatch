package com.rsp.refereeprematch;

public class Recibo {

    public void Recibo(){}

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    String Nombre;

    public String getEmailArbitro() {
        return EmailArbitro;
    }

    public void setEmailArbitro(String emailArbitro) {
        EmailArbitro = emailArbitro;
    }

    public String getEmailA1() {
        return EmailA1;
    }

    public void setEmailA1(String emailA1) {
        EmailA1 = emailA1;
    }

    public String getEmailA2() {
        return EmailA2;
    }

    public void setEmailA2(String emailA2) {
        EmailA2 = emailA2;
    }

    String EmailArbitro;
    String EmailA1;
    String EmailA2;

    public String getPartido() {
        return Partido;
    }

    public void setPartido(String partido) {
        Partido = partido;
    }

    String Partido;
    String PagoTotal, PagoArbitroPrincipal, PagoAsistente1, PagoAsistente2, PagoRFEF;

    public String getPagoTotal() {
        return PagoTotal;
    }

    public void setPagoTotal(String pagoTotal) {
        PagoTotal = pagoTotal;
    }

    public String getPagoArbitroPrincipal() {
        return PagoArbitroPrincipal;
    }

    public void setPagoArbitroPrincipal(String pagoArbitroPrincipal) {
        PagoArbitroPrincipal = pagoArbitroPrincipal;
    }
    public String getPagoAsistente1() {
        return PagoAsistente1;
    }

    public void setPagoAsistente1(String pagoAsistente1) {
        PagoAsistente1 = pagoAsistente1;
    }

    public String getPagoAsistente2() {
        return PagoAsistente2;
    }

    public void setPagoAsistente2(String pagoAsistente2) {
        PagoAsistente2 = pagoAsistente2;
    }

    public String getPagoRFEF() {
        return PagoRFEF;
    }

    public void setPagoRFEF(String pagoRFEF) {
        PagoRFEF = pagoRFEF;
    }
}
