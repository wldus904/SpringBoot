package com.infrun.myrestfulservice.study.board.dto;

import com.infrun.myrestfulservice.study.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeListDto {
    private Integer boardId;
    private String title;
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public static NoticeListDto toDto(Board board) {
        NoticeListDto noticeListDto = new NoticeListDto();

        noticeListDto.setBoardId(board.getBoardId());
        noticeListDto.setTitle(board.getTitle());
        noticeListDto.setWriterName(board.getMember().getName());
        noticeListDto.setRegDate(board.getRegDate());
        noticeListDto.setModDate(board.getModDate());

        return noticeListDto;
    }
}
