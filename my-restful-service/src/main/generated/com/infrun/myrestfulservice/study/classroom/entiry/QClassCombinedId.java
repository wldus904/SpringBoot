package com.infrun.myrestfulservice.study.classroom.entiry;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClassCombinedId is a Querydsl query type for ClassCombinedId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QClassCombinedId extends BeanPath<ClassCombinedId> {

    private static final long serialVersionUID = 1103312750L;

    public static final QClassCombinedId classCombinedId = new QClassCombinedId("classCombinedId");

    public final NumberPath<Integer> classroomId = createNumber("classroomId", Integer.class);

    public final StringPath memberId = createString("memberId");

    public QClassCombinedId(String variable) {
        super(ClassCombinedId.class, forVariable(variable));
    }

    public QClassCombinedId(Path<? extends ClassCombinedId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassCombinedId(PathMetadata metadata) {
        super(ClassCombinedId.class, metadata);
    }

}

