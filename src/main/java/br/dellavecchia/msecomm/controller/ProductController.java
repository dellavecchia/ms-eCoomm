package br.dellavecchia.msecomm.controller;
import br.dellavecchia.msecomm.dto.ProductDTO;
import br.dellavecchia.msecomm.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO request) {
        Optional<ProductDTO> response = service.create(request);

///        if (response.isPresent()) {
//            return ResponseEntity<>(response.get(), HttpStatus.CREATED);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //JAVA 8/11 STYLE
        return response.map(productDTO -> new ResponseEntity<>(response.get(), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") Long id) {
        Optional<ProductDTO> response = service.getById(id);

//        if (response.isPresent()) {
//            return ResponseEntity.ok(response.get());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id,
                                             @RequestBody @Valid ProductDTO request) {
        Optional<ProductDTO> response = service.update(id, request);

        return response.map(ResponseEntity::ok).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inactive(@PathVariable("id") Long id) {
        boolean inactive = service.inactivate(id);
        return inactive
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<Void> activate(@PathVariable("id") Long id) {
        boolean activate = service.activate(id);
        return activate
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

