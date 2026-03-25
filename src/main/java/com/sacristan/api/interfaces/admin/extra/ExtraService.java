package com.sacristan.api.interfaces.admin.extra;

import aj.org.objectweb.asm.commons.Remapper;
import com.sacristan.api.global.entities.content.category.Category;
import com.sacristan.api.global.entities.content.category.CategoryModelService;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.entities.users.user.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtraService {

    private final UserModelService userModelService;
    private final CategoryModelService categoryModelService;


    public User getUser(Long id) {
        return userModelService.getById(id);
    }

    public Page<Category> getCategories(Pageable pageable) {
        return categoryModelService.pageAll(pageable);
    }

    public Category getCategory(Long id) {
        return categoryModelService.getById(id);
    }
}
