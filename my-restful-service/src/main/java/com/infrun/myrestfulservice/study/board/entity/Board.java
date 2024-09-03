package com.infrun.myrestfulservice.study.board.entity;

import com.infrun.myrestfulservice.study.member.entity.Member;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Integer boardId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

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

    @OneToOne(mappedBy = "board")
    private StudentRequests studentRequests;

    @OneToOne
    @JoinColumn(name = "writer_member_id", referencedColumnName = "member_id", nullable = false)
    private Member member;

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
    public Board(BoardConfig boardConfig, String title, String content, Member member, int status, String refId) {
        this.boardConfig = boardConfig;
        this.title = title;
        this.content = content;
        this.member = member;
        this.status = status;
        this.refId = refId;
    }

    public void updateBoard(BoardConfig boardConfig, String title, String content, int status) {
        this.boardConfig = boardConfig;
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
