package org.calendarmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="schedules")
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // 외래키
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    public Schedule(String title, String content,Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
