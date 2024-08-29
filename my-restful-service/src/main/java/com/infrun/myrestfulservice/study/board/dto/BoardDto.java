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
    private String boardName;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    private String content;
    private String writerMemberId;
    private int status;
    private String refId;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    // 생성자 추가
    public BoardDto(Integer boardConfigId,Integer boardId, String boardName, String title, String content,
                    String writerMemberId, int status, String refId,
                    LocalDateTime regDate, LocalDateTime modDate) {
        this.boardConfigId = boardConfigId;
        this.boardId = boardId;
        this.boardName = boardName;
        this.title = title;
        this.content = content;
        this.writerMemberId = writerMemberId;
        this.status = status;
        this.refId = refId;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    // 생성자 추가
    public static BoardDto toDto(Board board) {
        BoardDto boardDto = new BoardDto();

        boardDto.setBoardConfigId(board.getBoardId());
        boardDto.setBoardId(board.getBoardId());
        boardDto.setBoardName(board.getBoardConfig().getTitle());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        boardDto.setWriterMemberId(board.getWriterMemberId());
        boardDto.setStatus(board.getStatus());
        boardDto.setRefId(board.getRefId());
        boardDto.setRegDate(board.getRegDate());
        boardDto.setModDate(board.getModDate());

        return boardDto;
    }
}
