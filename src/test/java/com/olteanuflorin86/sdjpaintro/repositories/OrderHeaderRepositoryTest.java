package com.olteanuflorin86.sdjpaintro.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;  
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.domain.OrderHeader;
import com.olteanuflorin86.sdjpaintro.domain.OrderLine;
import com.olteanuflorin86.sdjpaintro.domain.Product;
import com.olteanuflorin86.sdjpaintro.domain.ProductStatus;
import com.olteanuflorin86.sdjpaintro.domain.Customer;
import com.olteanuflorin86.sdjpaintro.domain.OrderApproval;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderHeaderRepositoryTest {

	@Autowired
    OrderHeaderRepository orderHeaderRepository;
	
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    OrderApprovalRepository orderApprovalRepository;

    Product product;

    @BeforeEach
    void setUp() {
        Product newProduct = new Product();
        newProduct.setProductStatus(ProductStatus.NEW);
        newProduct.setDescription("test product");
        product = productRepository.saveAndFlush(newProduct);
    }

    @Test
    void testSaveOrder() {
        OrderHeader orderHeader = new OrderHeader();
//        orderHeader.setCustomer("New Customer");
        Customer customer = new Customer();
        customer.setCustomerName("New Customer");
        Customer savedCustomer = customerRepository.save(customer);

        orderHeader.setCustomer(savedCustomer);
        
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());

        OrderHeader fetchedOrder = orderHeaderRepository.getById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertNotNull(fetchedOrder.getId());
        assertNotNull(fetchedOrder.getCreatedDate());
        assertNotNull(fetchedOrder.getLastModifiedDate());
    }

    @Test
    void testSaveOrderWithLine() {
        OrderHeader orderHeader = new OrderHeader();
//      orderHeader.setCustomer("New Customer");
        Customer customer = new Customer();
	    customer.setCustomerName("New Customer");
	    Customer savedCustomer = customerRepository.save(customer);
	
	    orderHeader.setCustomer(savedCustomer);

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantityOrdered(5);
        orderLine.setProduct(product);

//        orderHeader.setOrderLines(Set.of(orderLine));
//        orderLine.setOrderHeader(orderHeader);
        orderHeader.addOrderLine(orderLine);
        
        
        OrderApproval approval = new OrderApproval();
        approval.setApprovedBy("me");
//        OrderApproval savedApproval = orderApprovalRepository.save(approval);
//        orderHeader.setOrderApproval(savedApproval);
        orderHeader.setOrderApproval(approval);
        
        OrderHeader savedOrder = orderHeaderRepository.save(orderHeader);
        
        orderHeaderRepository.flush();

        assertNotNull(savedOrder);
        assertNotNull(savedOrder.getId());
        assertNotNull(savedOrder.getOrderLines());
        assertEquals(savedOrder.getOrderLines().size(), 1);
        
        OrderHeader fetchedOrder = orderHeaderRepository.getById(savedOrder.getId());

        assertNotNull(fetchedOrder);
        assertEquals(fetchedOrder.getOrderLines().size(), 1);
    }

}
