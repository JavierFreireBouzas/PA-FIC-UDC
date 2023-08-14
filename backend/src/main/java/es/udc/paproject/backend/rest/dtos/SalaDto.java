package es.udc.paproject.backend.rest.dtos;

public class SalaDto {

    private Long id;
    private String nombreSala;
    private int localidadesLibres;

    public SalaDto(){}

    public SalaDto(Long id, String nombreSala, int localidadesLibres){
        this.id=id;
        this.nombreSala=nombreSala;
        this.localidadesLibres=localidadesLibres;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNombreSala() {return nombreSala;}

    public void setNombreSala(String nombreSala) {this.nombreSala = nombreSala;}

    public int getLocalidadesLibres() {return localidadesLibres;}

    public void setLocalidadesLibres(int localidadesLibres) {this.localidadesLibres = localidadesLibres;}
}
