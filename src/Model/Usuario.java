
package Model;


public class Usuario {
    private int Id;
    private String Rut;
    private String Nombre;
    private String Cargo;
    private String Area;
    private String Clave;
    private String Correo;
    
    public int getId() {
        return Id;
    }
    
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public String getClave() {
        return Clave;
    }

    //Modificaion EH
    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
      
}

