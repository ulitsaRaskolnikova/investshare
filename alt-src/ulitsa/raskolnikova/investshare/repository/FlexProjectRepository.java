package ulitsa.raskolnikova.investshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ulitsa.raskolnikova.investshare.entity.FlexProjectEntity;

import java.util.List;

@Repository
public interface FlexProjectRepository extends JpaRepository<FlexProjectEntity, Integer> {
    List<FlexProjectEntity> findByIsPublicTrue();
}
