package br.com.danllopes.reminderapi.domain;

import br.com.danllopes.reminderapi.dtos.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "category")
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    public Category(CategoryDTO data) {
        this.name = data.name();
    }
}
