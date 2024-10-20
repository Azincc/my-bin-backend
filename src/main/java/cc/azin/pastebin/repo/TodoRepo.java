package cc.azin.pastebin.repo;

import cc.azin.pastebin.entity.TodoPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoRepo extends BaseMapper<TodoPo> {}
