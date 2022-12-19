package com.newhouse.service.option.optionList;

import com.newhouse.model.entity.ProductOptionList;
import com.newhouse.repository.IproductOptListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductOptListService implements IProductOptListService {
    @Autowired
    private IproductOptListRepository productOptListRepository;

    @Override
    public Iterable<ProductOptionList> findAll() {
        return productOptListRepository.findAll();
    }

    @Override
    public Optional<ProductOptionList> findById(Long id) {
        return productOptListRepository.findById(id);
    }

    @Override
    public ProductOptionList save(ProductOptionList productOptionList) {
        return productOptListRepository.save(productOptionList);
    }

    @Override
    public void deleteById(Long id) {
        productOptListRepository.deleteById(id);
    }
}
