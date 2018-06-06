package ua.nure.queuemanagementapi.util;

import ua.nure.queuemanagementapi.entity.CategoryEntity;

import java.util.Collections;
import java.util.Optional;

public interface CategoriesUtils {

    static void initCategoriesTree(CategoryEntity category) {
        Optional.ofNullable(category.getOptions())
                .orElse(Collections.emptyList())
                .forEach(c -> {
                    c.setParent(category);
                    initCategoriesTree(c);
                });
    }
}
