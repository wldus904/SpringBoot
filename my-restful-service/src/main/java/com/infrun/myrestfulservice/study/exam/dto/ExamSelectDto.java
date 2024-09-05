package com.infrun.myrestfulservice.study.exam.dto;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.Id;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExamSelectDto {
    @Id
    private Integer examId;
    private Integer examSubjectId;
    private Integer examScoreId;
    private int year;
    private int grade;
    private int classSection;
    private int semester;
    private String title;
    private String subjectCode;
    private String subjectName;
    private int score;
    private String memberId;
    private String name;
}
