package com.infrun.myrestfulservice.study.board.controller;

import com.infrun.myrestfulservice.study.board.dto.BoardDto;
import com.infrun.myrestfulservice.study.board.service.NoticeService;
import com.infrun.myrestfulservice.study.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @PostMapping("/notice")
    public CommonResponse saveNotice(@RequestBody BoardDto boardDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            noticeService.saveNotice(boardDto);
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
            List<BoardDto> boardDtos = noticeService.findAllNotice();
            commonResponse.setData(boardDtos);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @GetMapping("/notice/{boardId}")
    public CommonResponse getNoticeById(@PathVariable Integer boardId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            BoardDto boardDto = noticeService.findNoticeById(boardId);
            commonResponse.setData(boardDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return commonResponse;
    }

    @PutMapping("/notice/{boardId}")
    public CommonResponse updateMapping(@PathVariable Integer boardId, @RequestBody BoardDto boardDto) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            BoardDto resBoardDto = noticeService.updateNotice(boardId, boardDto);
            commonResponse.setData(resBoardDto);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }

    @DeleteMapping("/notice/{boardId}")
    public CommonResponse deleteNotice(@PathVariable Integer boardId) {
        CommonResponse commonResponse = new CommonResponse();

        try {
            noticeService.deleteNoticeById(boardId);
        } catch (Exception e) {
            commonResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        return commonResponse;
    }
}
