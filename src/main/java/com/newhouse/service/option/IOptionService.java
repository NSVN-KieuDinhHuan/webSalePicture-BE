package com.newhouse.service.option;

import com.newhouse.model.entity.Option;
import com.newhouse.service.IGeneralService;



public interface IOptionService extends IGeneralService<Option> {
    Iterable<Option> getOptionByOptionGroup (int optionGroupId);
}