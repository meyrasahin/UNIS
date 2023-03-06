package com.egeuniversity.Tez.Service.Invoice;


import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.Address.AddressRequestDTO;
import com.egeuniversity.Tez.Model.Customer.Customer;
import com.egeuniversity.Tez.Model.Customer.CustomerType;
import com.egeuniversity.Tez.Model.Invoice.Invoice;
import com.egeuniversity.Tez.Model.Invoice.InvoiceRequestDto;
import com.egeuniversity.Tez.Model.Order.Order;
import com.egeuniversity.Tez.Model.Order.OrderResponseDto;
import com.egeuniversity.Tez.Model.Order.OrderStatus;
import com.egeuniversity.Tez.Model.Utility.LocalDateTimeUtility;
import com.egeuniversity.Tez.Repository.Address.AddressRepository;
import com.egeuniversity.Tez.Repository.Customer.CustomerRepository;
import com.egeuniversity.Tez.Repository.Invoice.InvoiceRepository;
import com.egeuniversity.Tez.Repository.Order.OrderRepository;
import com.egeuniversity.Tez.Service.Address.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final OrderRepository orderRepository;
    private final InvoiceRepository invoiceRepository;
    private final LocalDateTimeUtility localDateTimeUtility;
    private final CustomerRepository customerRepository;
    private final AddressServiceImpl addressService;

    @Override
    public OrderResponseDto createInvoice(InvoiceRequestDto invoiceRequestDto) {

        Invoice invoice = invoiceRepository.save(assembleCreateInvoice(invoiceRequestDto));
        Order order = orderRepository.getById(invoiceRequestDto.getOrderId());
        order.setOrderStatus(OrderStatus.COMPLETED);
        invoiceRepository.save(invoice);
        return OrderResponseDto.builder()
                .orderStatus(OrderStatus.COMPLETED)
                .situation("Fatura başarıyla oluşturuldu.")
                .build();
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    private Invoice assembleCreateInvoice(InvoiceRequestDto invoiceRequestDto) {
        Order order = orderRepository.getById(invoiceRequestDto.getOrderId());
        Customer customer = customerRepository.getById(invoiceRequestDto.getCustomerId());
        AddressRequestDTO addressRequestDTO = invoiceRequestDto.getAddress();

        return Invoice.builder()
                .createdAt(localDateTimeUtility.getCurrentDateTime())
                .invoiceType(invoiceRequestDto.getCustomerType())
                .totalPrice(order.getTotalPrice())
                .customer(customer)
                .order(order)
                .address(addressRequestDTO == null ? order.getAddress() : addressService.assembleAddress(addressRequestDTO) )
                .build();
    }
}
