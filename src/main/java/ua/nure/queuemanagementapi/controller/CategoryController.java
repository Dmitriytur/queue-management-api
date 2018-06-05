package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.repository.CategoryRepository;

@RestController
public class CategoryController {

    @Autowired
    public CategoryRepository categoryRepository;
}
