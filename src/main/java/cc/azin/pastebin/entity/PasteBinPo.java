package cc.azin.pastebin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("pastebin")
public class PasteBinPo {
  @TableId private String id;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;
  private Boolean markForDelete;
}
