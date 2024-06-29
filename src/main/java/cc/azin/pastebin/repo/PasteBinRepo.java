package cc.azin.pastebin.repo;

import cc.azin.pastebin.entity.PasteBinPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasteBinRepo extends BaseMapper<PasteBinPo> {}
