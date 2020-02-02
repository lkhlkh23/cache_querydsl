package practice.cache.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionDto {

    private long exhibitionNo;
    private String title;
    private String description;

}
