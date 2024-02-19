package shop.develop.aurorashop.model;

import java.time.LocalDate;
import java.util.Objects;

public class Enlatado extends Producto {

    private LocalDate fechaEnvasado;
    private double peso;
    private Pais pais;

    public Enlatado (String idProducto, String nombreProducto, String descripcion, int stock, double value, LocalDate fechaEnvasado, double peso, Pais pais) {
        super(idProducto, nombreProducto, descripcion, stock, value);
        this.fechaEnvasado = fechaEnvasado;
        this.peso = peso;
        this.pais = pais;
    }

    public LocalDate getFechaEnvasado() {
        return fechaEnvasado;
    }

    public void setFechaEnvasado(LocalDate fechaEnvasado) {
        this.fechaEnvasado = fechaEnvasado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enlatado)) return false;
        Enlatado enlatado = (Enlatado) o;
        return idProducto.equals(enlatado.idProducto);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

}
