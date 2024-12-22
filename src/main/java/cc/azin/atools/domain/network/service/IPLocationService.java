package cc.azin.atools.domain.network.service;

import cc.azin.atools.domain.network.IPRepository;
import cc.azin.atools.domain.network.convertor.IPInfoConvertor;
import cc.azin.atools.domain.network.po.IpInformationPo;
import cc.azin.atools.domain.network.vo.IpInformationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author azin
 */
@Service
@Slf4j
public class IPLocationService extends ServiceImpl<IPRepository, IpInformationPo> {
    private static final String RESOLVE_FAILED = "解析失败";

    public final IPInfoConvertor convertor = IPInfoConvertor.INSTANCE;

    public String getAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        String clientIp = request.getRemoteAddr();
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        } else {
            return clientIp;
        }
        return ipAddress;
    }

    public IpInformationVo getLocation(String ip) {
        InetAddress address;
        try {
            address = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            return IpInformationVo.ERROR;
        }
        if (address instanceof Inet4Address) {
            Optional<IpInformationPo> ipInformationPo = getIpInformationPo((Inet4Address)address);
            if (ipInformationPo.isPresent()) {
                IpInformationVo convert = convertor.convert(ipInformationPo.get());
                convert.setIp(ip);
                return convert;
            }
        }
        return IpInformationVo.NOT_FOUND;
    }

    public Optional<IpInformationPo> getIpInformationPo(Inet4Address address) {
        byte[] bytes = address.getAddress();
        long ipAddress = byteArrayToInt(bytes);
        log.info("Start to query ip");
        long start = System.currentTimeMillis();
        Optional<IpInformationPo> ipInformationPo =
            this.lambdaQuery().le(IpInformationPo::getStartIp, ipAddress).ge(IpInformationPo::getEndIp, ipAddress)
                .last("LIMIT 1").oneOpt();
        log.info("Query ip finished, cost {} ms", System.currentTimeMillis() - start);
        return ipInformationPo;
    }

    /**
     * 将byte数组转换为int
     *
     * @param bytes 字节数组
     * @return 整数值
     */
    private long byteArrayToInt(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException("Invalid IPv4 address length");
        }
        return ((bytes[0] & 0xFFL) << 24) | ((bytes[1] & 0xFFL) << 16) | ((bytes[2] & 0xFFL) << 8) | (bytes[3] & 0xFFL);
    }
}
