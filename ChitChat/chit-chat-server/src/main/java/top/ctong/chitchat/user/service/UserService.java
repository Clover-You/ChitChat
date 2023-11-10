package top.ctong.chitchat.user.service;

import top.ctong.chitchat.user.domain.entity.User;
import top.ctong.chitchat.user.domain.vo.request.user.UserRegisterReq;

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
 * 用户相关服务
 * </p>
 *
 * @author Clover
 * @date 2023-11-10 14:53
 */
public interface UserService {

    /**
     * 通过账号查找用户信息
     *
     * @param account 账号
     * @return User
     * @author Clover You
     * @date 2023/11/10 15:31
     */
    User findUserByAccount(String account);

    /**
     * 用户注册
     *
     * @param req 注册参数
     * @return String
     * @author Clover You
     * @date 2023/11/10 16:09
     */
    String register(UserRegisterReq req);
}
