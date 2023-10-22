package br.dellavecchia.msecomm.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.dellavecchia.msecomm.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("br.dellavecchia.msecomm.fixture");
    }

    @Test
    void shouldCreateProductController() throws Exception{
        ProductDTO request1 = Fixture.from(ProductDTO.class).gimme("valid_1");
        String content = mapper.writeValueAsString(request1);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .header(HttpHeaders.AUTHORIZATION, "Token foo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}