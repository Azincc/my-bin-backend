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

  public QueryPasteBinResp getBin(String binId) {
    PasteBinPo po = pasteBinRepo.selectById(binId);
    return QueryPasteBinResp.builder().content(po.getContent()).build();
  }
}
