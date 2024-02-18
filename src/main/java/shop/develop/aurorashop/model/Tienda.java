package shop.develop.aurorashop.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tienda {

    private ArrayList<Cliente> listCliente;
    private ArrayList<Producto> listProducto;

    public Tienda(ArrayList<Cliente> listCliente, ArrayList<Producto> listProducto) {
        this.listCliente = listCliente;
        this.listProducto =listProducto;
    }

    public ArrayList<Cliente> getListCliente() {return listCliente;}

    public ArrayList<Producto> getListProducto() {return listProducto;}


    public boolean createClienteNatural(String id, String nombre, String apellido, String direccion, String telefono, String email, LocalDate fechaNacimiento){
        Natural clienteNatural = new Natural (nombre, apellido, direccion, telefono, id, email, fechaNacimiento);
        if (listCliente.contains(clienteNatural)) {
            return false;
        }
        listCliente.add(clienteNatural);
        return true;
    }

    public boolean createClienteJuridica (String id, String nombre, String apellido, String direccion, String telefono){
        Juridica clienteJuridica = new Juridica(id, nombre, apellido, direccion, telefono);
        if (listCliente.contains(clienteJuridica)) {
            return false;
        }
        listCliente.add(clienteJuridica);
        return true;
    }

    public boolean createProductoRefrigerado (String idProducto, String nombreProducto, String descripcion, double valor, int stock, String codigoAprovacion, double temperatura) {
        Refrigerado productoRefrigerado = new Refrigerado(idProducto, nombreProducto, descripcion, valor, stock, codigoAprovacion, temperatura);
        if (listProducto.contains(productoRefrigerado)){
            return false;
        }
        listProducto.add(productoRefrigerado);
        return true;
    }
    public boolean createProductoEnlatado (String idProducto, String nombreProducto, String descripcion, double valor, int stock, LocalDate fechaEnvasado, double peso, Pais pais){
        Enlatado productoEnlatado = new Enlatado(idProducto, nombreProducto, descripcion, valor, stock, fechaEnvasado, peso, pais);
        if (listProducto.contains(productoEnlatado)) {
            return false;
        }
        listProducto.add(productoEnlatado);
        return true;
    }

    public boolean createProductoPerecedero (String idProducto, String nombreProducto, String descripcion, double valor, int stock, LocalDate fechaVencimiento) {
        Perecedero productoPerecedero = new Perecedero(idProducto, nombreProducto, descripcion, valor, stock, fechaVencimiento);
        if (listProducto.contains(productoPerecedero)){
            return false;
        }
        listProducto.add(productoPerecedero);
        return false;
    }

    public void deleteProducto (String idProducto){
        if (listProducto.contains(idProducto)) {
            int i = listProducto.indexOf(idProducto);
            listProducto.remove(i);
        } else {
            System.out.println("No existe");
        }
    }

    public void deleteCliente (String id){
        if (listCliente.contains(id)) {
            int i = listCliente.indexOf(id);
            listCliente.remove(i);
        } else {
            System.out.println("No existe");
        }
    }

    public boolean updateProductoRefrigerado (String idProducto, String nombreProducto, String descripcion, double valor, int stock, String codigoAprovacion, double temperatura){
        Refrigerado productoRefrigerado = new Refrigerado(idProducto, nombreProducto, descripcion, valor, stock, codigoAprovacion, temperatura);
        if (listProducto.contains(productoRefrigerado)) {
            int i = listProducto.indexOf(productoRefrigerado);
            listProducto.set(i, productoRefrigerado);
            return true;
        }
        return false;
    }

    public boolean updateProductoEnlatado (String idProducto, String nombreProducto, String descripcion, double valor, int stock, LocalDate fechaEnvasado, double peso, Pais pais){
        Enlatado productoEnlatado = new Enlatado(idProducto, nombreProducto, descripcion, valor, stock, fechaEnvasado, peso, pais);
        if (listProducto.contains(productoEnlatado)) {
            int i= listProducto.indexOf(productoEnlatado);
            listProducto.set(i, productoEnlatado);
            return true;
        }
        return false;
    }

    public boolean updateProductoPerecedero (String idProducto, String nombreProducto, String descripcion, double valor, int stock, LocalDate fechaVencimiento) {
        Perecedero productoPerecedero = new Perecedero(idProducto, nombreProducto, descripcion, valor, stock, fechaVencimiento);
        if (listProducto.contains(productoPerecedero)) {
            int i= listProducto.indexOf(productoPerecedero);
            listProducto.set(i, productoPerecedero);
            return true;
        }
        return false;
    }

    public boolean updateClienteNatural(String id, String nombre, String apellido, String direccion, String telefono, String email, LocalDate fechaNacimiento){
        Natural clienteNatural = new Natural (nombre, apellido, direccion, telefono, id, email, fechaNacimiento);
        if (listCliente.contains(clienteNatural)) {
            int i = listCliente.indexOf(clienteNatural);
            listCliente.set(i, clienteNatural);
            return true;
        }
        return false;
    }

    public boolean updateClienteJuridica (String id, String nombre, String apellido, String direccion, String telefono){
        Juridica clienteJuridica = new Juridica(id, nombre, apellido, direccion, telefono);
        if (listCliente.contains(clienteJuridica)) {
            int i = listCliente.indexOf(clienteJuridica);
            listCliente.set(i, clienteJuridica);
            return true;
        }
        return false;
    }

    public Producto searchProducto (String idProducto){
        if (listProducto.contains(idProducto)) {
            int i = listProducto.indexOf(idProducto);
            Producto obj = listProducto.get(i);
            return obj;
        } else {
            return null;
        }
    }
    public Cliente searchCliente (String id){
        if (listCliente.contains(id)) {
            int i = listCliente.indexOf(id);
            Cliente obj = listCliente.get(i);
            return obj;
        } else {
            return null;
        }
    }



}




