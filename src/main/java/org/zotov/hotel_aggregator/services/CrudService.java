package org.zotov.hotel_aggregator.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.zotov.hotel_aggregator.exceptions.service.ModelNotFoundException;
import org.zotov.hotel_aggregator.interfaces.services.ModelMapperService;
import org.zotov.hotel_aggregator.interfaces.services.BaseService;

public abstract class CrudService<ResponseDTO, RequestDTO, Model, Repo extends CrudRepository<Model, Long>> implements BaseService<ResponseDTO, RequestDTO> {

    protected final Repo repository;
    protected final ModelMapperService<RequestDTO, ResponseDTO, Model> modelMapper;
    protected final String MODEL_NAME; // Used for exception messages

    public CrudService(Repo repository, ModelMapperService<RequestDTO, ResponseDTO, Model> modelMapper, String MODEL_NAME) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.MODEL_NAME = MODEL_NAME;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseDTO save(RequestDTO requestModel) {
        Model modelToSave = modelMapper.RequestDTOtoModel(requestModel);
        Model savedModel = this.repository.save(modelToSave);

        return modelMapper.modelToResponseDTO(savedModel);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseDTO findById(Long id) {
        Model foundModel = this.repository.findById(id).orElseThrow(() -> new ModelNotFoundException(MODEL_NAME + "not found, id = " + id));

        return this.modelMapper.modelToResponseDTO(foundModel);
    }

    @Override
    public abstract void update(Long id, RequestDTO model);

    @Override
    public Long count(){
        return this.repository.count();
    }
}
