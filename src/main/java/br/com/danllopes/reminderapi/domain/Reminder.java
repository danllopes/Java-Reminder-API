package br.com.danllopes.reminderapi.domain;

import br.com.danllopes.reminderapi.domain.enums.Priority;
import br.com.danllopes.reminderapi.dtos.ReminderDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "reminder")
@Entity(name = "reminder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    private LocalDate reminderDate;

    private LocalTime reminderTime;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Reminder(ReminderDTO data, Category category) {
        this.title = data.title();
        this.description = data.description();
        this.reminderDate = data.date();
        this.reminderTime = data.time();
        this.category = category;
        this.priority = data.priority();
    }
}
