package com.infrun.myrestfulservice.study.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardDto {
    @JsonIgnore
    private Integer boardConfigId;
    private Integer boardId;
    @JsonIgnore
    private String boardName;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    private String content;
    @JsonIgnore
    private String writerMemberId;
    private String writerName;
    @JsonIgnore
    private int status;
    private String refId;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // 생성자 추가
    public static BoardDto toDto(Board board) {
        BoardDto boardDto = new BoardDto();

        boardDto.setBoardConfigId(board.getBoardId());
        boardDto.setBoardId(board.getBoardId());
        boardDto.setBoardName(board.getBoardConfig().getTitle());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setWriterMemberId(board.getMember().getMemberId());
        boardDto.setWriterName(board.getMember().getName());
        boardDto.setStatus(board.getStatus());
        boardDto.setRefId(board.getRefId());
        boardDto.setRegDate(board.getRegDate());
        boardDto.setModDate(board.getModDate());

        return boardDto;
    }
}
