package practice.cache.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ExhibitionJpaRepository extends JpaRepository<Exhibition, Long> {

    @Modifying
    @Query("UPDATE Exhibition e set e.theme = :theme where e.description = ''")
    void initDefaultThemeWhenNoDescription(@Param("theme") String theme);

}
