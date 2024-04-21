package br.com.danllopes.reminderapi.repositories;

import br.com.danllopes.reminderapi.domain.Category;
import br.com.danllopes.reminderapi.domain.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, String> {
    List<Reminder> findRemindersByCategory(Category category);
}
