package cc.azin.atools.facade;

import cc.azin.atools.service.PasteBinService;
import cc.azin.atools.vo.CreatePasteBinReq;
import cc.azin.atools.vo.CreatePasteBinResp;
import cc.azin.atools.vo.QueryPasteBinResp;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/v1/bin")
@Slf4j
public class PasteBinFacade {
  @Resource private PasteBinService pasteBinService;

  @PreAuthorize("hasAuthority('USER')")
  @PostMapping(path = "/create")
  public CreatePasteBinResp createPasteBin(
      @NotNull @RequestBody CreatePasteBinReq createPasteBinReq) {
    return pasteBinService.createBin(createPasteBinReq);
  }

  @PreAuthorize("hasAuthority('USER')")
  @GetMapping(path = "/get/{bin_id}")
  public QueryPasteBinResp getPasteBin(@PathVariable("bin_id") String binId) {
    return pasteBinService.getBin(binId);
  }
}
