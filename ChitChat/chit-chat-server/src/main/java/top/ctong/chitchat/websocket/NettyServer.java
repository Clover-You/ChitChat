package top.ctong.chitchat.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

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
 * netty 服务启动
 * </p>
 *
 * @author Clover
 * @date 2023-10-20 14:30
 */
@Component
@EnableConfigurationProperties(NettyProperty.class)
public class NettyServer implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    private final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     * worker 线程组，用于服务端接受客户端数据读写
     */
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * boss 线程组，用于服务端接受客户端连接
     */
    private final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);

    private final NettyServerHandlerInitializer nettyServerHandlerInitializer;

    private final NettyProperty property;

    private Channel serverChannel;

    private ApplicationContext applicationContext;

    @Autowired
    public NettyServer(NettyProperty property, NettyServerHandlerInitializer nettyServerHandlerInitializer) {
        this.property = property;
        this.nettyServerHandlerInitializer = nettyServerHandlerInitializer;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 创建 Server Bootstrap 对象，用于启动 netty
        var serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        // 指定 channel 为服务端
        serverBootstrap.channel(NioServerSocketChannel.class);
        // 设置 Netty Server 启动端口
        serverBootstrap.localAddress(new InetSocketAddress("0.0.0.0", property.getPort()));
        serverBootstrap.childHandler(nettyServerHandlerInitializer);

        var future = serverBootstrap.bind().sync();

        if (future.isSuccess()) {
            serverChannel = future.channel();
            logger.info("WebSocket server start in {} port!", property.getPort());
        }
    }

    /**
     * 在 SpringBoot 服务关闭时触发
     *
     * @author Clover You
     * @date 2023/10/20 14:32
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (this.serverChannel != null) {
            this.serverChannel.close();
        }
        // 关闭线程组
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();

        logger.info("websocket stop!");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
