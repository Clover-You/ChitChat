package top.ctong.chitchat.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ctong.chitchat.user.domain.entity.User;
import top.ctong.chitchat.user.dao.UserDao;
import top.ctong.chitchat.user.domain.vo.request.user.UserRegisterReq;
import top.ctong.chitchat.user.service.UserService;
import top.ctong.chitchatcore.exception.ErrorCode;
import top.ctong.chitchatcore.exception.ErrorUtils;
import top.ctong.chitchatcore.utils.StringUtils;

import java.util.Date;

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
 * 用户服务
 * </p>
 *
 * @author Clover
 * @date 2023-11-10 15:32
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 通过账号查找用户信息
     *
     * @param account 账号
     * @return User
     * @author Clover You
     * @date 2023/11/10 15:31
     */
    @Override
    public User findUserByAccount(String account) {
        return userDao.getByAccount(account);
    }

    /**
     * 用户注册
     *
     * @param req 注册参数
     * @return String
     * @author Clover You
     * @date 2023/11/10 16:09
     */
    @Override
    @Transactional
    public String register(UserRegisterReq req) {
        // 生成一个随机账号
        var myAcc = "2621869236";
        // 检查数据库是否存在这个账号，如果数据库中存在该账号
        // 那么重新生成如果5次之后还重复，那么返回系统异常
        ErrorUtils.isFalse(userDao.existAccount(myAcc), ErrorCode.BAD_REQUEST);
        var metaRecord = genRegisterUserData(
            User.builder().name(req.name()).account(myAcc).password(req.password()).build()
        );
        var saveStatus = userDao.save(metaRecord);
        if (!saveStatus) log.error("用户注册 状态 FALSE 无异常");
        ErrorUtils.isTrue(saveStatus, ErrorCode.UNKNOWN_SYS_ERROR);
        return myAcc;
    }

    /**
     * 生成注册数据
     *
     * @return User
     * @author Clover You
     * @date 2023/11/10 16:22
     */
    private User genRegisterUserData(User record) {
        var userBuilder = User.builder()
            .createTime(new Date());

        if (StringUtils.isNotBlank(record.getAvatar())) {
            userBuilder.avatar(record.getAvatar());
        } else {
            userBuilder.avatar("https://thirdqq.qlogo.cn/g?b=sdk&k=uuh6j4MaKaIsMqYolhtGmg&kti=ZU3qHgAAAAE&s=100&t=1688390589");
        }

        if (StringUtils.isNotBlank(record.getName())) {
            userBuilder.name(record.getName());
        } else {
            userBuilder.name("chit chat user");
        }

        if (StringUtils.isNotBlank(record.getAccount())) {
            userBuilder.account(record.getAccount());
        }

        if (StringUtils.isNotBlank(record.getPassword())) {
            userBuilder.password(record.getPassword());
        }

        return userBuilder.build();
    }
}
