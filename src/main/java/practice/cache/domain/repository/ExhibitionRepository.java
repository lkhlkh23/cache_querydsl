package practice.cache.domain.repository;

import java.util.List;

public interface ExhibitionRepository {

    List<Exhibition> getExhibitionWithTheme(String theme);

    int initDefaultThemeWhenNoDescription();

}
