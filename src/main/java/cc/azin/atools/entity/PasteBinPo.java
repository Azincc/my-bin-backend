package cc.azin.atools.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@TableName("pastebin")
@AllArgsConstructor
@NoArgsConstructor
public class PasteBinPo extends BasePo {
  private String content;
}
