package cc.azin.pastebin.constant;

public enum TodoStatusEnum {
  UNFINISHED("未完成"),
  FINISHED("已完成");

  private final String status;

  TodoStatusEnum(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }
}
