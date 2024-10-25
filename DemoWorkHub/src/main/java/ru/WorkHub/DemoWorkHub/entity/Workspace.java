package ru.WorkHub.DemoWorkHub.entity; // Изменено название пакета на соответствующее проекту WorkHub

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workspaces") // Изменено с STUDENTS на workspaces
public class Workspace { // Изменено с Student на Workspace
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location") // Предполагаем, что данные о расположении важны для рабочего пространства
    private String location;

    @Column(name = "capacity") // Вместимость, например, сколько людей может разместиться
    private int capacity;
}