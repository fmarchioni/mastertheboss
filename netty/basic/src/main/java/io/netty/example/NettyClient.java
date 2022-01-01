package io.netty.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


 
public final class NettyClient {

	static final String HOST = "127.0.0.1";
	static final int PORT = 8007;
 
 
	public static void main(String[] args) throws Exception {
 
		 
	 
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group) // Set EventLoopGroup to handle all eventsf for client.
					.channel(NioSocketChannel.class)// Use NIO to accept new connections.
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							/*
							 * Socket/channel communication happens in byte streams. String decoder &
							 * encoder helps conversion between bytes & String.
							 */
							p.addLast(new StringDecoder());
							p.addLast(new StringEncoder());
 
							// This is our custom client handler which will have logic for chat.
							p.addLast(new  ClientHandler());
 
						}
					});
 
			// Start the client.
			ChannelFuture f = b.connect(HOST, PORT).sync();
 
			 
				String input = "Frank";
				Channel channel = f.sync().channel();
				channel.writeAndFlush(input);
				channel.flush();
			 
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down the event loop to terminate all threads.
			group.shutdownGracefully();
		}
	}
}
  class  ClientHandler extends SimpleChannelInboundHandler<String> {
	  
 
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
			System.out.println("Message from Server: " + msg);
	 
		}
	 
	}