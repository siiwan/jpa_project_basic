package hellojpa;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;

    private LocalDateTime createdDate;

    private String lastModifiedBy;

    private LocalDateTime lastModifiedDate;

    public void updateBaseEntity(String createdBy, LocalDateTime createdDate, String lastModifiedBy, LocalDateTime lastModifiedDate) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }
}
