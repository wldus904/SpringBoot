package com.infrun.myrestfulservice.study.classroom.entiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClassStaff is a Querydsl query type for ClassStaff
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassStaff extends EntityPathBase<ClassStaff> {

    private static final long serialVersionUID = -114653774L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClassStaff classStaff = new QClassStaff("classStaff");

    public final QClassroom classroom;

    public final QClassCombinedId id;

    public final com.infrun.myrestfulservice.study.member.entity.QMember member;

    public final StringPath subjectCode = createString("subjectCode");

    public QClassStaff(String variable) {
        this(ClassStaff.class, forVariable(variable), INITS);
    }

    public QClassStaff(Path<? extends ClassStaff> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClassStaff(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClassStaff(PathMetadata metadata, PathInits inits) {
        this(ClassStaff.class, metadata, inits);
    }

    public QClassStaff(Class<? extends ClassStaff> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classroom = inits.isInitialized("classroom") ? new QClassroom(forProperty("classroom")) : null;
        this.id = inits.isInitialized("id") ? new QClassCombinedId(forProperty("id")) : null;
        this.member = inits.isInitialized("member") ? new com.infrun.myrestfulservice.study.member.entity.QMember(forProperty("member")) : null;
    }

}

