package org.calendarmanagement.repository;

import org.calendarmanagement.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    boolean existsByName(String name);

    Author findByName(String name);
}
