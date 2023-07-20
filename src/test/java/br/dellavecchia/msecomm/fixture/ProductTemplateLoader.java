package br.dellavecchia.msecomm.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.dellavecchia.msecomm.dto.ProductDTO;

public class ProductTemplateLoader implements TemplateLoader {

    @Override
    public void load(){
        Fixture.of(ProductDTO.class).addTemplate("valid_1", new Rule(){{
            add("name","Iphone 14 Pro Max" );
            add("description", "Next generation cellphone with the components and features");
            add("price",random(Double.class, range(0.01, 7999.98)) );
            }});
        Fixture.of(ProductDTO.class).addTemplate("valid_2", new Rule(){{
            add("name", "PlayStation 5" );
            add("description", "Next generation Console with the components and features");
            add("price",random(Double.class, range(0.01, 7999.98)) );
        }});
    }

}
