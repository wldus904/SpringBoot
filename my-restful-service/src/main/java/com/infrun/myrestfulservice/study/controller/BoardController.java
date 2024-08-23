package com.infrun.myrestfulservice.study.controller;

import com.infrun.myrestfulservice.study.dto.BoardDto;
import com.infrun.myrestfulservice.study.entity.Board;
import com.infrun.myrestfulservice.study.service.BoardService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/notice")
    public CommonResponse saveNotice(@RequestBody BoardDto boardDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            boardService.saveNotice(boardDto);
            commonResponse.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping("/notices")
    public CommonResponse getAllNotice() {
        CommonResponse commonResponse = new CommonResponse();

        try {
            List<BoardDto> boards = boardService.findAllNotice();
            commonResponse.setData(boards);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @PutMapping("/notice/{boardId}")
    public CommonResponse updateMapping(@PathVariable Integer boardId, @RequestBody BoardDto boardDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            Board board = boardService.updateNotice(boardId, boardDto);
            commonResponse.setData(board);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/notice/{boardId}")
    public CommonResponse deleteNotice(@PathVariable Integer boardId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            boardService.deleteNoticeById(boardId);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
