package br.com.danllopes.reminderapi.services;

import br.com.danllopes.reminderapi.domain.Category;
import br.com.danllopes.reminderapi.dtos.CategoryDTO;
import br.com.danllopes.reminderapi.exceptions.CategoryNotFoundException;
import br.com.danllopes.reminderapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findCategoryById(String id) {
        return this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public Category createCategory(CategoryDTO data) {
        Category newCategory = new Category(data);
        this.saveCategory(newCategory);
        return newCategory;
    }

    public Category updateCategory(CategoryDTO data, String id) {
        Category category = this.findCategoryById(id);
        category.setName(data.name());
        this.saveCategory(category);
        return category;
    }

    public void deleteCategory(String id) {
        Category category = this.findCategoryById(id);
        this.repository.delete(category);
    }

    public List<Category> getAllCategories() {
        return this.repository.findAll();
    }

    private void saveCategory(Category category) {
        this.repository.save(category);
    }
}
