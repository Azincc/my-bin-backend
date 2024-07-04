package cc.azin.pastebin.service;

import cc.azin.pastebin.entity.PasteBinPo;
import cc.azin.pastebin.repo.PasteBinRepo;
import cc.azin.pastebin.vo.CreatePasteBinReq;
import cc.azin.pastebin.vo.CreatePasteBinResp;
import cc.azin.pastebin.vo.QueryPasteBinResp;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PasteBinService {

  @Resource private PasteBinRepo pasteBinRepo;

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