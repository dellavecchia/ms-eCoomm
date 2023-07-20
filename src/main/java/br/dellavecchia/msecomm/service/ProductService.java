package br.dellavecchia.msecomm.service;

import br.dellavecchia.msecomm.dto.ProductDTO;


import java.util.List;
import java.util.Optional;


public interface ProductService {

    Optional<ProductDTO> create(ProductDTO request);

    List<ProductDTO> getAll();

    Optional<ProductDTO> getById(Long id);

    Optional<ProductDTO> update(Long id, ProductDTO request);

    boolean inactivate(Long id);

    boolean activate(Long id);

}
