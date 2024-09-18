package com.projetservice.mapper;

import com.projetservice.dto.projetDto;
import com.projetservice.model.Projet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjetMapper {

    projetDto projetToProjetDto(Projet projet);
    Projet projetDtoToProjet(projetDto projetDto);

}
