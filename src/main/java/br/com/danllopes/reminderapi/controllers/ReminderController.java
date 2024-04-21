package br.com.danllopes.reminderapi.controllers;

import br.com.danllopes.reminderapi.domain.Reminder;
import br.com.danllopes.reminderapi.dtos.ReminderDTO;
import br.com.danllopes.reminderapi.services.ReminderService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public ResponseEntity<Reminder> addReminder(@RequestBody ReminderDTO data) {
        Reminder newReminder = reminderService.createReminder(data);
        return new ResponseEntity<>(newReminder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Reminder>> getAllReminders() {
        List<Reminder> reminders = reminderService.getAllReminders();
        return new ResponseEntity<>(reminders, HttpStatus.OK);
    }

    @GetMapping("/{reminderId}")
    public ResponseEntity<Reminder> getReminder(@PathVariable String reminderId) {
        return new ResponseEntity<>(reminderService.getReminder(reminderId), HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/{reminderId}")
    public ResponseEntity<Reminder> updateReminder(@RequestBody @Valid ReminderDTO data, @PathVariable String reminderId) {
        return new ResponseEntity<>(reminderService.updateReminder(data, reminderId), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{reminderId}")
    public ResponseEntity<Void> deleteReminder(@PathVariable String reminderId) {
        reminderService.deleteReminder(reminderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
