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
    @Column(name="comment_id")
    private Long id;

    // 스케쥴이 있어야 댓글이 있음
    @ManyToOne(fetch = FetchType.LAZY,optional = false) // jpa에서 null 허용 여부
    @JoinColumn(name="schedule_id",nullable = false) // db에서 null 허용 여부
    private Schedule schedule;

    // 유저가 있어야 댓글이 있음
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    public Comment(Long id, Schedule schedule, User user, String content) {
        this.id = id;
        this.schedule = schedule;
        this.user = user;
        this.content = content;
    }
}
