package com.brain.brainapp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProducRepository producRepository;

    @Autowired
    public ProductService(ProducRepository producRepository){
        this.producRepository = producRepository;
    }

    public List<Product> getProducts() {
        return this.producRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = producRepository.findProductByName(product.getName());
        HashMap<String, Object> datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("error", true);
            datos.put("message","Ya existe un producto con ese nombre");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        producRepository.save(product);
        datos.put("dats", product);
        datos.put("message","Se guardo con exito");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );

    }
}
