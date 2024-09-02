package com.infrun.myrestfulservice.study.exam.controller;

import com.infrun.myrestfulservice.study.exam.dto.ExamSubjectDto;
import com.infrun.myrestfulservice.study.exam.service.ExamSubjectService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
