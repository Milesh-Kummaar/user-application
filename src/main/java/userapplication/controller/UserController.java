package userapplication.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import userapplication.constants.NameSpaceConstants;
import userapplication.dto.UserDto;
import userapplication.io.BaseResponse;
import userapplication.service.UserService;
import userapplication.utils.CommonUtils;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "UserController", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = NameSpaceConstants.ADD_USER)
    public ResponseEntity<BaseResponse> processCreateUser(@RequestBody UserDto req) throws Exception {
        log.info("UserController :: processCreateUser() :: Init :: Request :: "+ CommonUtils.toJson(req));
        BaseResponse response = userService.processCreateUser(req);
        log.info("UserController :: processCreateUser() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.RETRIEVE_USERS)
    public ResponseEntity<BaseResponse> processRetrieveUsers(@PathVariable int page,@PathVariable int number) throws Exception {
        log.info("UserController :: processRetrieveUsers() :: Init ");
        BaseResponse response = userService.processRetrieveUsers(page,number);
        log.info("UserController :: processRetrieveUsers() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = NameSpaceConstants.RETRIEVE_USER)
    public ResponseEntity<BaseResponse> processRetrieveUser(@PathVariable UUID userId) throws Exception {
        log.info("UserController :: processRetrieveUser() :: Init :: Request :: "+ userId);
        BaseResponse response = userService.processRetrieveUser(userId);
        log.info("UserController :: processRetrieveUser() :: Ends :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = NameSpaceConstants.UPDATE_USER)
    public ResponseEntity<BaseResponse> processUpdateUser(@RequestBody UserDto req, @PathVariable UUID userId) throws Exception {
        log.info("UserController :: processUpdateUser() :: Init :: Request :: "+ CommonUtils.toJson(req));
        BaseResponse response = userService.processUpdateUser(req,userId);
        log.info("UserController :: processUpdateUser() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = NameSpaceConstants.DELETE_USER)
    public ResponseEntity<BaseResponse> processDeleteUser(@PathVariable UUID userId) throws Exception {
        log.info("UserController :: processDeleteUser() :: Init :: Request :: "+ userId);
        BaseResponse response = userService.processDeleteUser(userId);
        log.info("UserController :: processDeleteUser() :: Response :: " + CommonUtils.toJson(response));
        return ResponseEntity.ok(response);
    }
}
