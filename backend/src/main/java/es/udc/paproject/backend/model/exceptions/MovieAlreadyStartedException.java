package es.udc.paproject.backend.model.exceptions;

public class MovieAlreadyStartedException extends Exception {

    private Long movieId;
    private Long sesionId;

    public MovieAlreadyStartedException(String message) {
        super(message);
    }

    public MovieAlreadyStartedException(Long movieId, Long sesionId) {
        this.movieId = movieId;
        this.sesionId = sesionId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getSesionId() {
        return sesionId;
    }
}
