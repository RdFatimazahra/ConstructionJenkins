package com.tacheservice.service;

import com.tacheservice.Client.RessourcesClient;
import com.tacheservice.ResourceNotFoundException;
import com.tacheservice.dto.TacheDto;
import com.tacheservice.mapper.TacheMapper;
import com.tacheservice.model.FullTachesResponse;
import com.tacheservice.model.Ressources;
import com.tacheservice.model.Tache;
import com.tacheservice.repository.TacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TacheServiceImplTest {

    @Mock
    private TacheRepository tacheRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TacheMapper tacheMapper;

    @Mock
    private RessourcesClient ressourcesClient;

    @InjectMocks
    private TacheServiceImpl tacheService;

    private Tache tache;
    private TacheDto tacheDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup mock data
        tache = new Tache();
        tache.setIdTache(1);
        tache.setDescription("Test Tache");

        tacheDto = new TacheDto();
        tacheDto.setDescription("Test Tache DTO");
    }

    @Test
    void createTache() {
        when(tacheMapper.tacheDtoToTache(tacheDto)).thenReturn(tache);
        when(tacheRepository.save(tache)).thenReturn(tache);
        when(tacheMapper.tacheToTacheDto(tache)).thenReturn(tacheDto);

        TacheDto result = tacheService.createTache(tacheDto, 1);

        assertEquals(tacheDto, result);
        verify(restTemplate, times(1)).getForObject("http://localhost:8090/api/projets/1", Object.class);
        verify(tacheRepository, times(1)).save(tache);
    }

    @Test
    void getAllTaches() {
        when(tacheRepository.findAll()).thenReturn(Arrays.asList(tache));
        when(tacheMapper.tacheToTacheDto(tache)).thenReturn(tacheDto);

        List<TacheDto> result = tacheService.getAllTaches();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Tache DTO", result.get(0).getDescription());
    }

    @Test
    void getTacheById() {
        when(tacheRepository.findById(1)).thenReturn(Optional.of(tache));
        when(tacheMapper.tacheToTacheDto(tache)).thenReturn(tacheDto);

        TacheDto result = tacheService.getTacheById(1);

        assertEquals("Test Tache DTO", result.getDescription());
    }

    @Test
    void getTacheById_NotFound() {
        when(tacheRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            tacheService.getTacheById(1);
        });
    }

    @Test
    void updateTache() {
        when(tacheRepository.findById(1)).thenReturn(Optional.of(tache));
        when(tacheMapper.tacheDtoToTache(tacheDto)).thenReturn(tache);
        when(tacheRepository.save(tache)).thenReturn(tache);
        when(tacheMapper.tacheToTacheDto(tache)).thenReturn(tacheDto);

        TacheDto result = tacheService.updateTache(1, tacheDto);

        assertEquals(tacheDto, result);
        verify(tacheRepository, times(1)).save(tache);
    }

    @Test
    void deleteTache() {
        when(tacheRepository.findById(1)).thenReturn(Optional.of(tache));

        tacheService.deleteTache(1);

        verify(tacheRepository, times(1)).delete(tache);
    }

    @Test
    void getTachesByProjet() {
        when(tacheRepository.findByIdProjet(1)).thenReturn(Arrays.asList(tache));
        when(tacheMapper.tacheToTacheDto(tache)).thenReturn(tacheDto);

        List<TacheDto> result = tacheService.getTachesByProjet(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Tache DTO", result.get(0).getDescription());
    }

    @Test
    void tachWithRessources() {
        when(tacheRepository.findById(1)).thenReturn(Optional.of(tache));
        when(ressourcesClient.getRessourcesByTache(1)).thenReturn(Arrays.asList(new Ressources()));

        FullTachesResponse response = tacheService.tachWithRessources(1);

        assertNotNull(response);
        assertEquals("Test Tache", response.getDescription());
    }

    @Test
    void deleteTachesByProjetId() {
        when(tacheRepository.findByIdProjet(1)).thenReturn(Arrays.asList(tache));

        tacheService.deleteTachesByProjetId(1);

        verify(tacheRepository, times(1)).deleteAll(Arrays.asList(tache));
    }
}
