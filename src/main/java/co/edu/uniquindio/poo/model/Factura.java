package co.edu.uniquindio.poo.model;

import co.edu.uniquindio.poo.model.Enum.EstadoFactura;
import co.edu.uniquindio.poo.model.Enum.MetodoPago;

import java.time.LocalDate;
import java.util.ArrayList;

public record Factura(String codigo, LocalDate fecha, double total,
                      EstadoFactura estadoFactura, MetodoPago metodoPago, Cliente cliente,
                      ArrayList<DetalleFactura> listaDetallesFactura, Tienda ownedByTienda) {




    public double calcularTotal() {
        double total = 0;

        for (DetalleFactura detalle : listaDetallesFactura) {
            total += detalle.getSubTotal();
        }

        return total;
    }

}