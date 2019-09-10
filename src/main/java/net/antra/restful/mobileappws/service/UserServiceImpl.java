package net.antra.restful.mobileappws.service;

import net.antra.restful.mobileappws.io.repository.UserRepository;
import net.antra.restful.mobileappws.io.entity.UserEntity;
import net.antra.restful.mobileappws.shared.Utils;
import net.antra.restful.mobileappws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        if ( userRepository.findUserByEmail(user.getEmail()) != null)
            throw new RuntimeException("Record has already existed");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity ue = userRepository.findUserByEmail(email);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(ue, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String id) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(id);

        if (userEntity == null) throw new UsernameNotFoundException(id);
        BeanUtils.copyProperties(userEntity, returnValue);
        
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity ue = userRepository.findUserByEmail(email);
        if (ue == null) throw new UsernameNotFoundException(email);
        return new User(ue.getEmail(), ue.getEncryptedPassword(), new ArrayList<>());
    }
}
