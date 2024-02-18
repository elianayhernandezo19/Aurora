package shop.develop.aurorashop.model;

public class Cliente {

    protected String id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public Cliente(String id, String nombre, String apellido, String direccion, String telefono){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getId() {return id;}

    public String getNombre() {return nombre;}

    public String getApellido() {return apellido;}

    public String getDireccion() {return direccion;}

    public String getTelefono() {return telefono;}

    public void listCliente(){

    }
}
