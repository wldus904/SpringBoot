package com.infrun.myrestfulservice.study.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentLoginDto {
    @NotBlank(message = "학생 번호를 입력해주세요.")
    private String studentId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
