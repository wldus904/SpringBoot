package com.infrun.myrestfulservice.study.exam.controller;

import com.infrun.myrestfulservice.study.exam.dto.ExamSubjectDto;
import com.infrun.myrestfulservice.study.exam.service.ExamSubjectService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam/subject")
public class ExamSubjectController {
    @Autowired
    private ExamSubjectService examSubjectService;
    
    @PostMapping
    public CommonResponse saveExamSubject(@RequestBody ExamSubjectDto examSubjectDto) {
        CommonResponse commonResponse = new CommonResponse();
        
        try {
            ExamSubjectDto resEamSubjectDto = examSubjectService.saveExamSubject(examSubjectDto);
            commonResponse.setData(resEamSubjectDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        
        return commonResponse;
    }

    @DeleteMapping("/{examSubjectId}")
    public CommonResponse deleteExamSubject(@PathVariable Integer examSubjectId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            examSubjectService.deleteExamSubject(examSubjectId);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
