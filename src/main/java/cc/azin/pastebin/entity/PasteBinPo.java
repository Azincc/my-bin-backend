package cc.azin.pastebin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@TableName("pastebin")
public class PasteBinPo extends BaseEntity {
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;
}
