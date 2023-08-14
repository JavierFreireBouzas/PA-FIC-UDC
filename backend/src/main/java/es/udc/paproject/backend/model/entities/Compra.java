package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Compra {

    private Long id;
    private User user;
    private Sesion sesion;
    private LocalDateTime fecha;
    private int numLocalidades;
    private String tarjetaBancaria;
    private boolean entregadas;

    public Compra() {}

    public Compra(User user, Sesion sesion, LocalDateTime fecha, int numLocalidades, String tarjetaBancaria, boolean entregadas) {
        this.user = user;
        this.sesion = sesion;
        this.fecha = fecha;
        this.numLocalidades = numLocalidades;
        this.tarjetaBancaria = tarjetaBancaria;
        this.entregadas = entregadas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sesionId")
    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getNumLocalidades() {
        return numLocalidades;
    }

    public void setNumLocalidades(int numLocalidades) {
        this.numLocalidades = numLocalidades;
    }
    @Transient
    public void addLocalidad(){setNumLocalidades(this.numLocalidades+1);}
    @Transient
    public void quitarLocalidad(){
        setNumLocalidades(this.numLocalidades-1);
    }
    @Transient
    public int getPreciototal(){ return sesion.getPrecio().intValue() * numLocalidades; }

    public String getTarjetaBancaria() {
        return tarjetaBancaria;
    }

    public void setTarjetaBancaria(String tarjetaBancaria) {
        this.tarjetaBancaria = tarjetaBancaria;
    }

    public boolean isEntregadas() {
        return entregadas;
    }

    public void setEntregadas(boolean entregadas) {
        this.entregadas = entregadas;
    }

    @Transient
    public BigDecimal getPrecioTotal(){

        BigDecimal numLocalidades = new BigDecimal(this.numLocalidades);
        return sesion.getPrecio().multiply(numLocalidades);

    }
}
