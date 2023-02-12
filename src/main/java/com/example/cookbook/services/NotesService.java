package com.example.cookbook.services;

import com.example.cookbook.domain.Note;
import com.example.cookbook.dto.NotesDTO;
import org.springframework.stereotype.Service;

@Service
public class NotesService {
    public NotesDTO convertToDTO(Note notes){
        NotesDTO notesDTO = new NotesDTO();
        notesDTO.setId(notes.getId());
        notesDTO.setRecipeNotes(notes.getRecipeNotes());
        return notesDTO;
    }

    public Note convertToNotes(NotesDTO notesDTO) {
        Note notes = new Note();
        notes.setRecipeNotes(notesDTO.getRecipeNotes());
        notes.setId(notesDTO.getId());
        return notes;
    }
}
