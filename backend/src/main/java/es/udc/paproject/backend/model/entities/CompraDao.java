package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

public interface CompraDao extends PagingAndSortingRepository<Compra, Long> {

    Slice<Compra> findByUserIdOrderByFechaDesc(Long id, Pageable pageable);

}