package com.example.autogarage.service;

import com.example.autogarage.dto.ProcedureDto;
import com.example.autogarage.dto.ReceiptDto;
import com.example.autogarage.model.Procedure;
import com.example.autogarage.model.Receipt;
import com.example.autogarage.repsitory.ReceiptRepository;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@NoArgsConstructor
@Accessors(chain = true)
@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public ReceiptService(ReceiptRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        Receipt newReceipt = repository.save(modelMapper.map(receiptDto, Receipt.class));
        return modelMapper.map(newReceipt, ReceiptDto.class);
    }

}
