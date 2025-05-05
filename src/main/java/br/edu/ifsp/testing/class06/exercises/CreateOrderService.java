package br.edu.ifsp.testing.class06.exercises;

public class CreateOrderService {
    public void createOrder(Order order, AddressRecord record) {

        final Address address = new Address(record.street(), record.city(), record.state(), record.zip());

        if(order.items().isEmpty())
            throw new IllegalArgumentException("Pedido deve conter pelo menos um item");
        order.setStatus(Order.Status.PENDING);
    }
}
