package com.infrun.myrestfulservice.study.classroom.entiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClassroom is a Querydsl query type for Classroom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassroom extends EntityPathBase<Classroom> {

    private static final long serialVersionUID = 135767977L;

    public static final QClassroom classroom = new QClassroom("classroom");

    public final NumberPath<Integer> classroomId = createNumber("classroomId", Integer.class);

    public final NumberPath<Integer> classSection = createNumber("classSection", Integer.class);

    public final ListPath<ClassStaff, QClassStaff> classStaff = this.<ClassStaff, QClassStaff>createList("classStaff", ClassStaff.class, QClassStaff.class, PathInits.DIRECT2);

    public final ListPath<ClassStudent, QClassStudent> classStudent = this.<ClassStudent, QClassStudent>createList("classStudent", ClassStudent.class, QClassStudent.class, PathInits.DIRECT2);

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final StringPath homeroomTeacher = createString("homeroomTeacher");

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QClassroom(String variable) {
        super(Classroom.class, forVariable(variable));
    }

    public QClassroom(Path<? extends Classroom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassroom(PathMetadata metadata) {
        super(Classroom.class, metadata);
    }

}

