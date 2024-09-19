package com.ressourceservice.service;

import com.ressourceservice.Dto.RessourceDto;
import com.ressourceservice.mapper.RessourceMapper;
import com.ressourceservice.model.ressources;
import com.ressourceservice.repository.RessourceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class RessourceServiceImplTest {

    @InjectMocks
    private RessourceServiceImpl ressourceService;

    @Mock
    private RessourceRepository ressourceRepository;

    @Mock
    private RessourceMapper ressourceMapper;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRessource() {
        RessourceDto ressourceDto = new RessourceDto();
        ressources ressource = new ressources();
        when(restTemplate.getForObject(anyString(), eq(Object.class))).thenReturn(new Object());
        when(ressourceMapper.ressourceDtoToRessource(ressourceDto)).thenReturn(ressource);
        when(ressourceRepository.save(ressource)).thenReturn(ressource);
        when(ressourceMapper.ressourceToRessourceDto(ressource)).thenReturn(ressourceDto);

        RessourceDto createdRessource = ressourceService.createRessource(ressourceDto, 1);

        assertNotNull(createdRessource);
        verify(ressourceRepository).save(ressource);
    }

    @Test
    void getRessourceById() {
        ressources ressource = new ressources();
        when(ressourceRepository.findById(1)).thenReturn(Optional.of(ressource));
        when(ressourceMapper.ressourceToRessourceDto(ressource)).thenReturn(new RessourceDto());

        RessourceDto foundRessource = ressourceService.getRessourceById(1);

        assertNotNull(foundRessource);
    }

    @Test
    void getAllRessources() {
        ressources ressource1 = new ressources();
        ressources ressource2 = new ressources();
        List<ressources> ressourcesList = Arrays.asList(ressource1, ressource2);
        when(ressourceRepository.findAll()).thenReturn(ressourcesList);
        when(ressourceMapper.ressourceToRessourceDto(any(ressources.class))).thenReturn(new RessourceDto());

        List<RessourceDto> result = ressourceService.getAllRessources();

        assertEquals(2, result.size());
    }

    @Test
    void getRessourcesByTacheId() {
        ressources ressource = new ressources();
        List<ressources> ressourcesList = Arrays.asList(ressource);
        when(ressourceRepository.findByIdTache(1)).thenReturn(ressourcesList);
        when(ressourceMapper.ressourceToRessourceDto(ressource)).thenReturn(new RessourceDto());

        List<RessourceDto> result = ressourceService.getRessourcesByTacheId(1);

        assertEquals(1, result.size());
    }

    @Test
    void updateRessource() {
        RessourceDto ressourceDto = new RessourceDto();
        ressources ressource = new ressources();
        when(ressourceRepository.findById(1)).thenReturn(Optional.of(ressource));
        when(ressourceMapper.ressourceToRessourceDto(ressource)).thenReturn(ressourceDto);
        when(ressourceRepository.save(ressource)).thenReturn(ressource);

        RessourceDto updatedRessource = ressourceService.updateRessource(1, ressourceDto);

        assertNotNull(updatedRessource);
        verify(ressourceRepository).save(ressource);
    }

    @Test
    void deleteRessource() {
        ressources ressource = new ressources();
        when(ressourceRepository.findById(1)).thenReturn(Optional.of(ressource));

        ressourceService.deleteRessource(1);

        verify(ressourceRepository).delete(ressource);
    }
}
