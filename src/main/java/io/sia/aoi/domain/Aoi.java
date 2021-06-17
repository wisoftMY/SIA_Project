package io.sia.aoi.domain;

import io.sia.common.Area;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

import static io.sia.aoi.service.dto.AoiDto.*;

@Entity
@Table(name = "aoi")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aoi_id")
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon area;

    public static Aoi createAoi(final AoiRegisterRequest regionDto, final Polygon polygon) {
        Aoi aoi = new Aoi();
        aoi.setName(regionDto.getName());
        aoi.setArea(polygon);

        return aoi;
    }

    public static List<intersectsAoiResponse> mergeAoisWithAreas(final List<Aoi> aois, final List<List<Area>> areasList) {
        List<intersectsAoiResponse> intersectsAoiResponseList = new LinkedList<>();

        int index = 0;

        for (Aoi aoi : aois) {
            List<Area> areas = new LinkedList<>();
            for (int i = 0; i < areasList.get(index).size(); i++) {
                areas.add(new Area(areasList.get(index).get(i).getX(), areasList.get(index).get(i).getY()));
            }
            intersectsAoiResponseList.add(
                    new intersectsAoiResponse(
                            aoi.getId(),
                            aoi.getName(),
                            areas));
            index++;
        }

        return intersectsAoiResponseList;
    }
}
