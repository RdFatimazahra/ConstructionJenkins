package com.ressourceservice.mapper;


import com.ressourceservice.Dto.RessourceDto;
import com.ressourceservice.model.ressources;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RessourceMapper {
    RessourceMapper INSTANCE = Mappers.getMapper(RessourceMapper.class);

    RessourceDto ressourceToRessourceDto(ressources ressource);
    ressources ressourceDtoToRessource(RessourceDto ressourceDto);
}
