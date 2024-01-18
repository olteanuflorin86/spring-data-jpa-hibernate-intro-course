package com.olteanuflorin86.sdjpaintro;

import java.util.List;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.olteanuflorin86.sdjpaintro.domain.Customer;
import com.olteanuflorin86.sdjpaintro.domain.OrderHeader;
import com.olteanuflorin86.sdjpaintro.domain.OrderLine;
import com.olteanuflorin86.sdjpaintro.domain.Product;
import com.olteanuflorin86.sdjpaintro.domain.ProductStatus;
import com.olteanuflorin86.sdjpaintro.domain.Address;
import com.olteanuflorin86.sdjpaintro.repositories.CustomerRepository;
import com.olteanuflorin86.sdjpaintro.repositories.OrderHeaderRepository;
import com.olteanuflorin86.sdjpaintro.repositories.ProductRepository;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataLoadTest {
	
	// we add 3 product descriptions for fetching purposes:
	final String PRODUCT_D1 = "Product 1";
	final String PRODUCT_D2 = "Product 2";
	final String PRODUCT_D3 = "Product 3";
	
	// we add 1 customer name for fetching purposes:
	final String TEST_CUSTOMER = "TEST CUSTOMER";

	@Autowired
	OrderHeaderRepository orderHeaderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
    @Disabled
    @Rollback(value = false)
	@Test
	void testDataLoader() {
        List<Product> products = loadProducts();
        Customer customer = loadCustomers();

        int ordersToCreate = 100;

        for (int i = 0; i < ordersToCreate; i++){
            System.out.println("Creating order #: " + i);
            saveOrder(customer, products);
        }

        orderHeaderRepository.flush();
	}
    
    @Disabled
    @Test
    void testLazyVsEager() {
    	OrderHeader orderHeader = orderHeaderRepository.getById(5l);
    								 
    	System.out.println("Order Id is: " + orderHeader.getId());
    	System.out.println("Customer Name is: " + orderHeader.getCustomer().getCustomerName());
    }
	
//    @Disabled
    @Test
    void testN_PlusOneProblem() {
    	Customer customer = customerRepository.findCustomerByCustomerNameIgnoreCase(TEST_CUSTOMER).get();
    	
    	IntSummaryStatistics totalOrdered = orderHeaderRepository.findAllByCustomer(customer).stream()
    		.flatMap(orderHeader -> orderHeader.getOrderLines().stream())
    				.collect(Collectors.summarizingInt(ol -> ol.getQuantityOrdered()));
    	
    	System.out.println("Total ordered: " + totalOrdered.getSum());
    }
    
    private OrderHeader saveOrder(Customer customer, List<Product> products){
        Random random = new Random();

        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setCustomer(customer);

        products.forEach(product -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantityOrdered(random.nextInt(20));
//            orderHeader.getOrderLines().add(orderLine);
            orderHeader.addOrderLine(orderLine);
        });

        return orderHeaderRepository.save(orderHeader);
    }
	
    private Customer loadCustomers() {
        return getOrSaveCustomer(TEST_CUSTOMER);
    }
	
	private Customer getOrSaveCustomer(String customerName) {
		 return customerRepository.findCustomerByCustomerNameIgnoreCase(customerName)
				 .orElseGet(() -> {
	                    Customer c1 = new Customer();
	                    c1.setCustomerName(customerName);
	                    c1.setEmail("test@example.com");
	                    Address address = new Address();
	                    address.setAddress("123 Main");
	                    address.setCity("New Orleans");
	                    address.setState("LA");
	                    c1.setAddress(address);
	                    return customerRepository.save(c1);
	                });
	}
	
    private List<Product> loadProducts(){
        List<Product> products = new ArrayList<>();

        products.add(getOrSaveProduct(PRODUCT_D1));
        products.add(getOrSaveProduct(PRODUCT_D2));
        products.add(getOrSaveProduct(PRODUCT_D3));

        return products;
    }
    
	private Product getOrSaveProduct(String description) {
		return productRepository.findByDescription(description)
				.orElseGet(() -> {
					Product p1 = new Product();
                    p1.setDescription(description);
                    p1.setProductStatus(ProductStatus.NEW);
                    return productRepository.save(p1);
				});
	}
	
}
