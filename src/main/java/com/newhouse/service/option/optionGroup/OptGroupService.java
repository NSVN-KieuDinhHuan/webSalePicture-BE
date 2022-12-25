package com.newhouse.service.option.optionGroup;

import com.newhouse.model.entity.Option;
import com.newhouse.model.entity.OptionGroup;
import com.newhouse.repository.IOptGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptGroupService implements IOptGroupService {
    @Autowired
    private IOptGroupRepository optGroupRepository;

    @Override
    public Iterable<OptionGroup> findAll() {
        return optGroupRepository.findAll();
    }

    @Override
    public Optional<OptionGroup> findById(Long id) {
        return optGroupRepository.findById(id);
    }

    @Override
    public OptionGroup save(OptionGroup optionGroup) {
        return optGroupRepository.save(optionGroup);
    }

    @Override
    public void deleteById(Long id) {
        optGroupRepository.deleteById(id);
    }

}
