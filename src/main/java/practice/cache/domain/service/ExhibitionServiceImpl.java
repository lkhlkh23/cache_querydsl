package practice.cache.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import practice.cache.api.dto.ExhibitionDto;
import practice.cache.domain.repository.ExhibitionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionRepository exhibitionRepository;

    private static final int WAIT_MIN = 1000 * 60;

    @Override
    @Cacheable(cacheNames = "getExhibitionWithTheme", key="{#p0}")
    public List<ExhibitionDto> getExhibitionWithTheme(String theme) throws InterruptedException {
        Thread.sleep(WAIT_MIN); // cache hit 검증을 위한 SLEEP

        return exhibitionRepository.getExhibitionWithTheme(theme)
                                   .stream()
                                   .map(e -> e.transferDto())
                                   .collect(Collectors.toList());
    }

}
