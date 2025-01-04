package cc.azin.atools.facade;

import cc.azin.atools.domain.network.convertor.IPInfoConvertor;
import cc.azin.atools.domain.network.po.IpInformationPo;
import cc.azin.atools.domain.network.service.IPLocationService;
import cc.azin.atools.domain.network.vo.IpInformationVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author azin
 */
@RestController
@RequestMapping("/v1/public/ip")
@Slf4j
public class IPFacade {
    @Resource
    private IPLocationService ipLocationService;

    @PostMapping(value = "/get/myLocation")
    public IpInformationVo getLocation(HttpServletRequest request) {
        String ip = ipLocationService.getAddress(request);
        return ipLocationService.getLocation(ip);
    }

    @PostMapping(value = "/get/ipLocation")
    public IpInformationVo getLocationByIp(@RequestBody String ip) {
        InetAddress address;
        try {
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return IpInformationVo.ERROR;
        }
        if (address instanceof Inet4Address) {
            Optional<IpInformationPo> ipInformationPo = ipLocationService.getIpInformationPo((Inet4Address)address);
            if (ipInformationPo.isPresent()) {
                return IPInfoConvertor.INSTANCE.convert(ipInformationPo.get());
            }
        } else if (address instanceof Inet6Address) {
            return IpInformationVo.NOT_SUPPORTED;
        }
        return ipLocationService.getLocation(ip);
    }
}
