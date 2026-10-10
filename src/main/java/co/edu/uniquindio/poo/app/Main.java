package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.Cliente;
import co.edu.uniquindio.poo.model.Categoria;
import co.edu.uniquindio.poo.model.Producto;
import co.edu.uniquindio.poo.model.Tienda;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Tienda tienda = new Tienda("TechStore ", "752", "350");
        int opcion = 0;
        do {

            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Bienvenido al sistema de Gestion de ventas de TechStore" + "\nSeleccione la opcion que desea ejecutar" + "\n1.registrar Cliente" + "\n2.Buscar Cliente" + "\n3.Eliminar Cliente" + "\n4.Registar Producto" + "\n5.Buscar Producto" + "\n6.Eliminar Producto" + "\n7.Crear Factura"));
            switch (opcion) {
                case 1:
                    crearCliente(tienda);
                    break;
                case 2:
                    buscarCliente(tienda);
                    break;
                case 3:
                    eliminarCliente(tienda);
                    break;
                case 4:
                    crearProducto(tienda);
                    break;
                case 5:
                    buscarProducto(tienda);
                    break;
                case 6:
                    eliminarProducto(tienda);
                    break;
            }

        } while (opcion != 7);

    }

    private static void crearCliente(Tienda tienda) {
        String nombreCompleto = JOptionPane.showInputDialog(null, "ingrese el nombre del cliente");
        String documentoIdentidad = JOptionPane.showInputDialog(null, "digite el documento de identidad del cliente");
        String telefono = JOptionPane.showInputDialog(null, "ingrese el telefono del cliente");
        String correo = JOptionPane.showInputDialog(null, "ingrese el correo electronico del cliente");
        String ciudadResidencia = JOptionPane.showInputDialog(null, "ingrese la ciudad de residendencia  del cliente");
        Cliente cliente = new Cliente(documentoIdentidad, nombreCompleto,
                tienda, telefono,
                correo, ciudadResidencia);
        if (tienda.crearCliente(cliente)) {
            JOptionPane.showMessageDialog(null, "Cliente registrado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "No es posible registar este cliente, ya hay otro con los mismos datos,");
        }
    }

    private static void eliminarCliente(Tienda tienda) {
        String documentoIdentidad = JOptionPane.showInputDialog(null, "digite el documento de identidad del cliente");
        tienda.eliminarCliente(documentoIdentidad);

    }

    private static void buscarCliente(Tienda tienda) {
        String documentoIdentidad = JOptionPane.showInputDialog(null, "digite el documento de identidad del cliente");
        tienda.eliminarCliente(documentoIdentidad);
    }

    private static void crearProducto(Tienda tienda) {
        String nombre = JOptionPane.showInputDialog(null, "ingrese el nombre del producto");
        String codigo = JOptionPane.showInputDialog(null, "digite el codigo a asignar al producto");
        String descripcion = JOptionPane.showInputDialog(null, "ingrese la descipcion del producto");
        int cantidadDisponible = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese el inventario"));
        double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "ingrese el valor del prodcuto "));
        Categoria[] categorias = Categoria.values();
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione la categoría:\n"
                + "1. " + categorias[0] + "\n"
                + "2. " + categorias[1] + "\n"
                + "3. " + categorias[2]));
        Categoria categoria = categorias[(opcion) - 1];
        Producto producto = new Producto(nombre, codigo, descripcion, cantidadDisponible, valor, categoria, tienda);
        tienda.crearProducto(producto);

    }

    private static void buscarProducto(Tienda tienda) {
        String codigo = JOptionPane.showInputDialog(null, "digite el codigo a asignar al producto");
        tienda.buscarProducto(codigo);

    }

    private static void eliminarProducto(Tienda tienda) {
        String codigo = JOptionPane.showInputDialog(null, "digite el codigo a asignar al producto");
        tienda.eliminarProducto(codigo);


    }
    private static void crearFactura(Tienda tienda){

    }
}


