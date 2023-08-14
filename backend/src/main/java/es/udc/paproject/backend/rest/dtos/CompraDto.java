package es.udc.paproject.backend.rest.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraDto {

    private long fechaCompra;
    private Long idCompra;
    private String tituloPelicula;
    private int numLocalidades;
    private BigDecimal precioTotal;
    private long fechaSesion;
    private boolean entregadas;

    public CompraDto() {
    }

    public CompraDto(long fechaCompra, Long idCompra, String tituloPelicula, int numLocalidades, BigDecimal precioTotal, long fechaSesion, boolean entregadas) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.tituloPelicula = tituloPelicula;
        this.numLocalidades = numLocalidades;
        this.precioTotal = precioTotal;
        this.fechaSesion = fechaSesion;
        this.entregadas = entregadas;
    }

    public long getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(long fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
    }

    public int getNumLocalidades() {
        return numLocalidades;
    }

    public void setNumLocalidades(int numLocalidades) {
        this.numLocalidades = numLocalidades;
    }
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public long getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(long fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public boolean isEntregadas() {
        return entregadas;
    }

    public void setEntregadas(boolean entregadas) {
        this.entregadas = entregadas;
    }
}
