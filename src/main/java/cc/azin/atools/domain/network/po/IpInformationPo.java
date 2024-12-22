package cc.azin.atools.domain.network.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author azin
 */
@TableName("ip_information_t")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IpInformationPo {
    @TableId
    private Integer id;
    private long startIp;
    private long endIp;
    private String location;
    private String isp;
}
