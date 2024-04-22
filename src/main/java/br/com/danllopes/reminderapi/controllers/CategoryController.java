package br.com.danllopes.reminderapi.controllers;

import br.com.danllopes.reminderapi.domain.Category;
import br.com.danllopes.reminderapi.domain.Reminder;
import br.com.danllopes.reminderapi.dtos.CategoryDTO;
import br.com.danllopes.reminderapi.services.CategoryService;
import br.com.danllopes.reminderapi.services.ReminderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ReminderService reminderService;

    public CategoryController(CategoryService categoryService, ReminderService reminderService) {
        this.categoryService = categoryService;
        this.reminderService = reminderService;
    }


    @PostMapping
    @Operation(summary = "Cadastrar", description = "Método responsável por salvar uma categoria.", tags = "Categoria")
    public ResponseEntity<Category> addCategory(@RequestBody @Valid CategoryDTO data) {
        Category newCategory = categoryService.createCategory(data);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Método responsável por listar todas as categorias registradas.", tags = "Categoria")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "Retornar por ID", description = "Método responsável por retornar categoria escolhida.", tags = "Categoria")
    public ResponseEntity<Category> getCategory(@PathVariable String categoryId) {
        return new ResponseEntity<>(this.categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{categoryId}")
    @Operation(summary = "Alterar", description = "Método responsável por atualizar determinada categoria.", tags = "Categoria")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid CategoryDTO data, @PathVariable String categoryId) {
        return new ResponseEntity<>(categoryService.updateCategory(data, categoryId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("{categoryId}")
    @Operation(summary = "Excluir", description = "Método responsável por deletar determinada categoria.", tags = "Categoria")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        List<Reminder> reminders = this.reminderService.getRemindersByCategory(category);

        for(Reminder reminder : reminders) {
            reminder.setCategory(null);
            this.reminderService.saveReminder(reminder);
        }

        categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
