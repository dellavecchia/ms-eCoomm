package br.dellavecchia.msecomm.repository;

import br.dellavecchia.msecomm.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product(2L, "Rolex",
                "A very expensive and exclusive watch that shows elegance and glamour",
                10000.0, true);
        repository.save(product);

    }

    @AfterEach
    void tearDown() {
        product = null;
        repository.deleteAll();
    }


    //Test case SUCCESS
    @Test
    void testFindByName_Found() {
        List<Product> productList = repository.findByName("Rolex");
        assertThat(productList.get(0).getId()).isEqualTo(product.getId());
        assertThat(productList.get(0).getDescription()).isEqualTo(product.getDescription());
    }

    //Test case FAILURE
    @Test
    void tesFindByName_NotFound() {
        List<Product> productList = repository.findByName("Casio");
        assertThat(productList.isEmpty()).isTrue();


    }

}