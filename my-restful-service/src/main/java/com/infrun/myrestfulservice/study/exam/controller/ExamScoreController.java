package com.infrun.myrestfulservice.study.exam.controller;

import com.infrun.myrestfulservice.study.exam.dto.ExamScoreCondition;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreDto;
import com.infrun.myrestfulservice.study.exam.dto.ExamScoreSaveDto;
import com.infrun.myrestfulservice.study.exam.dto.ExamSelectDto;
import com.infrun.myrestfulservice.study.exam.dto.group.ExamGroupDto;
import com.infrun.myrestfulservice.study.exam.service.ExamScoreService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam/score")
public class ExamScoreController {
    @Autowired
    private ExamScoreService examScoreService;

    @PostMapping
    public CommonResponse saveExamScore(@RequestBody ExamScoreSaveDto examScoreDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            examScoreService.saveExamScore(examScoreDto);
            commonResponse.setData(HttpStatus.OK.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping
    public CommonResponse getScore(@ModelAttribute ExamScoreCondition condition) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            List<ExamGroupDto> examScoreDtos = examScoreService.getExamScore(condition);
            commonResponse.setData(examScoreDtos);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
