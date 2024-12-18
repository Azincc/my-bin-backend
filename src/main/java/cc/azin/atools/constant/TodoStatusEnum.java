package cc.azin.atools.constant;

import lombok.Getter;

@Getter
public enum TodoStatusEnum {
  UNFINISHED("未完成"),
  FINISHED("已完成");

  private final String status;

  TodoStatusEnum(String status) {
    this.status = status;
  }

}
