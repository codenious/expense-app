package in.himanshu.expenseapp.mapper;

import in.himanshu.expenseapp.dto.RequestUserDto;
import in.himanshu.expenseapp.dto.ResponseUserDto;
import in.himanshu.expenseapp.entity.User;

public class UserMapper {


    public static User convertToUser(RequestUserDto requestUserDto){

        User user = new User();

        user.setFirstName(requestUserDto.getFirstName());
        user.setLastName(requestUserDto.getLastName());
        user.setUserName(requestUserDto.getUserName());
        user.setEmail(requestUserDto.getEmail());
        user.setPassword(requestUserDto.getPassword());
        user.setAge(requestUserDto.getAge());
        return user;
    }


    public static RequestUserDto convertToRequestUserDto(User user){
        RequestUserDto requestUserDto = new RequestUserDto();
        requestUserDto.setId(user.getId());
        requestUserDto.setFirstName(user.getFirstName());
        requestUserDto.setLastName(user.getLastName());
        requestUserDto.setUserName(user.getUserName());
        requestUserDto.setEmail(user.getEmail());
        requestUserDto.setPassword(user.getPassword());
        requestUserDto.setAge(user.getAge());

        return requestUserDto;
    }

    public static ResponseUserDto convertToResponseUserDto(User user){
       return new ResponseUserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(),user.getEmail(), user.getAge());

    }

//    public static User  convertToUserFromResponseUserDto(ResponseUserDto responseUserDto){
//        User user = new User();
//        user.setId(responseUserDto.getId());
//        user.setFirstName(responseUserDto.getFirstName());
//        user.setLastName(responseUserDto.getLastName());
//        user.setUserName(responseUserDto.getUserName());
//        user.setEmail(responseUserDto.getEmail());
//        user.setAge(responseUserDto.getAge());
//        return user;
//    }

}
