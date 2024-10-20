package cc.azin.pastebin.facade;

import cc.azin.pastebin.service.TodoService;
import cc.azin.pastebin.vo.CreateTodoReq;
import cc.azin.pastebin.vo.CreateTodoResp;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/todo")
public class TodoFacade {

  @Resource private TodoService todoService;

  /**
   * 创建一个todo
   *
   * @param createTodoReq 创建todo的请求
   * @return Todo的所有信息
   */
  @PostMapping(path = "/create")
  public CreateTodoResp createTodo(@NotNull @RequestBody CreateTodoReq createTodoReq) {
    return todoService.createTodo(createTodoReq);
  }
}
