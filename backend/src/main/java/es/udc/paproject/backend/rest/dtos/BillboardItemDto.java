package es.udc.paproject.backend.rest.dtos;

import java.util.List;

public class BillboardItemDto {

    private Long peliculaId;
    private String titulo;
    private List<SesionSummaryDto> sesions;

    public BillboardItemDto(Long peliculaId, String titulo, List<SesionSummaryDto> sesions) {
        this.peliculaId = peliculaId;
        this.titulo = titulo;
        this.sesions = sesions;
    }

    public Long getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Long peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<SesionSummaryDto> getSesions() {
        return sesions;
    }

    public void setSesions(List<SesionSummaryDto> sesions) {
        this.sesions = sesions;
    }
}
