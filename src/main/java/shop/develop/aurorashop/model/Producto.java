package shop.develop.aurorashop.model;

public class Producto {

    protected String idProducto;
    private String nombreProducto;
    private String descripcion;
    private int stock;
    private double value;

    public Producto(String idProducto, String nombreProducto, String descripcion, int stock,  double value) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.value = value;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }


    public void listProducts(){

    }

}
