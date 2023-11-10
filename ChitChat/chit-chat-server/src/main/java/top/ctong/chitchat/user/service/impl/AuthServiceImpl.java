package top.ctong.chitchat.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ctong.chitchat.common.domain.entity.User;
import top.ctong.chitchat.common.domain.vo.request.auth.PasswordAuthLoginReq;
import top.ctong.chitchat.user.exceptioin.LoginException;
import top.ctong.chitchat.user.service.AuthService;
import top.ctong.chitchat.user.service.UserService;
import top.ctong.chitchatcore.exception.ErrorCode;
import top.ctong.chitchatcore.exception.ErrorUtils;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀     ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒      ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░      ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄      ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄     ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒     ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 * Copyright 2023 Clover You.
 * <p>
 * 鉴权服务 IMPL
 * </p>
 *
 * @author Clover
 * @date 2023-11-10 14:36
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Autowired
    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * 账号密码登录校验
     *
     * @param request 请求数据
     * @author Clover You
     * @date 2023/11/10 14:38
     */
    @Override
    public User login(PasswordAuthLoginReq request) {
        User user = userService.findUser(request.getAccount());
        ErrorUtils.ifTrue(user != null, new LoginException(ErrorCode.BAD_REQUEST));
        ErrorUtils.isEqual(request.getPassword(), user.getPassword(), new LoginException(ErrorCode.BAD_REQUEST));
        // 校验通过
        return user;
    }

}
