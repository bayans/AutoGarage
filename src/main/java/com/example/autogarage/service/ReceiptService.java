package com.example.autogarage.service;

import com.example.autogarage.dto.ReceiptDto;
import com.example.autogarage.exception.RecordNotFoundException;
import com.example.autogarage.model.Receipt;
import com.example.autogarage.model.Repair;
import com.example.autogarage.repsitory.ReceiptRepository;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ReceiptDto> getAllReceipts() {
        return repository.findAll().stream()
                .map(receipt -> modelMapper.map(receipt, ReceiptDto.class)).collect(Collectors.toList());

    }

    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        Receipt newReceipt = repository.save(modelMapper.map(receiptDto, Receipt.class));
        return modelMapper.map(newReceipt, ReceiptDto.class);
    }

    public void updateReceiptById(ReceiptDto receiptDto, Long id) {
        Optional<Receipt> receiptOptional = repository.findById(id);
        if (receiptOptional.isPresent()) {
            Receipt receipt = receiptOptional.get();
            if (receiptDto.getRepair() != null) {
                receipt.setRepair(modelMapper.map(receiptDto.getRepair(),Repair.class));
            }
            if (receiptDto.getTotal() != null ) {
                receipt.setTotal(receiptDto.getTotal());
            }
            if (receipt.getIsPaid() != null) {
                receipt.setIsPaid(receiptDto.getIsPaid());
            }

            repository.save(receipt);
        } else {
            throw new RecordNotFoundException("receipt with id " + id + " not found");
        }
    }

}
