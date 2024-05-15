package in.himanshu.expenseapp.service.impl;

import in.himanshu.expenseapp.dto.RequestUserDto;
import in.himanshu.expenseapp.dto.ResponseUserDto;
import in.himanshu.expenseapp.entity.User;
import in.himanshu.expenseapp.exceptions.ItemAlreadyExistsException;
import in.himanshu.expenseapp.exceptions.ResourceNotFoundException;
import in.himanshu.expenseapp.mapper.UserMapper;
import in.himanshu.expenseapp.repository.UserRepository;
import in.himanshu.expenseapp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import in.himanshu.expenseapp.utils.constants;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseUserDto createUser(RequestUserDto requestUserDto) {
        //Why no need to create a new User?
        User newUser;
        if(!userRepository.existsByEmail(requestUserDto.getEmail())){
            if(!userRepository.existsByUserName(requestUserDto.getUserName())) {
                newUser = userRepository.save(UserMapper.convertToUser(requestUserDto));
            } else {
                throw new ItemAlreadyExistsException("User name already exists for username: " + requestUserDto.getUserName() );
            }
        } else {
            throw new ItemAlreadyExistsException("Email already exists for email: " + requestUserDto.getEmail());
        }
        return  UserMapper.convertToResponseUserDto(newUser);

    }

    @Override
    public ResponseUserDto readUserById(Long id) {
       if(userRepository.findById(id).isEmpty()){
           throw new ResourceNotFoundException("User doesn't exist for id: " + id);
       }

       return UserMapper.convertToResponseUserDto(userRepository.findById(id).get());
    }

    @Override
    public ResponseUserDto updateUser(Long id, RequestUserDto requestUserDto) throws ResourceNotFoundException{
        User newUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User don't exist for id: " + id));

        newUser.setFirstName(requestUserDto.getFirstName() != null ? requestUserDto.getFirstName() : newUser.getFirstName());
        newUser.setLastName(requestUserDto.getLastName() != null? requestUserDto.getLastName() : newUser.getLastName());
        newUser.setUserName(requestUserDto.getUserName() != null ? requestUserDto.getUserName() : newUser.getUserName());
        newUser.setEmail(requestUserDto.getEmail() != null ? requestUserDto.getEmail() : newUser.getEmail());
        newUser.setPassword(requestUserDto.getPassword() != null ? requestUserDto.getPassword() : newUser.getPassword());
        newUser.setAge(requestUserDto.getAge() != null ? requestUserDto.getAge() : newUser.getAge());
        return UserMapper.convertToResponseUserDto(userRepository.save(newUser));

    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User don't exist for id: " + id));
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

}
