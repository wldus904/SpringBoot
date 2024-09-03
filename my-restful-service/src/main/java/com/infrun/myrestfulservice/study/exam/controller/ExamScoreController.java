package com.infrun.myrestfulservice.study.exam.controller;

import com.infrun.myrestfulservice.study.exam.dto.ExamScoreDto;
import com.infrun.myrestfulservice.study.exam.service.ExamScoreService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam/score")
public class ExamScoreController {
    @Autowired
    private ExamScoreService examScoreService;

    @PostMapping
    public CommonResponse saveExamScore(@RequestBody ExamScoreDto examScoreDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            ExamScoreDto resExamScoreDto = examScoreService.saveExamScore(examScoreDto);
            commonResponse.setData(resExamScoreDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
