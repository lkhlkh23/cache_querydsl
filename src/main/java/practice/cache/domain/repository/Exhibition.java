package practice.cache.domain.repository;

import lombok.Data;
import practice.cache.api.dto.ExhibitionDto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "EXHIBITION")
public class Exhibition {

    @Id
    private long exhibitionNo;
    private String title;
    private String description;
    private String theme;

    public ExhibitionDto transferDto() {
        return new ExhibitionDto(exhibitionNo, title, description);
    }

    public boolean isEmptyDescription() {
        return description.equals("");
    }
}
