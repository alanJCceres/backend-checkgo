package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.Plan;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.mapper.UserMapper;
import com.acmsoft.checkgo.repository.UserRepository;
import com.acmsoft.checkgo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PlanService planService;
    private final UserMapper userMapper;
    public User saveUser(UserCreateRequestDTO userRequest){
        Plan plan = planService.getPlan(userRequest.getPlanPublicId());
        return userRepository.save(userMapper.toUser(userRequest, plan));
    }
}
