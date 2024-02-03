package com.campus.app.service;

import com.campus.app.entity.StudyProgram;
import com.campus.app.repository.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramService {
    @Autowired
    private StudyProgramRepository repository;

    public StudyProgram createStudyProgram(StudyProgram studyProgram) {
        return repository.save(studyProgram);
    }

    public List<StudyProgram> getStudyProgram() {
        return repository.findAll();
    }

    public StudyProgram getStudyProgramById(int id) {
        return repository.findById(id).orElse(null);
    }

    public StudyProgram updateStudyProgram(StudyProgram studyProgram) {
        StudyProgram studyProgramExisting = repository.findById(studyProgram.getId()).orElse(null);
        if(studyProgramExisting != null) {
            studyProgramExisting.setName(studyProgram.getName());

            return repository.save(studyProgramExisting);
        } else {
            return null;
        }
    }

    public StudyProgram deleteStudyProgram(int id) {
        Optional<StudyProgram> studyProgramToDeleteOptional = repository.findById(id);

        // Check if the student exists
        if (studyProgramToDeleteOptional.isPresent()) {
            // Delete the student from the repository
            repository.deleteById(id);
            // Return the deleted student
            return studyProgramToDeleteOptional.get();
        } else {
            // If the student is not found, you can handle it as needed (throw exception, return null, etc.)
            return null;
        }
    }

    public boolean isNameProgramExists(String studyProgramName) {
        return repository.findByName(studyProgramName) != null;
    }
}
