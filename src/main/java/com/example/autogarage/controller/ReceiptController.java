package com.example.autogarage.controller;

import com.example.autogarage.dto.ReceiptDto;
import com.example.autogarage.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/receipts")
public class ReceiptController {

    ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }
    @GetMapping(value = "")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<ReceiptDto>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @PostMapping()
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> createReceipt(@Valid @RequestBody ReceiptDto receiptDto, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            receiptService.createReceipt(receiptDto);
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Object> updateReceiptById
            (@Valid @RequestBody ReceiptDto receiptDto, @PathVariable Long id, BindingResult br) {
        StringBuilder sb = new StringBuilder();
        if (br.hasErrors()) {
            for (FieldError error : br.getFieldErrors()) {
                sb.append(error.getField() + ": ");
                sb.append(error.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        } else {
            receiptService.updateReceiptById(receiptDto, id);
            return ResponseEntity.noContent().build();
        }
    }
}