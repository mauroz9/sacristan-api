package com.sacristan.api.global.entities.content.step;

import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StepModelService extends BaseServiceImpl<Step, Long, StepRepository> {
    public void deleteBySequenceId(Long id) {
        repository.deleteBySequenceId(id);
    }
}



