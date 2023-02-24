package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.Invoice.Invoice;
import com.egeuniversity.Tez.Model.Invoice.InvoiceRequestDto;
import com.egeuniversity.Tez.Model.Order.Order;
import com.egeuniversity.Tez.Model.Order.OrderResponseDto;
import com.egeuniversity.Tez.Service.Invoice.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping(path = "/create")
    public @ResponseBody ResponseEntity<OrderResponseDto> createInvoice(@RequestBody InvoiceRequestDto invoiceRequestDto) {
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceRequestDto));
    }

    @GetMapping(path = "/getAll")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

}
