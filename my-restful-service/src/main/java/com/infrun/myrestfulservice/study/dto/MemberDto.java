package com.infrun.myrestfulservice.study.dto;

import com.infrun.myrestfulservice.study.util.RegexType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberDto {
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = RegexType.PASSWORD, message = "비밀번호는 문자, 숫자, 특수 문자 1개 이상 포함하여 최소 8자 이상 입력해주세요")
    private String password;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "주소를 입력해주세요")
    private String address;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바르지 않은 이메일 형식입니다")
    private String email;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    private String phone;
}
