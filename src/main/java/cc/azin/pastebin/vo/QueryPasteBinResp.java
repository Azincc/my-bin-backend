package cc.azin.pastebin.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QueryPasteBinResp {
  private String content;
}
