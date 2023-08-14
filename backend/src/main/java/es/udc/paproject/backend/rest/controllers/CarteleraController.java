package es.udc.paproject.backend.rest.controllers;

import static es.udc.paproject.backend.rest.dtos.BillboardItemConversor.*;
import static es.udc.paproject.backend.rest.dtos.PeliculaConversor.toPeliculaDto;
import static es.udc.paproject.backend.rest.dtos.SesionConversor.toSesionDto;

import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.paproject.backend.model.exceptions.InvalidSearchDateException;
import es.udc.paproject.backend.model.exceptions.MovieAlreadyStartedException;
import es.udc.paproject.backend.model.services.CarteleraService;
import es.udc.paproject.backend.rest.dtos.BillboardItemDto;
import es.udc.paproject.backend.rest.dtos.PeliculaDto;
import es.udc.paproject.backend.rest.dtos.SesionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import es.udc.paproject.backend.rest.common.ErrorsDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/cartelera")
public class CarteleraController {

    private final static String INVALID_SEARCH_DATE_EXCEPTION_CODE = "project.exceptions.InvalidSearchDateException";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(InvalidSearchDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorsDto handleInvalidSearchExceptionDate(InvalidSearchDateException exception, Locale locale){
        String errorMessage = messageSource.getMessage(INVALID_SEARCH_DATE_EXCEPTION_CODE, null, INVALID_SEARCH_DATE_EXCEPTION_CODE, locale);
        return new ErrorsDto(errorMessage);
    }

    @Autowired
    private CarteleraService carteleraService;

    @GetMapping("/peliculas/{peliculaId}")
    public PeliculaDto buscarPelicula(@PathVariable Long peliculaId)
        throws InstanceNotFoundException{
        return toPeliculaDto(carteleraService.buscarPeliculaPorId(peliculaId));
    }

    @GetMapping("/sesiones/{sesionId}")
    public SesionDto buscarSesion(@PathVariable Long sesionId) throws InstanceNotFoundException, MovieAlreadyStartedException {
        return toSesionDto(carteleraService.buscarSesionPorId(sesionId));
    }

    @GetMapping("/catalog")
    public List<BillboardItemDto> buscarCartelera(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
            throws InvalidSearchDateException {

        return toBillboardItemDtos(carteleraService.buscarSesionesPorDia(date));
    }

}
