package org.zotov.hotel_aggregator.interfaces.services;

public interface ModelMapperService<RequestDTO, ResponseDTO, Model> {
    Model RequestDTOtoModel(RequestDTO requestDTO);

    ResponseDTO modelToResponseDTO(Model model);
}
