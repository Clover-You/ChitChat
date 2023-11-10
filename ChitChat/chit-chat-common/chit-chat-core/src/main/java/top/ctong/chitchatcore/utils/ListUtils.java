package top.ctong.chitchatcore.utils;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

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
 * 列表工具
 * </p>
 *
 * @author Clover
 * @date 2023-10-20 16:17
 */
public class ListUtils {

    private ListUtils() {}

    /**
     * 在列表中返回一条满足结果的数据
     *
     * @param list 列表
     * @param func 过滤器
     * @return T
     * @author Clover You
     * @date 2023/10/20 16:21
     */
    public static <T> T findOne(List<T> list, Function<T, Boolean> func) {
        for (T it : list) {
            if (func.apply(it)) {
                return it;
            }
        }
        return null;
    }

}
