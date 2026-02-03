package org.calendarmanagement.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Author {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AUTHOR_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Schedule> schedules = new ArrayList<>();

    public Author(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
    }
}
