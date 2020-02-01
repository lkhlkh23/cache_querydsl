package practice.cache.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import practice.cache.api.dto.ExhibitionDto;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExhibitionServiceImplTest {

    @Autowired
    private ExhibitionService exhibitionService;

    @Test
    public void test_getExhibitionWithTheme_returnTwoExhibitionWhenPassingFoodTheme() throws InterruptedException {
        final String theme = "food";
        List<ExhibitionDto> exhibitionWithThemes = exhibitionService.getExhibitionWithTheme(theme);

        assertEquals(exhibitionWithThemes.size(), 2);
    }

    @Test
    public void test_getExhibitionWithTheme_cacheHitSuccessWhenSameTheme() throws InterruptedException {
        final String theme = "food";
        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            exhibitionService.getExhibitionWithTheme(theme);
        }

        long finishTime = System.currentTimeMillis();
        double takenTime = ((double)(finishTime - beginTime) / 1000) / 60;
        log.info("Apply cache hit, time : {} sec", takenTime);

        assertTrue(takenTime < 2);
    }

    @Test
    public void test_getExhibitionWithTheme_cacheHitFailWhenAnotherTheme() throws InterruptedException {
        final String theme = "food";
        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < 2; i++) {
            exhibitionService.getExhibitionWithTheme(theme + i);
        }

        long finishTime = System.currentTimeMillis();
        double takenTime = ((double)(finishTime - beginTime) / 1000) / 60;
        log.info("Apply cache hit, time : {} sec", takenTime);

        assertTrue(takenTime > 2);
    }
}