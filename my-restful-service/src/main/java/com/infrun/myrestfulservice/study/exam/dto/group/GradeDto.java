package com.infrun.myrestfulservice.study.exam.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GradeDto {
    private int grade;
    private List<ClassSectionDto> classSections;
}
