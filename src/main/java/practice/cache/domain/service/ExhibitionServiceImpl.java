package practice.cache.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import practice.cache.api.dto.ExhibitionDto;
import practice.cache.domain.repository.Exhibition;
import practice.cache.domain.repository.ExhibitionJpaRepository;
import practice.cache.domain.repository.ExhibitionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;
    private final ExhibitionJpaRepository exhibitionJpaRepository;

    private static final int WAIT_MIN = 1000 * 60;
    private static final String DEFAULT_THEME = "DEFAULT";

    @Override
    @Cacheable(cacheNames = "getExhibitionWithTheme", key="{#p0}")
    public List<ExhibitionDto> getExhibitionWithTheme(String theme) throws InterruptedException {
        Thread.sleep(WAIT_MIN); // cache hit 검증을 위한 SLEEP

        return exhibitionRepository.getExhibitionWithTheme(theme)
                                   .stream()
                                   .map(e -> e.transferDto())
                                   .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void criteriaUpdate() {
        exhibitionRepository.initDefaultThemeWhenNoDescription();
    }

    @Override
    @Transactional
    public void jpaUpdate() {
        List<Exhibition> exhibitionsWithoutDescription = exhibitionJpaRepository.findAll();
        for (Exhibition exhibition : exhibitionsWithoutDescription) {
            if(exhibition.isEmptyDescription()) {
                exhibition.setTheme(DEFAULT_THEME);
            }
        }
    }

    @Override
    @Transactional
    public void jpqlUpdate() {
        exhibitionJpaRepository.initDefaultThemeWhenNoDescription(DEFAULT_THEME);
    }

}
