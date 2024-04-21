package br.com.danllopes.reminderapi.services;

import br.com.danllopes.reminderapi.domain.Category;
import br.com.danllopes.reminderapi.domain.Reminder;
import br.com.danllopes.reminderapi.dtos.ReminderDTO;
import br.com.danllopes.reminderapi.exceptions.ReminderNotFoundException;
import br.com.danllopes.reminderapi.repositories.ReminderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReminderService {

    private final ReminderRepository repository;
    private final CategoryService categoryService;

    public ReminderService(ReminderRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public Reminder findReminderById(String id) {
        return this.repository.findById(id)
                .orElseThrow(ReminderNotFoundException::new);
    }

    public Reminder createReminder(@RequestBody @Valid ReminderDTO data) {
        Category category = this.categoryService.findCategoryById(data.category());
        Reminder newReminder = new Reminder(data, category);
        this.saveReminder(newReminder);
        return newReminder;
    }

    public Reminder updateReminder(ReminderDTO data, String id) {
        Category category = this.categoryService.findCategoryById(data.category());
        Reminder reminder = this.findReminderById(id);

        reminder.setTitle(data.title());
        reminder.setDescription(data.description());
        reminder.setReminderDate(data.date());
        reminder.setReminderTime(data.time());
        reminder.setCategory(category);
        reminder.setPriority(data.priority());
        this.saveReminder(reminder);
        return reminder;
    }

    public void deleteReminder(String id) {
        Reminder reminder = this.findReminderById(id);
        this.repository.delete(reminder);
    }

    public List<Reminder> getAllReminders() {
        return this.repository.findAll();
    }

    public Reminder getReminder(String id) {
        return this.findReminderById(id);
    }

    public List<Reminder> getRemindersByCategory(Category category) {
        return this.repository.findRemindersByCategory(category);
    }

    public void saveReminder(Reminder reminder) {
        this.repository.save(reminder);
    }
}
