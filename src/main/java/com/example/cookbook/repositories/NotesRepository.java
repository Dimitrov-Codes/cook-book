package com.example.cookbook.repositories;

import com.example.cookbook.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Note, Long> {
}
