package practice.cache.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExhibitionRepositoryImpl implements ExhibitionRepository {

    private static final String DEFAULT_THEME = "DEFAULT";

    private final JPAQueryFactory jpaQueryFactory;
    private final CriteriaBuilder criteriaBuilder;
    private final EntityManager entityManager;

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

    @Override
    public int initDefaultThemeWhenNoDescription() {
        CriteriaUpdate<Exhibition> update = criteriaBuilder.createCriteriaUpdate(Exhibition.class);
        Root<Exhibition> root = update.from(Exhibition.class);
        update.set(root.get("theme"), DEFAULT_THEME).where(criteriaBuilder.equal(root.get("description"), ""));
        return entityManager.createQuery(update).executeUpdate();
    }

}
