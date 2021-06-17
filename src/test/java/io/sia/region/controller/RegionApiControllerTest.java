package io.sia.region.controller;

import io.sia.region.service.RegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(RegionApiController.class)
class RegionApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private RegionServiceImpl regionService;

}