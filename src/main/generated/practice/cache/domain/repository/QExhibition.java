package practice.cache.domain.repository;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QExhibition is a Querydsl query type for Exhibition
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QExhibition extends EntityPathBase<Exhibition> {

    private static final long serialVersionUID = 89263592L;

    public static final QExhibition exhibition = new QExhibition("exhibition");

    public final StringPath description = createString("description");

    public final NumberPath<Long> exhibitionNo = createNumber("exhibitionNo", Long.class);

    public final StringPath theme = createString("theme");

    public final StringPath title = createString("title");

    public QExhibition(String variable) {
        super(Exhibition.class, forVariable(variable));
    }

    public QExhibition(Path<? extends Exhibition> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExhibition(PathMetadata metadata) {
        super(Exhibition.class, metadata);
    }

}

