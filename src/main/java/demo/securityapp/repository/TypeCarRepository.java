package demo.securityapp.repository;

import demo.securityapp.models.ParkingRegistros;
import demo.securityapp.models.TypeCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCarRepository extends JpaRepository<TypeCarEntity, Long> {


}
