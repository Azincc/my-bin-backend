package cc.azin.pastebin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@TableName("pastebin")
@AllArgsConstructor
@NoArgsConstructor
public class PasteBinPo extends BaseEntity {

  private String content;

  private LocalDateTime expiresAt;
}
