package com.infrun.myrestfulservice.study.controller;

import com.infrun.myrestfulservice.study.dto.BoardDto;
import com.infrun.myrestfulservice.study.entity.Board;
import com.infrun.myrestfulservice.study.service.BoardService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/notices")
    public CommonResponse getAllNotice() {
        CommonResponse commonResponse = new CommonResponse();

        try {
            List<BoardDto> boards = boardService.getAllNotice();
            commonResponse.setData(boards);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }
}
