package es.udc.paproject.backend.model.entities;

import java.util.List;
import java.util.Objects;

public class BillboardItem {

    private Pelicula pelicula;
    private List<Sesion> sesions;

    public BillboardItem() {}

    public BillboardItem(Pelicula pelicula, List<Sesion> sesions) {
        this.pelicula = pelicula;
        this.sesions = sesions;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public List<Sesion> getSesions() {
        return sesions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BillboardItem that)) return false;
        return getPelicula().equals(that.getPelicula()) && Objects.equals(getSesions(), that.getSesions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPelicula(), getSesions());
    }
}
