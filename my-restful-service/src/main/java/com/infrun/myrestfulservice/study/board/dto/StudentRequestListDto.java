package com.infrun.myrestfulservice.study.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.board.constant.StudentRequestType;
import com.infrun.myrestfulservice.study.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentRequestListDto {
    private Integer boardId;
    private String title;
    private String writerName;
    private StudentRequestType requestType;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static StudentRequestListDto toDto(Board board) {
        StudentRequestListDto studentRequestListDto = new StudentRequestListDto();

        studentRequestListDto.setBoardId(board.getBoardId());
        studentRequestListDto.setTitle(board.getTitle());
        studentRequestListDto.setWriterName(board.getMember().getName());
        studentRequestListDto.setRequestType(StudentRequestType.fromCode(board.getStudentRequests().getRequestsType()));
        studentRequestListDto.setRegDate(board.getRegDate());
        studentRequestListDto.setModDate(board.getModDate());

        return studentRequestListDto;
    }
}
