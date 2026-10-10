package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
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
    public List<Producto> obtnerCantidadDisponible() {
        return listaProductos.values().stream().filter(producto -> producto.getCantidadDisponible() >= 10).toList();
        //2
    }

    public List<String> obtenerCodigoYStock() {
        return listaProductos.values().stream().filter(producto -> producto.getCantidadDisponible() >= 10 && producto.getCantidadDisponible() < 50)
                .map(producto -> producto.getCodigo()).toList();
    }

    //3
    public ArrayList<Cliente> obtenerListaClientesPorFecha(LocalDate fechaConsulta) {
        ArrayList<Cliente>  listaClentes= new ArrayList<>();
        for(Cliente aux: listaClientes){
            if(aux.isCompraEnFecha(fechaConsulta)==true){
                listaClentes.add(aux);
            }
        }
       return listaClentes;

    }
    //4

    public List<Factura> obtenerFacturasClientesR(){
        char letraInicial = 'r';
        List<Factura> listaFacturasResultado = new ArrayList<>();
        for(Factura facturaAux: listaFacturas){
            String cliente = String.valueOf((facturaAux.cliente()));
            if(cliente.charAt(0)==(letraInicial)){
                listaFacturasResultado.add(facturaAux);
            }
        }
        return listaFacturasResultado;
    }


    //punto 5:   Obtener las facturas donde se haya comprado un celular de marca Iphone 16 pro max
    public List<Factura> obtenerFacturasIphone(){
        return listaFacturas.stream().filter(factura -> factura.listaDetallesFactura().stream().anyMatch(detalleFactura -> detalleFactura.getProducto().getNombre().equals("Iphone 16 pro max"))).toList();
    }

    //punto 6:   Obtener las facturas que tenga un cliente
    // donde su nombre sea juan y haya comprado un celular de marca Iphone 16 pro max
    public List <Factura> obtenerFacturasJuanIphone(){
        return listaFacturas.stream().filter(factura -> factura.cliente().getNombreCompleto().equalsIgnoreCase("Juan")&& factura.listaDetallesFactura().stream().anyMatch(detalleFactura -> detalleFactura.getProducto().getNombre().equals("Iphone 16 pro max"))).toList();
        // anyMatch() cuando solo necesites una respuesta de SÍ o NO (boolean)

    }


    // Punto 7: Implementar un método que reciba una categoría
    //  y retorne todos los productos registrados que pertenezcan a ella.
    public List<Producto> obtenerProductosPorCategoria(Categoria categoriaBuscar){
        return listaProductos.values().stream().filter(producto -> producto.getCategoria().equals(categoriaBuscar)).toList();
        // RECORDAME en un hash map es diferente,
    }

    //punto 8 : Implementar un método que reciba un precio mínimo y un precio máximo, y retorne
    // los productos cuyo precio se encuentre dentro de ese rango, incluyendo ambos límites.
    public List <Producto> obtenerProductosRangoPrecio(double precioMinmo, double precioMaximo){
        return listaProductos.values().stream().filter(producto -> producto.getValor()>=precioMinmo&& producto.getValor()<=precioMaximo).toList();

    }

    //punto 9: Implementar un método que retorne todos los productos
    // registrados en la tienda, ordenados de menor a mayor según su precio.
    public
    //punto 10: Implementar un método que identifique el producto con el precio más alto de la tienda.
    // Si no existen productos registrados, el método debe retornar un Optional vacío.

    //Punto 11: Implementar un método que reciba el nombre de una ciudad y retorne
    // todos los clientes que residan en ella.
    // La búsqueda debe realizarse sin diferenciar entre mayúsculas y minúsculas.
}

