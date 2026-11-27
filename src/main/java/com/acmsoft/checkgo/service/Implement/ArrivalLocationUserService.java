package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.response.UserResponseDTO;
import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationUser;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.exception.ResourceNotFoundException;
import com.acmsoft.checkgo.mapper.UserMapper;
import com.acmsoft.checkgo.repository.ArrivalLocationUserRepository;
import com.acmsoft.checkgo.service.IArrivalLocationUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArrivalLocationUserService implements IArrivalLocationUserService {
    private final ArrivalLocationUserRepository arrivalLocationUserRepository;
    private final UserMapper userMapper;

    @Transactional
    public void saveArrivalLocationUsers(List<User> users, ArrivalLocation arrivalLocation){
        List<ArrivalLocationUser> assignments = users.stream().map(u -> {
            ArrivalLocationUser alu = new ArrivalLocationUser();
            alu.setUser(u);
            alu.setArrivalLocation(arrivalLocation);
            return alu;
        }).toList();
        arrivalLocationUserRepository.saveAll(assignments);
    }

    public List<ArrivalLocation> getAllArrivalLocationForUser(UUID publicIdUser){
        return arrivalLocationUserRepository.findAllArrivalLocationsByUserPublicId(publicIdUser);
    }

    public List<ArrivalLocationUser> getAllArrivalLocationUser(List<ArrivalLocation> locations){
        return arrivalLocationUserRepository.findByArrivalLocationIn(locations);
    }

    public Map<UUID, List<UserResponseDTO>> usersByLocation(List<ArrivalLocation> locations){
        List<ArrivalLocationUser> relations = getAllArrivalLocationUser(locations);

        return relations.stream()
                .collect(Collectors.groupingBy(
                        relation -> relation.getArrivalLocation().getPublicId(),
                        Collectors.mapping(
                                relation -> userMapper.toResponse(relation.getUser()),
                                Collectors.toList()
                        )
                ));
    }
}
