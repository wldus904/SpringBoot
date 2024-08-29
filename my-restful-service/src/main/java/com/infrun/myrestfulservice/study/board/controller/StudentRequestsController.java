package com.infrun.myrestfulservice.study.board.controller;

import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.dto.StudentRequestsDto;
import com.infrun.myrestfulservice.study.board.entity.Board;
import com.infrun.myrestfulservice.study.board.service.StudentRequestsService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
public class StudentRequestsController {
    @Autowired
    private StudentRequestsService studentRequestsService;

    @PostMapping("/student-request")
    public CommonResponse saveStudentRequest (@RequestBody StudentRequestsDto studentRequestsDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            studentRequestsService.saveStudentRequests(studentRequestsDto);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping("/student-requests")
    public CommonResponse getStudentRequests () {
        CommonResponse commonResponse = new CommonResponse();

        try {
            List<BoardDto> boardDtos = studentRequestsService.getStudentRequests();
            commonResponse.setData(boardDtos);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping("/student-request/{boardId}")
    public CommonResponse getStudentRequest (@PathVariable Integer boardId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            BoardDto boardDto = studentRequestsService.getStudentRequest(boardId);
            commonResponse.setData(boardDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @PutMapping("/student-request/{boardId}")
    public CommonResponse updateStudentRequest (@PathVariable Integer boardId, @RequestBody BoardDto boardDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            BoardDto resBoardDto = studentRequestsService.updateStudentRequest(boardDto, boardId);
            commonResponse.setData(resBoardDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/student-request/{boardId}")
    public CommonResponse deleteStudentRequest (@PathVariable Integer boardId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            studentRequestsService.deleteStudentRequest(boardId);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
