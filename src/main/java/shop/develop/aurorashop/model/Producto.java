package shop.develop.aurorashop.model;

public class Producto {

    protected String idProducto;
    private String title;
    private String descripcion;
    private int stock;
    private double value;

    public Producto(String idProducto, String title, String descripcion, int stock,  double value) {
        this.idProducto = idProducto;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
