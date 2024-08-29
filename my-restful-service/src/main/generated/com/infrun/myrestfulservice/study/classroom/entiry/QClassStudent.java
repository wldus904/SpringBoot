package com.infrun.myrestfulservice.study.classroom.entiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClassStudent is a Querydsl query type for ClassStudent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassStudent extends EntityPathBase<ClassStudent> {

    private static final long serialVersionUID = 1505286285L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClassStudent classStudent = new QClassStudent("classStudent");

    public final QClassroom classroom;

    public final QClassCombinedId id;

    public final com.infrun.myrestfulservice.study.member.entity.QMember member;

    public QClassStudent(String variable) {
        this(ClassStudent.class, forVariable(variable), INITS);
    }

    public QClassStudent(Path<? extends ClassStudent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClassStudent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClassStudent(PathMetadata metadata, PathInits inits) {
        this(ClassStudent.class, metadata, inits);
    }

    public QClassStudent(Class<? extends ClassStudent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classroom = inits.isInitialized("classroom") ? new QClassroom(forProperty("classroom")) : null;
        this.id = inits.isInitialized("id") ? new QClassCombinedId(forProperty("id")) : null;
        this.member = inits.isInitialized("member") ? new com.infrun.myrestfulservice.study.member.entity.QMember(forProperty("member")) : null;
    }

}

