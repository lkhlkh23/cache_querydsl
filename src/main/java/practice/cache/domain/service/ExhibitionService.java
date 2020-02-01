package practice.cache.domain.service;

import practice.cache.api.dto.ExhibitionDto;

import java.util.List;

public interface ExhibitionService {

    List<ExhibitionDto> getExhibitionWithTheme(String theme) throws InterruptedException;

}
