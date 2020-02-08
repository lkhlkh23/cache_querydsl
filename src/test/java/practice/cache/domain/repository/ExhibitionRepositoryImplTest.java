package practice.cache.domain.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExhibitionRepositoryImplTest {

    @Autowired
    private ExhibitionRepository exhibitionRepository;

    @Autowired
    private ExhibitionJpaRepository exhibitionJpaRepository;

    @Test
    public void test_getExhibitionWithTheme_returnTwoExhibitionWhenPassingFoodTheme() {
        final String theme = "food";
        List<Exhibition> exhibitionsWithThemes = exhibitionRepository.getExhibitionWithTheme(theme);

        assertEquals(exhibitionsWithThemes.size(), 2);
        assertEquals(exhibitionsWithThemes.get(0).getTheme(), theme);

        log.info("Exhibition : {}", exhibitionsWithThemes.toString());
    }

    @Test
    public void test_initDefaultThemeWhenNoDescription_successWhenExistNullDescription() {
        int countOfUpdated = exhibitionRepository.initDefaultThemeWhenNoDescription();

        assertEquals(countOfUpdated, 3);
    }
}