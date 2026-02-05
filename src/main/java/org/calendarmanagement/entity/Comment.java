package org.calendarmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="comments")
@NoArgsConstructor
public class Comment extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_ID")
    private Long id;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(length = 100, nullable = false)
    private String content;

    @Column(length = 15, nullable = false)
    private String author;

    @Column(length = 20, nullable = false)
    private String password;

    public Comment(Long scheduleId, String content, String author, String password) {
        this.scheduleId = scheduleId;
        this.content = content;
        this.author = author;
        this.password = password;
    }
}
