package practice.cache.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExhibitionRepositoryImpl implements ExhibitionRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Exhibition> getExhibitionWithTheme(String theme) {
        QExhibition qExhibition = QExhibition.exhibition;
        return jpaQueryFactory.select(qExhibition)
                              .from(qExhibition)
                              .where(qExhibition.theme.eq(theme))
                              .fetch()
                              .stream()
                              .collect(Collectors.toList());
    }

}
