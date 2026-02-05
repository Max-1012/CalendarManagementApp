package org.calendarmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="schedules")
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SCHEDULE_ID")
    private Long id;

    @Setter
    @Column(length = 30, nullable = false)

    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Setter
    @Column(length = 15, nullable = false)
    private String author;

    @Column(length = 20, nullable = false)
    private String password;

    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }


}
