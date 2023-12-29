package com.olteanuflorin86.sdjpaintro.domain;

import jakarta.persistence.AttributeOverride; 
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

@Entity
@AttributeOverrides({
    @AttributeOverride(
            name = "shippingAddress.address",
            column = @Column(name = "shipping_address")
    ),
    @AttributeOverride(
            name = "shippingAddress.city",
            column = @Column(name = "shipping_city")
    ),
    @AttributeOverride(
            name = "shippingAddress.state",
            column = @Column(name = "shipping_state")
    ),
    @AttributeOverride(
            name = "shippingAddress.zipCode",
            column = @Column(name = "shipping_zip_code")
    ),
    @AttributeOverride(
            name = "billToAddress.address",
            column = @Column(name = "bill_to_address")
    ),
    @AttributeOverride(
            name = "billToAddress.city",
            column = @Column(name = "bill_to_city")
    ),
    @AttributeOverride(
            name = "billToAddress.state",
            column = @Column(name = "bill_to_state")
    ),
    @AttributeOverride(
            name = "billToAddress.zipCode",
            column = @Column(name = "bill_to_zip_code")
    )
})
public class OrderHeader extends BaseEntity {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	private String customer;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
	
	@Embedded
	private Address shippingAddress;
	
	@Embedded
    private Address billToAddress;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillToAddress() {
        return billToAddress;
    }

    public void setBillToAddress(Address billToAddress) {
        this.billToAddress = billToAddress;
    }
    
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

//////    @Override
//////    public boolean equals(Object o) {
//////        if (this == o) return true;
//////        if (o == null || getClass() != o.getClass()) return false;
//////
//////        OrderHeader that = (OrderHeader) o;
//////
//////        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//////        return customer != null ? customer.equals(that.customer) : that.customer == null;
//////    }
//////
//////    @Override
//////    public int hashCode() {
//////        int result = id != null ? id.hashCode() : 0;
//////        result = 31 * result + (customer != null ? customer.hashCode() : 0);
//////        return result;
//////    }	
////
////    @Override
////    public boolean equals(Object o) {
////        if (this == o) return true;
////        if (!(o instanceof OrderHeader)) return false;
////        if (!super.equals(o)) return false;
////
////        OrderHeader that = (OrderHeader) o;
////
////        return getCustomer() != null ? getCustomer().equals(that.getCustomer()) : that.getCustomer() == null;
////    }
////
////    @Override
////    public int hashCode() {
////        int result = super.hashCode();
////        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
////        return result;
////    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof OrderHeader)) return false;
//        if (!super.equals(o)) return false;
//
//        OrderHeader that = (OrderHeader) o;
//
//        if (getCustomer() != null ? !getCustomer().equals(that.getCustomer()) : that.getCustomer() != null)
//            return false;
//        if (shippingAddress != null ? !shippingAddress.equals(that.shippingAddress) : that.shippingAddress != null)
//            return false;
//        return billToAddress != null ? billToAddress.equals(that.billToAddress) : that.billToAddress == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
//        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
//        result = 31 * result + (billToAddress != null ? billToAddress.hashCode() : 0);
//        return result;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderHeader)) return false;
        if (!super.equals(o)) return false;

        OrderHeader that = (OrderHeader) o;

        if (getCustomer() != null ? !getCustomer().equals(that.getCustomer()) : that.getCustomer() != null)
            return false;
        if (getShippingAddress() != null ? !getShippingAddress().equals(that.getShippingAddress()) : that.getShippingAddress() != null)
            return false;
        if (getBillToAddress() != null ? !getBillToAddress().equals(that.getBillToAddress()) : that.getBillToAddress() != null)
            return false;
        return getOrderStatus() == that.getOrderStatus();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getShippingAddress() != null ? getShippingAddress().hashCode() : 0);
        result = 31 * result + (getBillToAddress() != null ? getBillToAddress().hashCode() : 0);
        result = 31 * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        return result;
    }
}
