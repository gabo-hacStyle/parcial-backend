package demo.securityapp.repository;

import demo.securityapp.models.ParkingRegistros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRegistrosRepository extends JpaRepository<ParkingRegistros, Long> {

    <Optional> ParkingRegistros findByPlaca(String placa);


}
