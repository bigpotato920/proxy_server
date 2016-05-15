package com.bigpotato;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import org.littleshoot.proxy.HttpFilters;
import org.littleshoot.proxy.HttpFiltersAdapter;
import org.littleshoot.proxy.HttpFiltersSourceAdapter;
import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hjy on 5/15/16.
 */
public class TestLittleProxy {
    private final static Logger logger = LoggerFactory.getLogger(TestLittleProxy.class);

    public static void main(String []args) {
        HttpProxyServer server =
                DefaultHttpProxyServer.bootstrap()
                        .withPort(8080)
                        .withFiltersSource(new HttpFiltersSourceAdapter() {
                            public HttpFilters filterRequest(HttpRequest originalRequest, ChannelHandlerContext ctx) {
                                return new HttpFiltersAdapter(originalRequest) {
                                    @Override
                                    public HttpResponse clientToProxyRequest(HttpObject httpObject) {
                                        // TODO: implement your filtering here
                                        logger.info("#TestLittleProxy clientToProxyRequest#httpObject = {}", httpObject.toString());
                                        return null;
                                    }

                                    @Override
                                    public HttpObject serverToProxyResponse(HttpObject httpObject) {
                                        // TODO: implement your filtering here
                                        logger.info("#TestLittleProxy serverToProxyResponse#httpObject = {}", httpObject.toString());
                                        return httpObject;
                                    }
                                };
                            }
                        })
                        .start();
    }
}
