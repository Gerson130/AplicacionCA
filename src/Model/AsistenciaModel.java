
package Model;


public class AsistenciaModel {
    
    private int idAsistencia;
    private int idUsuario;
    private String accionUsuario;
    private String fechaAsistencia;
    private String horaAsistencia;

    
    
    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccionUsuario() {
        return accionUsuario;
    }

    public void setAccionUsuario(String accionUsuario) {
        this.accionUsuario = accionUsuario;
    }

    public String getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(String fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getHoraAsistencia() {
        return horaAsistencia;
    }

    public void setHoraAsistencia(String horaAsistencia) {
        this.horaAsistencia = horaAsistencia;
    }
    
    

    
    
}
