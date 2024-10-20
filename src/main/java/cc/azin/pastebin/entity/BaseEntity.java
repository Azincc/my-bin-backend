package cc.azin.pastebin.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseEntity {
  public String id;
  public String deleted;
  public LocalDateTime createdTime;
  public LocalDateTime updatedTime;
  public String createdBy;
}
