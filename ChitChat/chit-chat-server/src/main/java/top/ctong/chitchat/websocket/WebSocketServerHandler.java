package top.ctong.chitchat.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.ctong.chitchat.user.service.WebSocketServer;
import top.ctong.chitchat.websocket.adapter.MessageAdapter;
import top.ctong.chitchatcore.utils.ListUtils;

import java.util.List;

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
 * websocket 消息处理
 * </p>
 *
 * @author Clover
 * @date 2023-11-10 11:08
 */
@Component
public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final static Logger log = LoggerFactory.getLogger(WebSocketServerHandler.class);

    /**
     * 消息适配器
     */
    private final List<MessageAdapter> websocketMessageAdapter;

    private final WebSocketServer webSocketServer;

    @Autowired
    public WebSocketServerHandler(List<MessageAdapter> websocketMessageAdapter, WebSocketServer webSocketServer) {
        this.websocketMessageAdapter = websocketMessageAdapter;
        this.webSocketServer = webSocketServer;
    }

    /**
     * 引发异常时触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("WebSocket Exception {}", cause.getMessage());
        ctx.channel().close();
    }

    /**
     * Is called for each message of type {@link WebSocketFrame}.
     *
     * @param ctx   the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *              belongs to
     * @param frame the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        try {
            var messageHandler = ListUtils.findOne(websocketMessageAdapter, it -> it.canRead(frame));
            if (messageHandler == null) {
                frame.release();
                return;
            }
            messageHandler.invoke(ctx, frame);
        } catch (Exception e) {
            log.error(e.getMessage());
            // 如果出现错误那么通过 DISCARD 协议忽略本次消息
            ReferenceCountUtil.release(frame);
        }
    }

    /**
     * 客户端连接后触发
     *
     * @author Clover You
     * @date 2023/11/10 11:25
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
    }

    /**
     * 用户掉线之后触发
     *
     * @author Clover You
     * @date 2023/11/10 14:23
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
    }
}
