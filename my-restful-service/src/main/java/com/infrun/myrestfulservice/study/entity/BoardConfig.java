package com.infrun.myrestfulservice.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board_config")
public class BoardConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_config_id")
    private Integer boardConfigId;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private int type;

    @OneToMany(mappedBy = "boardConfig")
    private List<Board> boards;
}
