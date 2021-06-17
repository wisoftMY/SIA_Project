package io.sia.aoi.controller;

import io.sia.aoi.service.AoiServiceImpl;
import io.sia.aoi.service.dto.AoiDto.intersectsAoiResponse;
import io.sia.common.Area;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AoiApiController.class)
class AoiApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AoiServiceImpl aoiService;

    List<Area> areaList = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(AoiApiController.class)
                .alwaysExpect(MockMvcResultMatchers.status().isOk())
                 .build();

        areaList.add(new Area((float) 127.02,(float) 37.742));
        areaList.add(new Area((float) 127.023,(float) 37.664));
        areaList.add(new Area((float) 126.945,(float) 37.605));
        areaList.add(new Area((float) 126.962,(float) 37.692));
        areaList.add(new Area((float) 127.02,(float) 37.742));
    }


    @Test
    @DisplayName("RegionId를 주었을 때 관심지역의 지리 정보를 반환합니다.")
    void givenRegionIdWhenGetRequestThenInterestArea() throws Exception {
        // given
        intersectsAoiResponse intersectsAoiResponse = new intersectsAoiResponse(1L,"북한산", areaList);
        List<intersectsAoiResponse> intersectsAoiResponseList = new LinkedList<>();
        intersectsAoiResponseList.add(intersectsAoiResponse);
        given(aoiService.getIntersectsAoiByRegionId(1L))
                .willReturn(intersectsAoiResponseList);

        //when
        final ResultActions actions = mockMvc.perform(get("/regions/3/aois/intersects")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name",is("북한산")))
                .andDo(print());

    }
}