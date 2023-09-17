package org.zotov.hotel_aggregator.interfaces;

import java.util.List;

public interface DTOConvertable<Model, DTO> {
    List<DTO> toDTO(List<Model> model);
}
