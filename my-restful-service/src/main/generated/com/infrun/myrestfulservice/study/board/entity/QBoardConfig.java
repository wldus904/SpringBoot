package com.infrun.myrestfulservice.study.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardConfig is a Querydsl query type for BoardConfig
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardConfig extends EntityPathBase<BoardConfig> {

    private static final long serialVersionUID = -941571223L;

    public static final QBoardConfig boardConfig = new QBoardConfig("boardConfig");

    public final NumberPath<Integer> boardConfigId = createNumber("boardConfigId", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QBoardConfig(String variable) {
        super(BoardConfig.class, forVariable(variable));
    }

    public QBoardConfig(Path<? extends BoardConfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardConfig(PathMetadata metadata) {
        super(BoardConfig.class, metadata);
    }

}

