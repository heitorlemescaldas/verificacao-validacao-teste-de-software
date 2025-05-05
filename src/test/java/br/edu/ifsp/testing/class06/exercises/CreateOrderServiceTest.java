package br.edu.ifsp.testing.class06.exercises;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CreateOrderServiceTest {

    @Test
    @DisplayName("Should create new order")
    void shouldCreateNewOrder() {
        CreateOrderService sut = new CreateOrderService();
        OrderItem item = new OrderItem("Item");
        Order order = new Order();
        order.add(item);
        AddressRecord address = new AddressRecord("Street", "City", "State", "Zip");

        sut.createOrder(order, address);

        assertThat(order.status).isEqualTo(Order.Status.PENDING);
    }

    @Test
    @DisplayName("Should not accept orders without order items")
    void shouldNotAcceptOrdersWithoutOrderItems() {
        CreateOrderService sut = new CreateOrderService();
        Order order = new Order();
        AddressRecord address = new AddressRecord("Street", "City", "State", "Zip");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sut.createOrder(order, address))
                .withMessage("Pedido deve conter pelo menos um item");
    }

    @Test
    @DisplayName("Should not create a new order with invalid address")
    void shouldNotCreateANewOrderWithInvalidAddress() {
        CreateOrderService sut = new CreateOrderService();
        AddressRecord address = new AddressRecord("", "City", "State", "Zip");
        Order order = new Order();
        OrderItem item = new OrderItem("Item");
        order.add(item);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> sut.createOrder(order, address))
                .withMessage("Endereço de entrega inválido.");
    }

}