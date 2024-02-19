package shop.develop.aurorashop.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Venta {

    private String idVenta;
    private LocalDate date;
    private Double total;
    private double tax;
    protected Cliente comprador;
    private ArrayList<DetalleVenta> detalleVenta;

    public Venta(String idVenta, LocalDate date, Double total, double tax,Cliente comprador,ArrayList<DetalleVenta> detalleVenta ) {
        this.idVenta = idVenta;
        this.date = date;
        this.total = total;
        this.tax = tax;
        this.comprador = comprador;
        this.detalleVenta = detalleVenta;
    }

    public String getIdVenta() {return idVenta;}
    public LocalDate getDate() {return date;}
    public Double getTotal() {return total;}
    public double getTax() {return tax;}
    public Cliente getComprador() {return comprador;}
    public ArrayList<DetalleVenta> getDetalleVenta() {
        return detalleVenta;
    }
    public void RegisterVenta() {
    }

    public String searchVenta() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta)) return false;
        Venta venta = (Venta) o;
        return idVenta.equals(venta.idVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenta);
    }
}


