package com.example.autogarage.service;

import com.example.autogarage.AutoGarageApplication;
import com.example.autogarage.dto.PartDto;
import com.example.autogarage.dto.StockDto;
import com.example.autogarage.model.Part;
import com.example.autogarage.model.Stock;
import com.example.autogarage.repsitory.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@ContextConfiguration(classes = {AutoGarageApplication.class})
@ExtendWith(MockitoExtension.class)
class PartServiceTest {

    @InjectMocks
    private PartService partService;

    @Mock

    private PartRepository partRepository;

    @Mock
    private ModelMapper modelMapper;

    Part part = new Part();

    Part part2 = new Part();

    PartDto partDto = new PartDto();

    PartDto partDto2 = new PartDto();

    List<Part> parts = new ArrayList<>();

    List<PartDto> partDtos = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        part.setStock(new Stock());
        part.setPartNumber("007");
        part.setPrice(35.0);

        part2.setStock(new Stock());
        part2.setPartNumber("400");
        part2.setPrice(90);

        partDto.setStock(modelMapper.map(new Stock(), StockDto.class));
        partDto.setPartNumber("007");
        partDto.setPrice(35.0);

        partDto2.setStock(modelMapper.map(new Stock(), StockDto.class));
        partDto2.setPartNumber("400");
        partDto2.setPrice(90);
        parts.add(part);
    }

    @Test
    public void createPart() {

        lenient().when(partRepository.save(part)).thenReturn(part);
        PartDto dto = modelMapper.map(part, PartDto.class);

        Part newPart = modelMapper.map(partService.createPart(dto), Part.class);
        PartDto newDto = modelMapper.map(newPart, PartDto.class);
        System.out.println(""+newPart);
        assertThat(newDto).isEqualTo(dto);

    }

    @Test
    public void getAllReceipts() {

        lenient().when(partRepository.findAll()).thenReturn(parts);
        lenient().when(partRepository.count()).thenReturn(1L);
        List<PartDto> foundParts = partService.getAllParts();
        verify(partRepository, times(1)).findAll();

    }

    @Test
    public void deletePart() {

        Optional<Part> partOptional = Optional.of(part);
        lenient().when(partRepository.findById(part.getPartNumber())).thenReturn(partOptional);

        Boolean value = partService.deletePartById(part.getPartNumber());

        assertEquals(value, true);

    }


}
