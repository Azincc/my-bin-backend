package cc.azin.atools.domain.network;

import cc.azin.atools.domain.network.po.IpInformationPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author azin
 */
@Mapper
public interface IPRepository extends BaseMapper<IpInformationPo> {
}
