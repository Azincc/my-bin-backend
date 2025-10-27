package cc.azin.atools.facade;

import cc.azin.atools.config.SecurityConfig;
import cc.azin.atools.service.PasteBinService;
import cc.azin.atools.vo.CreatePasteBinReq;
import cc.azin.atools.vo.CreatePasteBinResp;
import cc.azin.atools.vo.QueryPasteBinResp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PasteBinFacade.class)
@Import(SecurityConfig.class)
class PasteBinFacadeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasteBinService pasteBinService;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "USER")
    void createPasteBin() throws Exception {
        CreatePasteBinReq req = new CreatePasteBinReq();
        req.setContent("test content");

        when(pasteBinService.createBin(any(CreatePasteBinReq.class)))
                .thenReturn(CreatePasteBinResp.builder().id("test-id").build());

        mockMvc.perform(post("/v1/bin/create")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void getPasteBin() throws Exception {
        String binId = "test-bin-id";
        when(pasteBinService.getBin(binId)).thenReturn(QueryPasteBinResp.builder().content("test content").build());

        mockMvc.perform(get("/v1/bin/get/{bin_id}", binId))
                .andExpect(status().isOk());
    }

    @Test
    void createPasteBin_unauthorized() throws Exception {
        CreatePasteBinReq req = new CreatePasteBinReq();
        req.setContent("test content");

        mockMvc.perform(post("/v1/bin/create")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "GUEST")
    void getPasteBin_unauthorized() throws Exception {
        String binId = "test-bin-id";

        mockMvc.perform(get("/v1/bin/get/{bin_id}", binId))
                .andExpect(status().isForbidden());
    }
}
