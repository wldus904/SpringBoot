package com.infrun.myrestfulservice.study.exam.controller;

import com.infrun.myrestfulservice.study.exam.dto.ExamDto;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreCondition;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreDto;
import com.infrun.myrestfulservice.study.exam.service.ExamService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public CommonResponse saveExam(@RequestBody ExamDto examDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            ExamDto resExamDto = examService.saveExam(examDto);
            commonResponse.setData(resExamDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping
    public CommonResponse getExam(@ModelAttribute ExamScoreCondition condition) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            List<ExamDto> examDtos = examService.getExam(condition);
            commonResponse.setData(examDtos);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/{examId}")
    public CommonResponse deleteExam(@PathVariable Integer examId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            examService.deleteExam(examId);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
