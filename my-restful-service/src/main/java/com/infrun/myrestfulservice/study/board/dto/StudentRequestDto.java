package com.infrun.myrestfulservice.study.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.board.constant.StudentRequestType;
import com.infrun.myrestfulservice.study.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestDto extends BoardDto {
    @JsonIgnore
    private Integer boardStudentRequestsId;
    @JsonIgnore
    private Integer boardId;
    private StudentRequestType requestType;

    public static StudentRequestDto toDto(Board board) {
        StudentRequestDto studentRequestDto = new StudentRequestDto();

        studentRequestDto.setBoardConfigId(board.getBoardId());
        studentRequestDto.setBoardId(board.getBoardId());
        studentRequestDto.setBoardName(board.getBoardConfig().getTitle());
        studentRequestDto.setTitle(board.getTitle());
        studentRequestDto.setContent(board.getContent());
        studentRequestDto.setWriterMemberId(board.getMember().getMemberId());
        studentRequestDto.setWriterName(board.getMember().getName());
        studentRequestDto.setStatus(board.getStatus());
        studentRequestDto.setRefId(board.getRefId());
        studentRequestDto.setRegDate(board.getRegDate());
        studentRequestDto.setModDate(board.getModDate());
        studentRequestDto.setBoardStudentRequestsId(board.getStudentRequests().getBoardStudentRequestsId());
        studentRequestDto.setBoardId(board.getBoardId());
        studentRequestDto.setRequestType(StudentRequestType.fromCode(board.getStudentRequests().getRequestsType()));

        return studentRequestDto;
    }
}
