package com.newhouse.service.option;

import com.newhouse.model.entity.Option;
import com.newhouse.repository.IOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OptionService implements IOptionService {
    @Autowired
    private IOptionRepository optionRepository;

    @Override
    public Iterable<Option> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public Optional<Option> findById(Long id) {
        return optionRepository.findById(id);
    }

    @Override
    public Option save(Option productOption) {
        return optionRepository.save(productOption);
    }

    @Override
    public void deleteById(Long id) {
        optionRepository.deleteById(id);
    }

    @Override
    public Iterable<Option> getOptionByOptionGroup(int optionGroupId) {
        return optionRepository.getOptionByOptionGroup(optionGroupId);
    }
}
