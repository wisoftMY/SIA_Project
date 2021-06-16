package io.sia.region.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.*;

import javax.persistence.*;

import static io.sia.region.service.dto.RegionDto.*;

@Entity
@Table(name = "region")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "geometry(Polygon,4326)")
    private Polygon area;

    // 생성 메서드
    public static Region createRegion(final RegionRegisterRequest regionDto, final Polygon polygon) {
        Region region = new Region();
        region.setName(regionDto.getName());
        region.setArea(polygon);

        return region;
    }

}
