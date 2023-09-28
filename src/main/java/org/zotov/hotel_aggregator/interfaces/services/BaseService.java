package org.zotov.hotel_aggregator.interfaces.services;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.ResponseEntity;

public interface BaseService<ResponseDTO, RequestDTO> {
    ResponseDTO save(RequestDTO model);
    void deleteById(Long id);
    ResponseDTO findById(Long id);
    void update(Long id, RequestDTO model);
    Long count();
}
