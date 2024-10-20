package cc.azin.pastebin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@TableName("todo_table")
public class TodoPo extends BaseEntity {
  @Schema(description = "Todo内容")
  private String content;

  @Schema(description = "Todo状态")
  private String status;

  @Schema(description = "用户ID")
  private String userId;

  @Schema(description = "到期时间")
  private LocalDateTime dueDatetime;
}
