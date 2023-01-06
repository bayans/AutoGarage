package com.example.autogarage.service;

import com.example.autogarage.dto.ReceiptDto;
import com.example.autogarage.dto.RepairDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.Receipt;
import com.example.autogarage.model.Repair;
import com.example.autogarage.repsitory.ReceiptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceiptServiceTest {
    @InjectMocks
    private ReceiptService receiptService;

    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private ModelMapper modelMapper = new ModelMapper();


    Receipt receipt = new Receipt();

    Receipt receipt2 = new Receipt();

    ReceiptDto receiptDto = new ReceiptDto();

    ReceiptDto receiptDto2 = new ReceiptDto();

    List<Receipt> receipts = new ArrayList<>();

    List<ReceiptDto> receiptDtos = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        receipt.setTotal(50.0);
        receipt.setRepair(new Repair());
        receipt.setIsPaid(false);

        receipt2.setTotal(100.0);
        receipt2.setRepair(new Repair());
        receipt2.setIsPaid(true);

        receiptDto.setTotal(50.0);
        receiptDto.setRepair(new RepairDto());
        receiptDto.setIsPaid(false);

        receiptDto2.setTotal(100.0);
        receiptDto2.setRepair(new RepairDto());
        receiptDto2.setIsPaid(true);

        receiptDtos.add(receiptDto);
        receiptDtos.add(receiptDto2);
    }
    @Test
    void createReceipt() {

        lenient().when(receiptRepository.save(receipt)).thenReturn(receipt);
        ReceiptDto dto = modelMapper.map(receipt, ReceiptDto.class);

        Receipt newReceipt = modelMapper.map(receiptService.createReceipt(dto), Receipt.class);
        ReceiptDto newDto = modelMapper.map(newReceipt, ReceiptDto.class);

        assertThat(newDto).isEqualTo(dto);


    }

    @Test
    public void getReceiptByIdException() {
        assertThrows(RecordNotFoundException.class, ()
                -> receiptService.updateReceiptById(new ReceiptDto(),new ReceiptDto().getId()));
    }


    @Test
    public void getAllReceipts() {

        lenient().when(receiptRepository.findAll()).thenReturn(receipts);
        lenient().when(receiptRepository.count()).thenReturn(1L);
        List<ReceiptDto> foundReceipts = receiptService.getAllReceipts();
        verify(receiptRepository, times(1)).findAll();
        assertThat(foundReceipts).isEqualTo(receipts);

    }

    @Test
    public void updateReceiptById() {

        lenient().when(receiptRepository.findById(receipt.getId())).thenReturn(Optional.of(receipt));
        lenient().when(receiptRepository.save(receipt)).thenReturn(receipt);

        receiptService.updateReceiptById(receiptDto, receipt2.getId());

        verify(receiptRepository, Mockito.times(1)).findById(receipt.getId());
        verify(receiptRepository, Mockito.times(1)).save(receipt);
    }

}