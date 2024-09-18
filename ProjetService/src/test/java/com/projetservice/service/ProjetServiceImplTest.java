package com.projetservice.service;

import com.projetservice.client.TacheClient;
import com.projetservice.dto.projetDto;
import com.projetservice.mapper.ProjetMapper;
import com.projetservice.model.FullProjectResponse;
import com.projetservice.model.Projet;
import com.projetservice.model.Taches;
import com.projetservice.repository.ProjetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjetServiceImplTest {

    @Mock
    private ProjetRepository projetRepository;

    @Mock
    private ProjetMapper projetMapper;

    @Mock
    private TacheClient tacheClient;

    @InjectMocks
    private ProjetServiceImpl projetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProjet() {
        projetDto inputDto = new projetDto();
        Projet projet = new Projet();
        Projet savedProjet = new Projet();
        projetDto outputDto = new projetDto();

        when(projetMapper.projetDtoToProjet(inputDto)).thenReturn(projet);
        when(projetRepository.save(projet)).thenReturn(savedProjet);
        when(projetMapper.projetToProjetDto(savedProjet)).thenReturn(outputDto);

        projetDto result = projetService.createProjet(inputDto);

        assertEquals(outputDto, result);
        verify(projetRepository).save(projet);
    }

    @Test
    void getProjetById() {
        int id = 1;
        Projet projet = new Projet();
        projetDto projetDto = new projetDto();

        when(projetRepository.findById(id)).thenReturn(Optional.of(projet));
        when(projetMapper.projetToProjetDto(projet)).thenReturn(projetDto);

        projetDto result = projetService.getProjetById(id);

        assertEquals(projetDto, result);
    }

    @Test
    void getAllProjets() {
        List<Projet> projets = Arrays.asList(new Projet(), new Projet());
        List<projetDto> projetDtos = Arrays.asList(new projetDto(), new projetDto());

        when(projetRepository.findAll()).thenReturn(projets);
        when(projetMapper.projetToProjetDto(any(Projet.class))).thenReturn(projetDtos.get(0), projetDtos.get(1));

        List<projetDto> result = projetService.getAllProjets();

        assertEquals(projetDtos.size(), result.size());
        verify(projetRepository).findAll();
    }

    @Test
    void updateProjet() {
        int id = 1;
        projetDto inputDto = new projetDto();
        Projet existingProjet = new Projet();
        Projet updatedProjet = new Projet();
        projetDto outputDto = new projetDto();

        when(projetRepository.findById(id)).thenReturn(Optional.of(existingProjet));
        when(projetMapper.projetDtoToProjet(inputDto)).thenReturn(updatedProjet);
        when(projetRepository.save(updatedProjet)).thenReturn(updatedProjet);
        when(projetMapper.projetToProjetDto(updatedProjet)).thenReturn(outputDto);

        projetDto result = projetService.updateProjet(id, inputDto);

        assertEquals(outputDto, result);
        verify(projetRepository).save(updatedProjet);
    }

    @Test
    void deleteProjet() {
        int id = 1;

        projetService.deleteProjet(id);

        verify(tacheClient).deleteTachesByProjetId(id);
        verify(projetRepository).deleteById(id);
    }

    @Test
    void projetWithTaches() throws Exception {
        int id = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse("2023-01-01");
        Date endDate = dateFormat.parse("2023-12-31");

        Projet projet = Projet.builder()
                .nomProjet("Test Project")
                .dateDebut(startDate)
                .dateFin(endDate)
                .description("Test Description")
                .budget(1000.0)
                .build();
        List<Taches> taches = Arrays.asList(new Taches(), new Taches());

        when(projetRepository.findById(id)).thenReturn(Optional.of(projet));
        when(tacheClient.findAllTachesByProjet(id)).thenReturn(taches);

        FullProjectResponse result = projetService.projetWithTaches(id);

        assertEquals(projet.getNomProjet(), result.getNomProjet());
        assertEquals(projet.getDateDebut(), result.getDateDebut());
        assertEquals(projet.getDateFin(), result.getDateFin());
        assertEquals(projet.getDescription(), result.getDescription());
        assertEquals(projet.getBudget(), result.getBudget());
        assertEquals(taches, result.getTaches());
    }
}