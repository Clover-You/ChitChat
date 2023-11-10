package top.ctong.chitchatcore.exception;

import java.util.Objects;

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
 * 异常工具
 * </p>
 *
 * @author Clover
 * @date 2023-11-10 14:54
 */
public class ErrorUtils {

    private ErrorUtils() {}

    public static void isTrue(boolean isTrue, ErrorCode error) {
        if (isTrue) return;
        throw new BusinessException(error);
    }

    public static void isTrue(boolean isTrue, BusinessException e) {
        if (isTrue) return;
        throw e;
    }

    public static void isEqual(Object obj1, Object obj2, BusinessException e) {
        if (Objects.equals(obj1, obj2)) return;
        throw e;
    }

    public static void isFalse(boolean isFalse, ErrorCode errorCode) {
        isTrue(!isFalse, errorCode);
    }
}
