package com.example.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //接受新连接的线程容器
        NioEventLoopGroup boos = new NioEventLoopGroup();
        //读取数据的线程容器
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ChannelFuture channelFuture = serverBootstrap.group(boos, worker)
                //设置通道
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                System.out.println(s);
                            }
                        });
                    }
                })
                //设置缓冲区大小
                .option(ChannelOption.SO_BACKLOG, 128)
                //设置保持连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //绑定端口
                .bind(8000).sync();
                //等待关闭
                channelFuture.channel()
                        .closeFuture().sync();
    }
}
