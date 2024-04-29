package com.example.HMS_MANAGEMENT.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailEntity is a Querydsl query type for EmailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmailEntity extends EntityPathBase<EmailEntity> {

    private static final long serialVersionUID = -1309472707L;

    public static final QEmailEntity emailEntity = new QEmailEntity("emailEntity");

    public final StringPath basicSal = createString("basicSal");

    public final StringPath designerEmail = createString("designerEmail");

    public final StringPath designerName = createString("designerName");

    public final StringPath emailContent = createString("emailContent");

    public final StringPath employmentInsurance = createString("employmentInsurance");

    public final StringPath healthInsurance = createString("healthInsurance");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath incomeTax = createString("incomeTax");

    public final StringPath mealAllowance = createString("mealAllowance");

    public final StringPath netSalary = createString("netSalary");

    public final StringPath overtimeAllowance = createString("overtimeAllowance");

    public final StringPath pension = createString("pension");

    public QEmailEntity(String variable) {
        super(EmailEntity.class, forVariable(variable));
    }

    public QEmailEntity(Path<? extends EmailEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailEntity(PathMetadata metadata) {
        super(EmailEntity.class, metadata);
    }

}

