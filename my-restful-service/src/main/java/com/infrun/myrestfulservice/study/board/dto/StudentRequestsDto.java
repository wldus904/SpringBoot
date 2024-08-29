package com.infrun.myrestfulservice.study.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infrun.myrestfulservice.study.board.entity.StudentRequests;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestsDto extends BoardDto {
    private Integer boardStudentRequestsId;
    private int requestsType;

    public static StudentRequestsDto toDto (StudentRequests studentRequests) {
        StudentRequestsDto studentRequestsDto = new StudentRequestsDto();
        studentRequestsDto.setBoardStudentRequestsId(studentRequestsDto.getBoardStudentRequestsId());
        studentRequestsDto.setRequestsType(studentRequests.getRequestsType());
        return studentRequestsDto;
    }
}
