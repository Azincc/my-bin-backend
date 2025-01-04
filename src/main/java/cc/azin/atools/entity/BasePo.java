package cc.azin.atools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BasePo {
  @TableId(type = IdType.ASSIGN_UUID)
  private String id;

  private LocalDateTime expiresAt;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
  private String createdBy;
  private Boolean deleted;
}
