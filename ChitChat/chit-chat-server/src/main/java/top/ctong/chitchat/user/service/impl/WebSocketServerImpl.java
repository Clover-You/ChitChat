package top.ctong.chitchat.user.service.impl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;
import top.ctong.chitchat.user.service.WebSocketServer;

import java.util.concurrent.ConcurrentHashMap;

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
 * websocket server
 * </p>
 *
 * @author Clover
 * @date 2023-11-10 13:59
 */
@Service
public class WebSocketServerImpl implements WebSocketServer {

    /**
     * 所有在线用户 channel
     */
    private final static ConcurrentHashMap<Integer, ChannelHandlerContext> ONLINE_UID_MAP = new ConcurrentHashMap<>();

    /**
     * 记录所有在线用户的 UID
     */
    private final static ConcurrentHashMap<Channel, Integer> ONLINE_WS_MAP = new ConcurrentHashMap<>();

    /**
     * 记录用户上线
     *
     * @param proof 用户登录凭证
     * @param ctx   socket channel
     */
    @Override
    public void online(Integer proof, ChannelHandlerContext ctx) {
        ONLINE_UID_MAP.put(proof, ctx);
        ONLINE_WS_MAP.put(ctx.channel(), proof);
    }

    /**
     * 用户离线
     *
     * @param uid 用户 ID
     */
    @Override
    public void offline(Integer uid) {
        if (ONLINE_UID_MAP.containsKey(uid)) return;
        var ctx = ONLINE_UID_MAP.get(uid);
        ONLINE_UID_MAP.remove(uid);
        ONLINE_WS_MAP.remove(ctx.channel());
    }
}
