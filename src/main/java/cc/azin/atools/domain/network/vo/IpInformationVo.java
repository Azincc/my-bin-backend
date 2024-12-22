package cc.azin.atools.domain.network.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IpInformationVo {
    public static final IpInformationVo ERROR = IpInformationVo.builder().location("信息错误").build();
    public static final IpInformationVo NOT_FOUND = IpInformationVo.builder().location("未找到").build();
    public static final IpInformationVo NOT_SUPPORTED = IpInformationVo.builder().location("暂不支持").build();
    private String location;
    private String isp;
    private String ip;
}
