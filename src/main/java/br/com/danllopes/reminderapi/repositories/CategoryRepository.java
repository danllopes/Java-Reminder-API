package br.com.danllopes.reminderapi.repositories;

import br.com.danllopes.reminderapi.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
