package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.ArrivalLocationRequestDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationSchedule;
import com.acmsoft.checkgo.entity.ArrivalLocationUser;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.exception.BadRequestException;
import com.acmsoft.checkgo.exception.BusinessRuleException;
import com.acmsoft.checkgo.mapper.ArrivalLocationMapper;
import com.acmsoft.checkgo.repository.ArrivalLocationRepository;
import com.acmsoft.checkgo.repository.ArrivalLocationScheduleRepository;
import com.acmsoft.checkgo.repository.ArrivalLocationUserRepository;
import com.acmsoft.checkgo.repository.UserRepository;
import com.acmsoft.checkgo.security.CustomUserDetails;
import com.acmsoft.checkgo.service.IArrivalLocationService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArrivalLocationService implements IArrivalLocationService {
    private final ArrivalLocationRepository arrivalLocationRepository;
    private final ArrivalLocationScheduleRepository arrivalLocationScheduleRepository;
    private final ArrivalLocationUserRepository arrivalLocationUserRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ArrivalLocationMapper arrivalLocationMapper;
    private final GeometryFactory geometryFactory;

    public ArrivalLocation saveArrivalLocation(ArrivalLocationRequestDTO arrivalLocationRequestDTO, CustomUserDetails userDetails){

        if(userDetails == null){
            throw new BadRequestException("No se puede listar los usuarios sin un access token o con un token expirado/invalido.");
        }

        ArrivalLocation arrivalLocation = arrivalLocationMapper.toArrivalLocation(arrivalLocationRequestDTO);
        UUID publicIdAdmin = userDetails.getPublicId();
        User superAdmin = userService.findUserByPublicId(publicIdAdmin);
        arrivalLocation.setCreatedBy(superAdmin);
        double lat = arrivalLocationRequestDTO.getLatitude();
        double lng = arrivalLocationRequestDTO.getLongitude();
        arrivalLocation.setLocation(geometryFactory.createPoint(new Coordinate(lng,lat)));

        //Guardamos los horarios seleccionados
        List<ArrivalLocationSchedule> schedules = arrivalLocationScheduleRepository.findAllByPublicIdIn(toUuids(arrivalLocationRequestDTO.getLocationSchedules()));
        if (schedules.size() != arrivalLocationRequestDTO.getLocationSchedules().size()) {
            throw new IllegalArgumentException("Algún horario no existe");
        }
        arrivalLocation.setArrivalLocationSchedules(new HashSet<>(schedules));

        //Guardamos el objeto arrivalLocation para usar su id en la tabla de users manyToMany
        ArrivalLocation saved = arrivalLocationRepository.save(arrivalLocation);

        //Obtenemos los usuarios, verificamos, relacionamos y posteriormente guardamos todos los datos en la tabla manyToMany
        List<UUID> userIds = toUuids(arrivalLocationRequestDTO.getAssignedUsers()).stream().distinct().toList();
        List<User> users = userRepository.findAllByPublicIdsAndAdmin(userIds, publicIdAdmin);
        if (users.size() != userIds.size()) {
            throw new BusinessRuleException("Uno o más usuarios no existen o no te pertenecen");
        }
        List<ArrivalLocationUser> assignments = users.stream().map(u -> {
            ArrivalLocationUser alu = new ArrivalLocationUser();
            alu.setUser(u);
            alu.setArrivalLocation(saved);
            return alu;
        }).toList();
        arrivalLocationUserRepository.saveAll(assignments);

        return saved;
    }

    private List<UUID> toUuids(List<String> ids) {
        try {
            return ids.stream()
                    .map(UUID::fromString)
                    .toList();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Uno de los identificadores enviados no es válido");
        }
    }
}
