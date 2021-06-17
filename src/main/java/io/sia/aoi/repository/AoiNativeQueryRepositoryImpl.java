package io.sia.aoi.repository;

import io.sia.aoi.domain.Aoi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AoiNativeQueryRepositoryImpl implements AoiNativeQueryRepository {

    private final EntityManager em;

    @Override
    public List<Aoi> getIntersectsRegionByRegionId(final Long id) {
        return em.createNativeQuery(
                "SELECT ai.aoi_id, ai.name, ai.area as area" +
                        " FROM Aoi as ai" +
                        " INNER JOIN Region as rg ON ST_INTERSECTS(rg.area, ai.area)" +
                        " WHERE rg.region_id = :id", Aoi.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Aoi getInterestNearbyAreaByLatWithLong(final float latitude, final float longitude) {
        return (Aoi) em.createNativeQuery(
                "SELECT ai.aoi_id, ai.name, ai.area as area" +
                        " FROM Aoi as ai" +
                        " ORDER BY ST_DistanceSphere(ai.area, ST_MakePoint(:latitude,:longitude)) DESC" +
                        " LIMIT 1", Aoi.class)
                .setParameter("latitude", latitude)
                .setParameter("longitude", longitude)
                .getSingleResult();
    }


}
