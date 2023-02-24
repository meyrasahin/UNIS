package com.egeuniversity.Tez.Service.Invoice;

import com.egeuniversity.Tez.Model.Invoice.Invoice;
import com.egeuniversity.Tez.Model.Invoice.InvoiceRequestDto;
import com.egeuniversity.Tez.Model.Order.OrderResponseDto;

import java.util.List;

public interface InvoiceService {

    OrderResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto);

    List<Invoice> getAllInvoices();
}
