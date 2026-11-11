package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.entity.ArrivalLocation;
import com.acmsoft.checkgo.entity.ArrivalLocationUser;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.repository.ArrivalLocationUserRepository;
import com.acmsoft.checkgo.service.IArrivalLocationUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArrivalLocationUserService implements IArrivalLocationUserService {
    private final ArrivalLocationUserRepository arrivalLocationUserRepository;

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
}
