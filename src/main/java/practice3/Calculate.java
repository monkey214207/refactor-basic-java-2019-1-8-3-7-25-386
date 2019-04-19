package practice3;

import java.math.BigDecimal;

public class Calculate {
    private Order order;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;

    public Calculate(Order order) {
        this.order = order;
    }

    public BigDecimal calculate() {
        subTotal = new BigDecimal(0);
        this.totalUpLineItems().subtractDiscount().calculateTax().calculateGrandTotal();
        return grandTotal;
    }

    private Calculate totalUpLineItems() {
        for (OrderLineItem lineItem : order.getOrderLineItemList()) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return this;
    }

    private Calculate subtractDiscount() {
        for (BigDecimal discount : order.getDiscounts()) {
            subTotal = subTotal.subtract(discount);
        }
        return this;
    }

    private Calculate calculateTax() {
        tax =  subTotal.multiply(order.getTax());
        return this;
    }

    private Calculate calculateGrandTotal() {
        grandTotal = subTotal.add(tax);
        return this;
    }
}
