package cc.azin.atools.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreatePasteBinReq {
  private String content;
  private LocalDateTime expireTime;
}
