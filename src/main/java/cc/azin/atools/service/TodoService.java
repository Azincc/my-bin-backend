package cc.azin.atools.service;

import static cc.azin.atools.constant.TodoStatusEnum.UNFINISHED;

import cc.azin.atools.entity.TodoPo;
import cc.azin.atools.repo.TodoRepo;
import cc.azin.atools.vo.CreateTodoReq;
import cc.azin.atools.vo.CreateTodoResp;
import cn.hutool.core.util.IdUtil;
import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/** todoService */
@Service
public class TodoService {
  @Resource private TodoRepo todoRepo;

  @Resource private ConversionService conversionService;

  /**
   * 创建一个todo
   *
   * @param createTodoReq CreateTodoReq
   * @return CreateTodoResp
   */
  public CreateTodoResp createTodo(CreateTodoReq createTodoReq) {
    TodoPo todoPo =
        TodoPo.builder()
            .id(IdUtil.randomUUID())
            .createdTime(LocalDateTime.now())
            .updatedTime(LocalDateTime.now())
            .content(createTodoReq.getContent())
            .status(UNFINISHED.getStatus())
            .build();
    todoRepo.insertOrUpdate(todoPo);
    return conversionService.convert(todoPo, CreateTodoResp.class);
  }
}
