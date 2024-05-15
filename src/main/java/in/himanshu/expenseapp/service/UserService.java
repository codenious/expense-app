package in.himanshu.expenseapp.service;

import in.himanshu.expenseapp.dto.RequestUserDto;
import in.himanshu.expenseapp.dto.ResponseUserDto;


public interface UserService{

    ResponseUserDto createUser(RequestUserDto requestUserDto);

    ResponseUserDto readUserById(Long id);

    ResponseUserDto updateUser(Long id, RequestUserDto requestUserDto);

    String deleteUser(Long id);
}
