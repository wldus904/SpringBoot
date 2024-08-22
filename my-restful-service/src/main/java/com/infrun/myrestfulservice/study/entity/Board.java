package com.infrun.myrestfulservice.study.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class Board {

//    board_config_id = 0 인것만 가져오기
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Integer boardId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer_member_id")
    private String writerMemberId;

    @Column(name = "status")
    private int status;

    @Column(name = "ref_id")
    private String refId;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @ManyToOne
    @JoinColumn(name = "board_config_id", nullable = false)
    private BoardConfig boardConfig;

    @PrePersist
    protected void onCreated() {
        regDate = LocalDateTime.now();
        modDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modDate = LocalDateTime.now();
    }

    @Builder
    public Board(String title, String content, String writerMemberId, int status, String refId) {
        this.title = title;
        this.content = content;
        this.writerMemberId = writerMemberId;
        this.status = status;
        this.refId = refId;
    }
}
