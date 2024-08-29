package com.infrun.myrestfulservice.study.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberRefreshToken is a Querydsl query type for MemberRefreshToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRefreshToken extends EntityPathBase<MemberRefreshToken> {

    private static final long serialVersionUID = -502898541L;

    public static final QMemberRefreshToken memberRefreshToken = new QMemberRefreshToken("memberRefreshToken");

    public final DateTimePath<java.time.LocalDateTime> expireDate = createDateTime("expireDate", java.time.LocalDateTime.class);

    public final StringPath memberId = createString("memberId");

    public final StringPath refreshToken = createString("refreshToken");

    public final NumberPath<Long> tokenId = createNumber("tokenId", Long.class);

    public QMemberRefreshToken(String variable) {
        super(MemberRefreshToken.class, forVariable(variable));
    }

    public QMemberRefreshToken(Path<? extends MemberRefreshToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberRefreshToken(PathMetadata metadata) {
        super(MemberRefreshToken.class, metadata);
    }

}

