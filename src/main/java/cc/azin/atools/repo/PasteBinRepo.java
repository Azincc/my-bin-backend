package cc.azin.atools.repo;

import cc.azin.atools.entity.PasteBinPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PasteBinRepo extends BaseMapper<PasteBinPo> {}
