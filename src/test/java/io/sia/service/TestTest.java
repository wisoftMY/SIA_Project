package io.sia.service;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//import static io.sia.service.Test.wktToGeometry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
class TestTest {

    @Test
    public void shouldConvertWktToGeometry() throws ParseException {

//        Geometry geometry = wktToGeometry("POINT (2 5)");

//        assertEquals("Point", geometry.getGeometryType());
//        assertTrue(geometry instanceof Point);
    }

    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    @DisplayName("")
    void givenWhenThen() {
        // given

        // when

        // then
    }

}