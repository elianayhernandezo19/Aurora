package shop.develop.aurorashop.model;

import java.util.Objects;

public class Juridica extends Cliente {

    public Juridica(String id, String nombre, String apellido, String direccion, String telefono){
        super(id, nombre, apellido, direccion, telefono);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Juridica)) return false;
        Juridica juridica = (Juridica) o;
        return id.equals(juridica.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
