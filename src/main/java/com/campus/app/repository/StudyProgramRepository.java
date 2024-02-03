package com.campus.app.repository;

import com.campus.app.entity.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyProgramRepository extends JpaRepository<StudyProgram,Integer> {

    StudyProgram findByName(String studyProgramName);
}
