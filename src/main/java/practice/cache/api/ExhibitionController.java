package practice.cache.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.cache.api.dto.ExhibitionDto;
import practice.cache.domain.service.ExhibitionService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/plan/v1")
public class ExhibitionController {

    @Autowired
    private ExhibitionService exhibitionService;

    @GetMapping("/exhibitions/{theme}")
    public List<ExhibitionDto> getExhibitionTitle(@PathVariable @NotNull String theme) throws InterruptedException {
        return exhibitionService.getExhibitionWithTheme(theme);
    }


}
