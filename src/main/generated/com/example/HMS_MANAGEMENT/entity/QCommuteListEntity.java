package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommuteListEntity is a Querydsl query type for CommuteListEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommuteListEntity extends EntityPathBase<CommuteListEntity> {

    private static final long serialVersionUID = 1280336889L;

    public static final QCommuteListEntity commuteListEntity = new QCommuteListEntity("commuteListEntity");

    public final TimePath<java.time.LocalTime> afterTime = createTime("afterTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> aTime = createTime("aTime", java.time.LocalTime.class);

    public final EnumPath<com.example.HMS_MANAGEMENT.constent.CommuteStatus> commuteStatus = createEnum("commuteStatus", com.example.HMS_MANAGEMENT.constent.CommuteStatus.class);

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> morningTime = createTime("morningTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> mTime = createTime("mTime", java.time.LocalTime.class);

    public final StringPath name = createString("name");

    public final TimePath<java.time.LocalTime> resultTime = createTime("resultTime", java.time.LocalTime.class);

    public final TimePath<java.time.LocalTime> time = createTime("time", java.time.LocalTime.class);

    public QCommuteListEntity(String variable) {
        super(CommuteListEntity.class, forVariable(variable));
    }

    public QCommuteListEntity(Path<? extends CommuteListEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommuteListEntity(PathMetadata metadata) {
        super(CommuteListEntity.class, metadata);
    }

}

