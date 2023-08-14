package es.udc.paproject.backend.model.exceptions;

public class TicketsAlreadyDeliveredException extends Exception {

    private Long compraId;

    public TicketsAlreadyDeliveredException(String message) {
        super(message);
    }

    public TicketsAlreadyDeliveredException(Long compraId) {
        this.compraId = compraId;
    }

    public Long getCompraId() {
        return compraId;
    }
}
