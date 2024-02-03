package com.campus.app.controller;


import com.campus.app.entity.StudyProgram;
import com.campus.app.response.StudyProgramResponse;
import com.campus.app.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/study-program")
public class StudyProgramController {


    @Autowired
    private StudyProgramService service;

    @PostMapping("/create")
    public ResponseEntity<StudyProgramResponse<StudyProgram>> createStudyProgram(@RequestBody StudyProgram studyProgram) {
        if(service.isNameProgramExists(studyProgram.getName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StudyProgramResponse<> (false, 400, "Name Program Already Exists", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new StudyProgramResponse<>(false, 200, "success created Study Program", null));
    }

    @GetMapping("/all")
    public ResponseEntity<StudyProgramResponse<List<StudyProgram>>> getAllStudyProgram() {
        List<StudyProgram> studyPrograms = service.getStudyProgram();
        if(!studyPrograms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new StudyProgramResponse<>(true, 200, "Success get all data", studyPrograms));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudyProgramResponse<>(false, 404, "data not found", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyProgramResponse<StudyProgram>> getAllStudyProgram(@PathVariable int id) {
        StudyProgram studyProgram = service.getStudyProgramById(id);
        if(studyProgram != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new StudyProgramResponse<>(true, 200, "Success get Study Program", studyProgram));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudyProgramResponse<>(false, 404, "Study program not found", null));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<StudyProgramResponse<StudyProgram>> updateStudyProgram(@RequestBody StudyProgram studyProgram) {
        StudyProgram updateStudyProgram = service.updateStudyProgram(studyProgram);
        if(updateStudyProgram != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new StudyProgramResponse<>(true, 200, "Success update Study Program Name", updateStudyProgram));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StudyProgramResponse<>(false, 404, "not data found!!", null));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudyProgramResponse<StudyProgram>> deleteStudyProgram(@PathVariable int id) {
        StudyProgram deleteStudyProgram = service.deleteStudyProgram(id);

        if (deleteStudyProgram != null) {
            // Return success response if the student was found and deleted
            return ResponseEntity.ok(new StudyProgramResponse<>(true, 200, "Success delete data", deleteStudyProgram));
        } else {
            // Return error response if the student was not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StudyProgramResponse<>(false, 404, "Student not found", null));
        }
    }
}
