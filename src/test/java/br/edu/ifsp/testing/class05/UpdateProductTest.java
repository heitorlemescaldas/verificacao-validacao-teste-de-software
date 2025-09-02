package br.edu.ifsp.testing.class05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductTest {

    @Mock ProductRepository repository;
    @InjectMocks UpdateProduct sut; // System Under Test

    Product product;

    @BeforeEach
    void setup() {
        product = new Product("1", "Laptop", 1000.0, 10);
    }

    @Test
    @DisplayName("Should update product when data is valid and product exists")
    void shouldUpdateProductSuccessfully() {
        when(repository.findById("1")).thenReturn(product);

        boolean result = sut.update("1", "Notebook", 1200.0, 5);

        verify(repository).update(product);
        assertThat(result).isTrue();
        assertThat(product.getName()).isEqualTo("Notebook");
        assertThat(product.getPrice()).isEqualTo(1200.0);
        assertThat(product.getQuantity()).isEqualTo(5);
    }

    @Test
    @DisplayName("Should return false when product does not exist")
    void shouldReturnFalseIfProductDoesNotExist() {
        when(repository.findById("2")).thenReturn(null);

        boolean result = sut.update("2", "Phone", 800.0, 3);

        verify(repository, never()).update(any());
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Should throw exception for invalid data")
    void shouldThrowForInvalidData() {
        when(repository.findById("1")).thenReturn(product);

        assertThatThrownBy(() -> sut.update("1", "", -10.0, -5))
                .isInstanceOf(IllegalArgumentException.class);

        verify(repository, never()).update(any());
    }
}
