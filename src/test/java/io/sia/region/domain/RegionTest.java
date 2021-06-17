package io.sia.region.domain;

import io.sia.common.Area;
import io.sia.region.repository.RegionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegionTest {

    @Autowired
    private RegionRepository regionRepository;

    private Region findRegion;
    List<Area> areaList = new LinkedList<>();

    @BeforeAll
    void setUp(){
        areaList.add(new Area((float) 126.835,(float) 37.688));
        areaList.add(new Area((float) 127.155,(float) 37.702));
        areaList.add(new Area((float) 127.184,(float) 37.474));
        areaList.add(new Area((float) 126.821,(float) 37.454));
        areaList.add(new Area((float) 126.835,(float) 37.688));

        Polygon polygon = Area.createPolygon(areaList);
        Region region = Region.createRegion("서울시1", polygon);

        findRegion = regionRepository.save(region);
    }

    @Test
    @DisplayName("Region을 저장하였을 때 저장이 되었는지 확인합니다.")
    void given_Region_When_RegionSaveOne_Then_ReturnValueWillOne() {
        // given
        // when

        // then
        assertNotNull(findRegion);
        assertThat(findRegion.getId()).isNotNull();
    }


}