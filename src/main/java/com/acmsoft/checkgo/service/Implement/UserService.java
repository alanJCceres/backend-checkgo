package com.acmsoft.checkgo.service.Implement;

import com.acmsoft.checkgo.dto.request.UserCreateRequestDTO;
import com.acmsoft.checkgo.entity.Plan;
import com.acmsoft.checkgo.entity.User;
import com.acmsoft.checkgo.enums.Rol;
import com.acmsoft.checkgo.exception.BadRequestException;
import com.acmsoft.checkgo.exception.ResourceAlreadyExistsException;
import com.acmsoft.checkgo.exception.ResourceNotFoundException;
import com.acmsoft.checkgo.mapper.UserMapper;
import com.acmsoft.checkgo.repository.UserRepository;
import com.acmsoft.checkgo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PlanService planService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public User findUserByPublicId(UUID publicId){
        return userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new ResourceNotFoundException("El User con ID " + publicId + " no existe."));
    }
    public User saveUser(UserCreateRequestDTO userRequest){
        User createdBy = null;
        Plan assignedPlan = null;

        validateUserCampos(userRequest);

        if(userRequest.getRol() == Rol.SUPER_ADMIN){
            assignedPlan = planService.findPlanByPublicId(userRequest.getPlanPublicId());
        }else {
            createdBy = findUserByPublicId(userRequest.getCreatedBy());
        }

        User newUser = userMapper.toUser(userRequest,assignedPlan,createdBy);
        newUser.setActive(true);
        newUser.setFirstTimeLogin(true);
        newUser.setUserPassword(passwordEncoder.encode(newUser.getUserPassword()));
        return userRepository.save(newUser);
    }

    private void validateUserCampos(UserCreateRequestDTO userRequest){

        if (userRepository.existsByUserName(userRequest.getUserName())) {
            throw new ResourceAlreadyExistsException(
                    "El nombre de usuario: " + userRequest.getUserName() + " ya está en uso."
            );
        }else if(userRequest.getEmail() != null){
            if(userRepository.existsByEmail(userRequest.getEmail())){
                throw new ResourceAlreadyExistsException(
                        "El email: " + userRequest.getEmail() + " ya está en uso."
                );
            }
        }
        if(userRequest.getRol() == Rol.SUPER_ADMIN){
            if(userRequest.getPlanPublicId() == null){
                throw new BadRequestException(
                        "El usuario admin debe tener un plan id asociado en planPublicId"
                );
            }
            if(userRequest.getCreatedBy() != null){
                throw new BadRequestException(
                        "El usuario admin no debe tener un admin id asociado en createdBy"
                );
            }
        }else{
            if(userRequest.getCreatedBy() == null){
                throw new BadRequestException(
                        "El nuevo usuario debe tener un admin id asociado en createdBy"
                );
            }
            if(userRequest.getPlanPublicId() != null){
                throw new BadRequestException(
                        "El nuevo usuario no debe tener un plan id asociado en planPublicId"
                );
            }
        }
    }
}
