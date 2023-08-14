package es.udc.paproject.backend.rest.dtos;

public class PeliculaDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private int duration;

    public PeliculaDto(){}

    public PeliculaDto(Long id, String titulo, String descripcion, int duracion){
        this.id=id;
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.duration=duracion;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public int getDuration() {return duration;}

    public void setDuration(int duration) {this.duration = duration;}
}
