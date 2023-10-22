package br.dellavecchia.msecomm.service;


import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.dellavecchia.msecomm.dto.ProductDTO;
import br.dellavecchia.msecomm.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//        List<Product> products = repository.findAll();
//        List<ProductDTO> responses = new ArrayList<>();
//        products.forEach(product -> {
//            ProductDTO response = mapper.map(product, ProductDTO.class);
//            responses.add(response);
//        });
        List<ProductDTO> responseList = service.getAll();


        // Assertions
        assertNotNull(responseList);
        assertEquals(responseList.get(0).getName(), response1.get().getName());
        assertEquals(responseList.get(0).getDescription(), response1.get().getDescription());
        assertEquals(responseList.get(0).getPrice(), response1.get().getPrice());
        assertEquals(responseList.get(1).getName(), response2.get().getName());
        assertEquals(responseList.get(1).getDescription(), response2.get().getDescription());
        assertEquals(responseList.get(1).getPrice(), response2.get().getPrice());

    }

    @Test
    void shouldGetById() {
        ProductDTO request1 = Fixture.from(ProductDTO.class).gimme("valid_1");
        //Create a product and save on the repository
        Optional<ProductDTO> response1 = service.create(request1);


        Optional<ProductDTO> productById = service.getById(response1.get().getId());
        // Assertions
        assertNotNull(productById);
        assertEquals(response1, productById);
        assertEquals(productById.get().getName(), response1.get().getName());
        assertEquals(productById.get().getDescription(), response1.get().getDescription());
        assertEquals(productById.get().getPrice(), response1.get().getPrice());
        assertTrue(productById.get().isAvailable());


    }

    @Test
    void shouldUpdate() {
        ProductDTO request2 = Fixture.from(ProductDTO.class).gimme("valid_2");
        Optional<ProductDTO> response2 = service.create(request2);
       //update request
        ProductDTO requestUpdated = new ProductDTO();
        requestUpdated.setDescription("The new playstation available the best in the world super happy with it");
        requestUpdated.setPrice(4999.97);
        //Creating product method

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

