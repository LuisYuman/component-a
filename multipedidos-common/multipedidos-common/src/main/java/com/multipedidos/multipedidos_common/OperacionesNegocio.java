package com.multipedidos.multipedidos_common;


public class OperacionesNegocio {

    private static final double IVA = 0.12;

    public static double calcularTotalConIVA(double subtotal) {
        if (subtotal < 0) throw new IllegalArgumentException("Subtotal inválido");
        return subtotal * (1 + IVA);
    }

    public static double aplicarDescuento(double total, double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100)
            throw new IllegalArgumentException("Descuento inválido");
        return total - (total * (porcentaje / 100.0));
    }

    public static boolean validarCodigo(String codigo) {
        return codigo != null && codigo.matches("[A-Z]{3}-\\d{4}");
    }

    public static double calcularTotalConIVAyDescuento(double subtotal, Double descuento) {
        double total = calcularTotalConIVA(subtotal);
        if (descuento != null && descuento > 0)
            total = aplicarDescuento(total, descuento);
        return total;
    }
}
