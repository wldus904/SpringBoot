package com.infrun.myrestfulservice.study.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudentRequests is a Querydsl query type for StudentRequests
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudentRequests extends EntityPathBase<StudentRequests> {

    private static final long serialVersionUID = 815844416L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudentRequests studentRequests = new QStudentRequests("studentRequests");

    public final QBoard board;

    public final NumberPath<Integer> boardStudentRequestsId = createNumber("boardStudentRequestsId", Integer.class);

    public final NumberPath<Integer> requestsType = createNumber("requestsType", Integer.class);

    public QStudentRequests(String variable) {
        this(StudentRequests.class, forVariable(variable), INITS);
    }

    public QStudentRequests(Path<? extends StudentRequests> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudentRequests(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudentRequests(PathMetadata metadata, PathInits inits) {
        this(StudentRequests.class, metadata, inits);
    }

    public QStudentRequests(Class<? extends StudentRequests> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

