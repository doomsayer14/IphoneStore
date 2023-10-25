package com.example.iphoneshop.mapper;

import com.example.iphoneshop.dto.IphoneDTO;
import com.example.iphoneshop.entity.Iphone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class IphoneMapperTest {

    @InjectMocks
    private IphoneMapper iphoneMapper;

    @Mock
    private Iphone iphone;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
        iphoneMapper = new IphoneMapper();
    }

    @Test
    void testIphoneToIphoneDto() {
        when(iphone.getId()).thenReturn(1L);
        when(iphone.getModelName()).thenReturn("iPhone X");
        when(iphone.getPrice()).thenReturn(999.99);

        IphoneDTO iphoneDTO = iphoneMapper.iphoneToIphoneDto(iphone);

        assertEquals(1L, iphoneDTO.getId());
        assertEquals("iPhone X", iphoneDTO.getModelName());
        assertEquals(999.99, iphoneDTO.getPrice());
    }

    @Test
    void testIphoneToIphoneDtoWithNullEntity() {
        // Act: Perform the mapping with a null Iphone entity
        IphoneDTO iphoneDTO = iphoneMapper.iphoneToIphoneDto(null);

        // Assert: Verify that the result is null
        assertNull(iphoneDTO);
    }
}