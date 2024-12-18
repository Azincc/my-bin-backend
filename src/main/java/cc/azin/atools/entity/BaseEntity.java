package cc.azin.atools.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
  public String id;
  public String deleted;
  public LocalDateTime createdTime;
  public LocalDateTime updatedTime;
  public String createdBy;
}
