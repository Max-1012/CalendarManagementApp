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
    @Column(name="schedule_id")
    private Long id;

    // user가 있어야 일정도 있음
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Setter
    @Column(nullable = false, length = 30)
    private String title;

    @Setter
    @Column(nullable = false,length = 100)
    private String content;

    public Schedule( User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
}
