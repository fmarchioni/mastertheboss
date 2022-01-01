package io.netty.example;

import java.util.ArrayList;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public final class NettyServer {
	// Port where chat server will listen for connections.
	static final int PORT = 8007;

	public static void main(String[] args) throws Exception {

		/*
		 * Configure the server.
		 */

		// Create boss & worker groups. Boss accepts connections from client. Worker
		// handles further communication through connections.
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup) // Set boss & worker groups
					.channel(NioServerSocketChannel.class)// Use NIO to accept new connections.
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							/*
							 * Socket/channel communication happens in byte streams. String decoder &
							 * encoder helps conversion between bytes & String.
							 */
							p.addLast(new StringDecoder());
							p.addLast(new StringEncoder());

							p.addLast(new ServerHandler());
						}
					});

			// Start the server.
			ChannelFuture f = b.bind(PORT).sync();
			System.out.println("Netty Server started.");

			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} finally {
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}

@Sharable
class ServerHandler extends SimpleChannelInboundHandler<String> {

	// List of connected client channels.
	static final List<Channel> channels = new ArrayList<Channel>();

	/*
	 * Whenever client connects to server through channel, add his channel to the
	 * list of channels.
	 */
	@Override
	public void channelActive(final ChannelHandlerContext ctx) {
		System.out.println("Client joined - " + ctx);
		channels.add(ctx.channel());
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("Message received: " + msg);
		for (Channel c : channels) {
			c.writeAndFlush("Hello " + msg + '\n');
		}
	}

	/*
	 * In case of exception, close channel. One may chose to custom handle exception
	 * & have alternative logical flows.
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("Closing connection for client - " + ctx);
		ctx.close();
	}
}