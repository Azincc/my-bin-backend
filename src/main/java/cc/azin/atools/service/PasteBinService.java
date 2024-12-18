package cc.azin.atools.service;

import cc.azin.atools.entity.PasteBinPo;
import cc.azin.atools.repo.PasteBinRepo;
import cc.azin.atools.vo.CreatePasteBinReq;
import cc.azin.atools.vo.CreatePasteBinResp;
import cc.azin.atools.vo.QueryPasteBinResp;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PasteBinService {

  @Resource private PasteBinRepo pasteBinRepo;

  @Resource private TodoService todoService;
  /**
   * 创建PasteBin
   *
   * @param createPasteBinReq CreatePasteBinReq
   * @return CreatePasteBinResp
   */
  public CreatePasteBinResp createBin(CreatePasteBinReq createPasteBinReq) {
    PasteBinPo po =
        PasteBinPo.builder()
            .id(RandomUtil.randomString(10))
            .content(createPasteBinReq.getContent())
            .expiresAt(createPasteBinReq.getExpireTime())
            .build();
    pasteBinRepo.insert(po);
    return CreatePasteBinResp.builder().id(po.getId()).build();
  }

  /**
   * 获取粘贴板内容
   *
   * @param binId 粘贴板Id
   * @return 粘贴内容
   */
  public QueryPasteBinResp getBin(String binId) {
    PasteBinPo po = pasteBinRepo.selectById(binId);
    return QueryPasteBinResp.builder().content(po.getContent()).build();
  }
}