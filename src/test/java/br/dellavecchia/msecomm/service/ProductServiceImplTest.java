package br.dellavecchia.msecomm.service;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.dellavecchia.msecomm.dto.ProductDTO;
import br.dellavecchia.msecomm.model.Product;
import br.dellavecchia.msecomm.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductServiceImplTest {


    @Autowired
    private ProductService service;
//    private ProductDTO request1;
//    private ProductDTO request2;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;





    @BeforeAll
    public static void setUp() {
//        startProduct();
        FixtureFactoryLoader.loadTemplates("br.dellavecchia.msecomm.fixture");



    }



    @Test
   void shouldCreateProduct() {
        ProductDTO request1 = Fixture.from(ProductDTO.class).gimme("valid_1");
        Optional<ProductDTO> response = service.create(request1);
        assertNotNull(response.get());
        assertEquals(request1.getName(), response.get().getName());
        assertEquals(request1.getDescription(), response.get().getDescription());
        assertEquals(request1.getPrice(), response.get().getPrice());
//        assertEquals(request1.getId(), response.get().getId());
        assertTrue(response.get().isAvailable());
    }



    @Test
    void shouldGetAllProducts() {
        ProductDTO request1 = Fixture.from(ProductDTO.class).gimme("valid_1");
        ProductDTO request2 = Fixture.from(ProductDTO.class).gimme("valid_2");
        //Created two requests
        Optional<ProductDTO> response1 = service.create(request1);
        Optional<ProductDTO> response2 = service.create(request2);

        //findAll on the repo
        List<Product> products = repository.findAll();
        List<ProductDTO> responses = new ArrayList<>();
        products.forEach(product -> {
            ProductDTO response = mapper.map(product, ProductDTO.class);
            responses.add(response);
        });
        List<ProductDTO> responseList = service.getAll();

        // Assertions
        assertEquals(responseList, responses);


    }

    @Test
    void shouldGetById() {
        ProductDTO request1 = Fixture.from(ProductDTO.class).gimme("valid_1");
        //Create a product and save on the repository
        Optional<ProductDTO> response1 = service.create(request1);
//        Product product1 = mapper.map(response1, Product.class);
//        repository.save(product1);
//        //Find by id on the repo
//        Optional<Product> product = repository.findById(product1.getId());
//        //Mapping as DTO
//       Optional<ProductDTO> productResponse = Optional.of(mapper.map(product.get(), ProductDTO.class));

       //Using the getById method

        Optional<ProductDTO> productById = service.getById(response1.get().getId());

        assertEquals(response1, productById);


    }

    @Test
    void shouldUpdate() {
        ProductDTO request2 = Fixture.from(ProductDTO.class).gimme("valid_2");
       //update request
        ProductDTO requestUpdated = new ProductDTO();
        requestUpdated.setDescription("The new playstation available the best in the world super happy with it");
        requestUpdated.setPrice(4999.97);
        //Creating product method
        Optional<ProductDTO> response2 = service.create(request2);
        //calling the update method
        Optional<ProductDTO> productUpdated = service.update(response2.get().getId(), requestUpdated);


        assertEquals(request2.getName(), productUpdated.get().getName());
        assertNotEquals(request2.getDescription(), productUpdated.get().getDescription());
        assertNotEquals(request2.getPrice(), productUpdated.get().getPrice());




    }

    @Test
    void shouldInactivate() {
        ProductDTO request2 = Fixture.from(ProductDTO.class).gimme("valid_2");
        Optional<ProductDTO> response2 = service.create(request2);
        boolean productInactivated = service.inactivate(response2.get().getId());
        assertFalse(productInactivated);

    }

    @Test
    void shouldActivate() {
        ProductDTO request2 = Fixture.from(ProductDTO.class).gimme("valid_2");
        Optional<ProductDTO> response2 = service.create(request2);
        boolean productInactivated = service.inactivate(response2.get().getId());
        boolean productActivated = service.activate(response2.get().getId());
        assertTrue(productActivated);


    }

//    private void startProduct(){
//        request1 = new ProductDTO();
//        request1.setId(1L);
//        request1.setName("Iphone 13");
//        request1.setDescription("Next generation cellphone with the components and features");
//        request1.setPrice(399.97);
//
//
//        request2 = new ProductDTO();
////        request2.setId(2L);
//        request2.setName("PlayStation 5");
//        request2.setDescription("Next generation Console with the components and features");
//        request2.setPrice(3499.98);



    }

