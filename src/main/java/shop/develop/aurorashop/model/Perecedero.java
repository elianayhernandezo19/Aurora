package shop.develop.aurorashop.model;

import java.time.LocalDate;
import java.util.Objects;

public class Perecedero extends Producto {

    private LocalDate fechaVencimiento;

    public Perecedero (String idProducto, String nombreProducto, String descripcion, double valor, int stock, LocalDate fechaVencimiento) {
        super(idProducto, nombreProducto, descripcion, valor, stock);
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Perecederos)) return false;
        Perecedero perecedero = (Perecedero) o;
        return idProducto.equals(perecedero.idProducto);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }
}
