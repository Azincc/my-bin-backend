package cc.azin.atools.domain.network.convertor;

import cc.azin.atools.domain.network.po.IpInformationPo;
import cc.azin.atools.domain.network.vo.IpInformationVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author azin
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IPInfoConvertor {
    IPInfoConvertor INSTANCE = Mappers.getMapper(IPInfoConvertor.class);

    @Mapping(target = "ip",ignore = true)
    IpInformationVo convert(IpInformationPo ipInformationPo);

    IpInformationPo convert(IpInformationVo ipInformationVo);
}
