package demo.securityapp.services;

import demo.securityapp.dto.request.CreateRegistroCarroDTO;
import demo.securityapp.models.ParkingRegistros;
import demo.securityapp.repository.ParkingRegistrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingRegisterServices {

    @Autowired
    private ParkingRegistrosRepository parkingRegistrosRepository;

    public List<ParkingRegistros> getAllParkingRegisters(){
        return parkingRegistrosRepository.findAll();
    }

    public void saveParkingRegister(ParkingRegistros parkingRegistros){
        parkingRegistrosRepository.save(parkingRegistros);
    }

    public CreateRegistroCarroDTO getParkingRegisterByPlaca(String placa){
        ParkingRegistros registro = parkingRegistrosRepository.findByPlaca(placa).orElse(null);

        return new CreateRegistroCarroDTO(registro.getPlaca(), registro.getFechaEntrada(), registro.getFechaSalida(), registro.getUbicacion(), registro.getTypeCarEntity());
    }

    public CreateRegistroCarroDTO saveParkingRegister(CreateRegistroCarroDTO createRegistroCarroDTO){
        ParkingRegistros parkingRegistros = ParkingRegistros.builder()
                .placa(createRegistroCarroDTO.getPlaca())
                .fechaEntrada(createRegistroCarroDTO.getFechaEntrada())
                .fechaSalida(createRegistroCarroDTO.getFechaSalida())
                .build();
        return createRegistroCarroDTO;
    }

    public ParkingRegistros updateParkingRegisterAll(String placa, CreateRegistroCarroDTO createRegistroCarroDTO){
        ParkingRegistros registro = parkingRegistrosRepository.findByPlaca(placa).orElse(null);

        registro.setPlaca(createRegistroCarroDTO.getPlaca());
        registro.setFechaEntrada(createRegistroCarroDTO.getFechaEntrada());
        registro.setFechaSalida(createRegistroCarroDTO.getFechaSalida());
        registro.setUbicacion(createRegistroCarroDTO.getUbicacion());

        return parkingRegistrosRepository.save(registro);
    }

    public ParkingRegistros updateParkingRegisterUbicacion(String placa, String ubicacion){
        ParkingRegistros registro = parkingRegistrosRepository.findByPlaca(placa).orElse(null);
        registro.setUbicacion(ubicacion);
        return parkingRegistrosRepository.save(registro);
    }

    public void deleteParkingRegister(String placa){
        parkingRegistrosRepository.findByPlaca(placa).ifPresent(carRegistro -> parkingRegistrosRepository.delete(carRegistro));
    }


}
