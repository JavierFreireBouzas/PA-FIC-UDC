package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.BillboardItem;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BillboardItemConversor {

    public BillboardItemConversor() {}

    public static List<BillboardItemDto> toBillboardItemDtos(List<BillboardItem> items) {
        List<BillboardItemDto> itemsDto = new ArrayList<>();

        for (BillboardItem item : items) {
            itemsDto.add(toBillboardItemDto(item));
        }

        return itemsDto;
    }

    private static BillboardItemDto toBillboardItemDto(BillboardItem item) {
        List<SesionSummaryDto> sesionsDto;

        sesionsDto = item.getSesions().stream().map(sesion ->
                new SesionSummaryDto(sesion.getId(), toMillis(sesion.getFecha()))).collect(Collectors.toList());

        return new BillboardItemDto(item.getPelicula().getId(), item.getPelicula().getTitulo(), sesionsDto);
    }

    private static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

}
