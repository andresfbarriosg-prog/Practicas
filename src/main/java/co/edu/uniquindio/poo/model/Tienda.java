package co.edu.uniquindio.poo.model;

import java.util.*;

public class Tienda {

    private final String nombre;
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final List<Factura> listaFacturas = new LinkedList<>();
    private Map<String, Producto> listaProductos = new HashMap<>();


    public Tienda(String nombre, String nit, String telefono) {
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;

    }

    // setters y getters

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean crearCliente(Cliente cliente) {

        if (buscarCliente(cliente.getDocumentoIdentidad()).isPresent()) {
            return false;
        }

        listaClientes.add(cliente);
        return true;
    }

    // BUSCAR
    public Optional<Cliente> buscarCliente(String documentoIdentidad) {
        return listaClientes.stream()
                .filter(aux -> aux.getDocumentoIdentidad().equals(documentoIdentidad))
                .findFirst(); // Retorna automáticamente un Optional con el producto o vacío
    }

    public boolean eliminarCliente(String documentoIdentidad) {

        Optional<Cliente> clienteEncontrado = buscarCliente(documentoIdentidad);

        if (clienteEncontrado.isPresent()) {
            listaClientes.remove(clienteEncontrado.get());
            return true;
        }

        return false;

    }
    //CRUD PRODUCTO


    // CREAR
    public boolean crearProducto(Producto producto) {

        if (buscarProducto(producto.getCodigo()).isPresent()) {
            return false;
        }

        listaProductos.put(producto.getCodigo(), producto);
        return true;
    }

    //BUSCAR
    public Optional<Producto> buscarProducto(String codigo) {
        return Optional.ofNullable(listaProductos.get(codigo));
    }

    //ACTUALIZAR
    public boolean eliminarProducto(String codigo) {

        if (buscarProducto(codigo).isPresent()) {
            listaProductos.remove(codigo);
            return true;
        }

        return false;
    }

    public boolean crearFactura(Factura factura) {
        listaFacturas.add(factura);
        return true;
    }

    // 1
    public List<Producto> obtnerCantidadDisponible(){
        return listaProductos.values().stream().filter(producto-> producto.getCantidadDisponible()>=10).toList();
    }
    public List<String> obtenerCodigoYStock(){
        return listaProductos.values().stream().filter(producto-> producto.getCantidadDisponible()>=10 && producto.getCantidadDisponible()<50)
                .map(producto->producto.getCodigo()).toList();
    }



}

