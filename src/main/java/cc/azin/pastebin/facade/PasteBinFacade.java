package cc.azin.pastebin.facade;

import cc.azin.pastebin.service.PasteBinService;
import cc.azin.pastebin.vo.CreatePasteBinReq;
import cc.azin.pastebin.vo.CreatePasteBinResp;
import cc.azin.pastebin.vo.QueryPasteBinResp;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/v1/bin")
public class PasteBinFacade {
  @Resource private PasteBinService pasteBinService;

  @PostMapping(path = "/create")
  public CreatePasteBinResp createPasteBin(@NotNull @RequestBody CreatePasteBinReq createPasteBinReq) {
    return pasteBinService.createBin(createPasteBinReq);
  }

  @GetMapping(path = "/get/{bin_id}")
  public QueryPasteBinResp getPasteBin(@PathVariable("bin_id") String binId) {
    return pasteBinService.getBin(binId);
  }
}
