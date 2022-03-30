package com.digitalstork.devbookskata.repository;

import com.digitalstork.devbookskata.model.DevelopmentBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevelopmentBookRepository extends JpaRepository<DevelopmentBook, Long> {
}
