package com.infrun.myrestfulservice.study.subject.dto;

import com.infrun.myrestfulservice.study.subject.entity.Subject;
import lombok.Data;

@Data
public class SubjectDto {
    private String subjectCode;
    private String subjectName;

    public static SubjectDto toDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectCode(subject.getSubjectCode());
        subjectDto.setSubjectName(subject.getSubjectName());

        return subjectDto;
    }
}
