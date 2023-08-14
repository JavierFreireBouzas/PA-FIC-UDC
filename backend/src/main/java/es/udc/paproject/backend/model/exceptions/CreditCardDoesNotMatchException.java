package es.udc.paproject.backend.model.exceptions;

public class CreditCardDoesNotMatchException extends Exception {

    private Long compraId;
    private String tarjetaBancaria;

    public CreditCardDoesNotMatchException(String message) {
        super(message);
    }

    public CreditCardDoesNotMatchException(Long compraId, String tarjetaBancaria) {
        this.compraId = compraId;
        this.tarjetaBancaria = tarjetaBancaria;
    }

    public Long getCompraId() {
        return compraId;
    }

    public String getTarjetaBancaria() {
        return tarjetaBancaria;
    }
}
