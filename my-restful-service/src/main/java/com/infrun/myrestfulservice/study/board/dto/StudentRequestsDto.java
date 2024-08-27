package com.infrun.myrestfulservice.study.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestsDto extends BoardDto {
    private Integer boardStudentRequestsId;
    private int requestsType;
}
