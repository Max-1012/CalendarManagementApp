package org.calendarmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor
public class Comment extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="SCHEDULE_ID")
    private Schedule schedule;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    public Comment(Schedule schedule, String content, String author, String password) {
        this.schedule = schedule;
        this.content = content;
        this.author = author;
        this.password = password;
    }
}
