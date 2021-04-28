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
    double PagoTotal, PagoArbitroPrincipal, PagoAsistente1, PagoAsistente2, PagoRFEF;

    public double getPagoTotal() {
        return PagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        PagoTotal = pagoTotal;
    }

    public double getPagoArbitroPrincipal() {
        return PagoArbitroPrincipal;
    }

    public void setPagoArbitroPrincipal(double pagoArbitroPrincipal) {
        PagoArbitroPrincipal = pagoArbitroPrincipal;
    }
    public double getPagoAsistente1() {
        return PagoAsistente1;
    }

    public void setPagoAsistente1(double pagoAsistente1) {
        PagoAsistente1 = pagoAsistente1;
    }

    public double getPagoAsistente2() {
        return PagoAsistente2;
    }

    public void setPagoAsistente2(double pagoAsistente2) {
        PagoAsistente2 = pagoAsistente2;
    }

    public double getPagoRFEF() {
        return PagoRFEF;
    }

    public void setPagoRFEF(double pagoRFEF) {
        PagoRFEF = pagoRFEF;
    }
}
