package userapplication.service.Imple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import userapplication.constants.MessageCodes;
import userapplication.dto.UserDto;
import userapplication.entity.Users;
import userapplication.io.BaseResponse;
import userapplication.io.StatusMessage;
import userapplication.repository.UserRepository;
import userapplication.service.UserService;
import userapplication.utils.CommonUtils;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse processRetrieveUser(UUID userId) throws Exception {
        log.info("UserServiceImpl :: processRetrieveUser() :: Init ");
        Users users=userRepository.getById(userId);
        if (ObjectUtils.isEmpty(users))
            throw new IllegalArgumentException("Please Check the User Id");
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(users, userDto);
        log.info("UserServiceImpl :: processRetrieveUser() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.RETRIEVE_USER)
                        .build())
                .data(userDto)
                .build();
    }

    @Override
    public BaseResponse processRetrieveUsers(int page, int number) throws Exception {
        log.info("UserServiceImpl :: processRetrieveRoles() :: Init ");
        Pageable pageable = PageRequest.of(page-1,number);
        Page<Users> usersPage=userRepository.findAll(pageable);
        log.info("UserServiceImpl :: processRetrieveRoles() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.RETRIEVE_USERS)
                        .build())
                .data(usersPage)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processCreateUser(UserDto request) throws Exception {
        log.info("UserServiceImpl :: processCreateUser() :: Init ");
        Users users1=userRepository.findByEmailId(request.getEmailId());
        if(users1!=null)
        {
            throw new IllegalArgumentException("User With this Email Id Already Exists!!");
        }
        Users users=new Users();
        if(CommonUtils.isValidEmail(request.getEmailId()))
        {
            throw new IllegalArgumentException("Enter a Valid Email ID!!");
        }
        if(CommonUtils.isValidPhoneNumber(request.getPhoneNumber()))
        {
            throw new IllegalArgumentException("Enter a Valid Phone Number!!");
        }
        BeanUtils.copyProperties(request, users);
        userRepository.save(users);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(users, userDto);
        log.info("UserServiceImpl :: processCreateUser()  :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.ADD_USER)
                        .build())
                .data(userDto)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processUpdateUser(UserDto request, UUID userId) throws Exception {
        log.info("UserServiceImpl :: processUpdateRole() :: Init ");
        Users users=userRepository.getById(userId);
        if (ObjectUtils.isEmpty(users))
            throw new IllegalArgumentException("Please Check the User Id");
        if(CommonUtils.isValidEmail(request.getEmailId()))
        {
            throw new IllegalArgumentException("Enter a Valid Email ID!!");
        }
        if(CommonUtils.isValidPhoneNumber(request.getPhoneNumber()))
        {
            throw new IllegalArgumentException("Enter a Valid Phone Number!!");
        }
        BeanUtils.copyProperties(request, users, CommonUtils.getNullPropertyNames(request));
        userRepository.save(users);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(users, userDto);
        log.info("UserServiceImpl :: processUpdateUser() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.UPDATE_USER)
                        .build())
                .data(userDto)
                .build();
    }

    @Override
    @Transactional
    public BaseResponse processDeleteUser(UUID userId) throws Exception {
        log.info("UserServiceImpl :: processDeleteRole() :: Init ");
        Users users=userRepository.getById(userId);
        if (ObjectUtils.isEmpty(users))
            throw new IllegalArgumentException("Please Check the User Id");
        userRepository.deleteById(userId);
        log.info("UserServiceImpl :: processDeleteRole() :: Ends ");
        return BaseResponse.builder()
                .status(MessageCodes.SUCCESS)
                .statusMessage(StatusMessage.builder()
                        .code(MessageCodes.SUCCESS)
                        .description(MessageCodes.DELETE_USER)
                        .build())
                .build();
    }
}