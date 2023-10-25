package com.example.iphoneshop.service;

import com.example.iphoneshop.dto.IphoneDTO;
import com.example.iphoneshop.entity.Iphone;
import com.example.iphoneshop.exception.IphoneNotFoundException;
import com.example.iphoneshop.repository.IphoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IphoneServiceTest {

    @InjectMocks
    private IphoneService iphoneService;

    @Mock
    private IphoneRepository iphoneRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        iphoneService = new IphoneService();
    }

    @Test
    void testGetIphoneById() {
        Long id = 1L;
        Iphone iphone = new Iphone();
        iphone.setId(id);
        when(iphoneRepository.findById(id)).thenReturn(Optional.of(iphone));

        Iphone result = iphoneService.getIphoneById(id);

        assertEquals(iphone, result);
    }

    @Test
    void testGetIphoneByIdNotFound() {
        Long id = 1L;
        when(iphoneRepository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(IphoneNotFoundException.class, () -> iphoneService.getIphoneById(id));
    }

    @Test
    void testGetAllIphones() {
        // Arrange
        List<Iphone> iphoneList = List.of(new Iphone(), new Iphone());
        when(iphoneRepository.findAll()).thenReturn(iphoneList);

        // Act
        List<Iphone> result = iphoneService.getAllIphones();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testCreateIphone() {
        // Arrange
        IphoneDTO iphoneDTO = new IphoneDTO();
        iphoneDTO.setId(1L);
        iphoneDTO.setModelName("iPhone X");
        iphoneDTO.setPrice(999.99);
        iphoneDTO.setQuantity(10);

        Iphone iphone = new Iphone();
        when(iphoneRepository.save(any(Iphone.class))).thenReturn(iphone);

        // Act
        Iphone result = iphoneService.createIphone(iphoneDTO);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("iPhone X", result.getModelName());
        assertEquals(999.99, result.getPrice());
        assertEquals(10, result.getQuantity());
    }
}
