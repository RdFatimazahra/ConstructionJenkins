package com.tacheservice.mapper;


import com.tacheservice.dto.TacheDto;
import com.tacheservice.model.Tache;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TacheMapper {
    TacheDto tacheToTacheDto(Tache tache);
    Tache tacheDtoToTache(TacheDto tacheDto);

}
