package io.sia.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

import java.util.LinkedList;
import java.util.List;

@Getter

@NoArgsConstructor
@AllArgsConstructor
public class Area {

    private float x;
    private float y;

    // 생성 메서드
    public static Polygon createPolygon(final List<Area> areaDto) {
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        List<Coordinate> coordinates = new LinkedList<>();

        for(Area area : areaDto) {
            Coordinate coordinate = new Coordinate(area.getX(),area.getY());
            coordinates.add(coordinate);
        }

        Polygon polygon = factory.createPolygon(coordinates.toArray(Coordinate[]::new));

        return polygon;
    }

    public static List<List<Area>> createAreaFromPolygon(final List<Polygon> areaDto) throws ParseException {
        final List<List<Area>> areasList = new LinkedList<>();
        final WKTWriter wktWriter = new WKTWriter();
        final WKTReader reader = new WKTReader();

        for(Polygon polygon :areaDto) {
            List<Area> areas = new LinkedList<>();
            String wktstr = wktWriter.write(polygon);
            Geometry geometryFromWkt = reader.read(wktstr);
            Coordinate[] coordinates = geometryFromWkt.getCoordinates();

            for (Coordinate coordinate : coordinates) {
                areas.add(new Area((float) coordinate.x, (float) coordinate.y));
            }

            areasList.add(areas);
        }

        return areasList;
    }

}
