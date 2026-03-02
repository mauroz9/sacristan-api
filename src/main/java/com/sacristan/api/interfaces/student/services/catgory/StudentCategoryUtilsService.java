package com.sacristan.api.interfaces.student.services.catgory;

import com.sacristan.api.global.models.Category;
import com.sacristan.api.global.models.Sequence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCategoryUtilsService {

    public Integer countSequencesByCategory(Category category, List<Sequence> sequences) {
        Integer count = 0;
        for (Sequence sequence : sequences) {
            if (sequence.getCategory().getId().equals(category.getId())) {
                count++;
            }
        }
        return count;
    }

}
