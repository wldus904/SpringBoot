package com.infrun.myrestfulservice.study.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.board.entity.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoticeDto extends BoardDto {
    public static NoticeDto toDto(Board board) {
        NoticeDto noticeDto = new NoticeDto();

        noticeDto.setBoardConfigId(board.getBoardId());
        noticeDto.setBoardId(board.getBoardId());
        noticeDto.setBoardName(board.getBoardConfig().getTitle());
        noticeDto.setTitle(board.getTitle());
        noticeDto.setContent(board.getContent());
        noticeDto.setWriterMemberId(board.getMember().getMemberId());
        noticeDto.setWriterName(board.getMember().getName());
        noticeDto.setStatus(board.getStatus());
        noticeDto.setRefId(board.getRefId());
        noticeDto.setRegDate(board.getRegDate());
        noticeDto.setModDate(board.getModDate());

        return noticeDto;
    }
}
