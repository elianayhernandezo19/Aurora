package shop.develop.aurorashop.model;

import java.util.Objects;

public class Refrigerado extends Producto {

    private String codigoAprovacion;
    private double temperatura;

    public Refrigerado (String idProducto, String nombreProducto, String descripcion, int stock, double value, String codigoAprovacion, double temperatura) {
        super(idProducto, nombreProducto, descripcion, stock, value);
        this.codigoAprovacion = codigoAprovacion;
        this.temperatura = temperatura;
    }

    public String getCodigoAprovacion() {
        return codigoAprovacion;
    }

    public void setCodigoAprovacion(String codigoAprovacion) {
        this.codigoAprovacion = codigoAprovacion;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refrigerado)) return false;
        Refrigerado refrigerados = (Refrigerado) o;
        return idProducto.equals(refrigerados.idProducto);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }


}
