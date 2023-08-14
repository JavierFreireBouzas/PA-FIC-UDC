package es.udc.paproject.backend.rest.controllers;


import es.udc.paproject.backend.model.entities.Compra;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.ComprasService;
import es.udc.paproject.backend.rest.common.ErrorsDto;
import es.udc.paproject.backend.rest.dtos.BlockDto;
import es.udc.paproject.backend.rest.dtos.CompraDto;
import es.udc.paproject.backend.rest.dtos.CompraParamsDto;
import es.udc.paproject.backend.rest.dtos.EntregaParamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static es.udc.paproject.backend.rest.dtos.CompraConversor.*;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final static String SIN_LOCALIDADES_DISPONIBLES_EXCEPTION_CODE = "project.exceptions.SinLocalidadesDisponiblesException";
    private final static String TICKETS_ALREADY_DELIVERED_EXCEPTION_CODE = "project.exceptions.TicketsAlreadyDelivered";
    private final static String CREDITCARD_DOES_NOT_MATCH_EXCEPTION_CODE = "project.exceptions.CreditCardDoesNotMatchException";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(SinLocalidadesDisponiblesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleSinLocalidadesDisponiblesException(SinLocalidadesDisponiblesException exception, Locale locale){
        String errorMessage = messageSource.getMessage(SIN_LOCALIDADES_DISPONIBLES_EXCEPTION_CODE, null,
                SIN_LOCALIDADES_DISPONIBLES_EXCEPTION_CODE, locale);
        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(TicketsAlreadyDeliveredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleTicketsAlreadyDeliveredException(TicketsAlreadyDeliveredException exception, Locale locale){
        String errorMessage = messageSource.getMessage(TICKETS_ALREADY_DELIVERED_EXCEPTION_CODE, null,
                TICKETS_ALREADY_DELIVERED_EXCEPTION_CODE, locale);
        return new ErrorsDto(errorMessage);
    }

    @ExceptionHandler(CreditCardDoesNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleCreditCardDoesNotMatchException(CreditCardDoesNotMatchException exception, Locale locale){
        String errorMessage = messageSource.getMessage(CREDITCARD_DOES_NOT_MATCH_EXCEPTION_CODE, null,
                CREDITCARD_DOES_NOT_MATCH_EXCEPTION_CODE, locale);
        return new ErrorsDto(errorMessage);
    }


    @Autowired
    private ComprasService comprasService;


    @PostMapping()
    public Long comprar(@RequestAttribute Long userId, @Validated @RequestBody CompraParamsDto params)
            throws InstanceNotFoundException, SinLocalidadesDisponiblesException, MovieAlreadyStartedException{

        return comprasService.comprar(userId, params.getSesionId(), params.getNumLocalidades(), params.getTarjetaBancaria()).getId();
    }

    @GetMapping()
    public BlockDto<CompraDto> encontrarCompras(@RequestAttribute Long userId, @RequestParam(defaultValue="0") int page) {

        Block<Compra> compraBlock = comprasService.encontrarCompras(userId, page, 2);

        return new BlockDto<>(toCompraDtos(compraBlock.getItems()), compraBlock.getExistMoreItems());
    }

    @PostMapping("/{compraId}/entregar-entradas")
    public Long entregarEntradas(@PathVariable Long compraId, @Validated @RequestBody EntregaParamsDto params)
            throws MovieAlreadyStartedException, TicketsAlreadyDeliveredException,
            InstanceNotFoundException, CreditCardDoesNotMatchException {

        return comprasService.entregarEntradas(compraId, params.getTarjetaBancaria());
    }

}
