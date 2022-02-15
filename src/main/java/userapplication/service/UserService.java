package userapplication.service;

import org.springframework.stereotype.Service;
import userapplication.dto.UserDto;
import userapplication.io.BaseResponse;

import java.util.UUID;


@Service
public interface UserService {

    BaseResponse processCreateUser(UserDto req) throws Exception;

    BaseResponse processRetrieveUsers(int page, int number) throws Exception;

    BaseResponse processRetrieveUser(UUID userId) throws Exception;

    BaseResponse processUpdateUser(UserDto req, UUID userId) throws Exception;

    BaseResponse processDeleteUser(UUID userId) throws Exception;

}
