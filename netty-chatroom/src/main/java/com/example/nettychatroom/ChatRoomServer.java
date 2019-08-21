package com.example.nettychatroom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 聊天室服务端
 */
public class ChatRoomServer {

    private int port;

    public ChatRoomServer(int port){
        this.port = port;
    }

    public void start(){
        //用于接收客户端连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用于channel读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatRoomServerInitialize())
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.SO_KEEPALIVE, true);

            //绑定监听端口，调用sync同步阻塞方法等待绑定操作完成，
            // 完成后返回ChannelFuture类似于JDK中Future
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("服务器启动：");

            //使用sync方法进行阻塞，等待服务端链路关闭之后Main函数才退出
            future.channel().closeFuture().sync();
            System.out.println("服务器关闭：");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args){
        new ChatRoomServer(8888).start();
    }
}
