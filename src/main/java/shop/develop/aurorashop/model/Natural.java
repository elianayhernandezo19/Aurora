package shop.develop.aurorashop.model;

import java.time.LocalDate;
import java.util.Objects;

public class Natural extends Cliente {

    private String email;
    private LocalDate fechaNacimiento;

    public Natural(String id, String nombre, String apellido, String direccion, String telefono, String email, LocalDate fechaNacimiento){
        super(id, nombre, apellido, direccion, telefono);
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {return email;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Natural)) return false;
        Natural natural = (Natural) o;
        return id.equals(natural.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
