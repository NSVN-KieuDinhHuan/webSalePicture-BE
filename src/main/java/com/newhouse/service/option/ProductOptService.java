package com.newhouse.service.option;

import com.newhouse.model.entity.ProductOption;
import com.newhouse.repository.IproductOptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductOptService implements IProductOptService {
    @Autowired
    private IproductOptRepository productOptRepository;

    @Override
    public Iterable<ProductOption> findAll() {
        return productOptRepository.findAll();
    }

    @Override
    public Optional<ProductOption> findById(Long id) {
        return productOptRepository.findById(id);
    }

    @Override
    public ProductOption save(ProductOption productOption) {
        return productOptRepository.save(productOption);
    }

    @Override
    public void deleteById(Long id) {
        productOptRepository.deleteById(id);
    }
}
