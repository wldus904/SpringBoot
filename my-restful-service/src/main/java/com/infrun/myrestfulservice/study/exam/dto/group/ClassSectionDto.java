package com.infrun.myrestfulservice.study.exam.dto.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassSectionDto {
    private int classSection;
    private List<MemberDto> members;
}
