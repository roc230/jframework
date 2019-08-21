package com.example.nettychatroom;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Channel事件处理
 */
public class ChatRoomServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel inComing = channelHandlerContext.channel();

        for(Channel channel : channels){
            //广播消息给所有用户
            if(channel != inComing){
                channel.writeAndFlush("[用户" + inComing.remoteAddress() + " 说：]" + s + "\n");
            }else{
                channel.writeAndFlush("[我说：]" + s + "\n");
            }
        }
    }

    /**
     * 当有客户端连接时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        //通知其它客户端有新人进入
        for(Channel channel : channels){
            if(channel != inComing){
                channel.writeAndFlush("[欢迎:" + inComing.remoteAddress() + "] 进入聊天室！\n");
            }
        }
        //添加新客户端
        channels.add(inComing);
    }

    /**
     * 断开连接时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel outComing = ctx.channel();
        for(Channel channel : channels){
            if(channel != outComing){
                channel.writeAndFlush("[再见: ]" + outComing.remoteAddress() + " 离开聊天室！\n");
            }
        }
        channels.remove(outComing);
    }

    /**
     * 服务端监听到客户端活动时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("[" + inComing.remoteAddress() + "]:在线");
    }

    /**
     * 离线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("[" + inComing.remoteAddress() + "]:离线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel income = ctx.channel();
        System.out.println(income.remoteAddress() + " 通讯异常!");
        ctx.close();
    }
}
