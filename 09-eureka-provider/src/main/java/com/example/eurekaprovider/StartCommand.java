package com.example.eurekaprovider;

import com.example.eurekaprovider.utils.ServerPortUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class StartCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartCommand.class);

    public StartCommand(String[] args){
        Boolean isServerPort = false;
        String serverPort = "";
        if (args != null){
            for (String arg : args) {
                if (StringUtils.hasText(arg) && arg.startsWith("--server.port")){
                    isServerPort = true;
                    serverPort = arg;
                    break;
                }
            }
        }
        // 没有指定端口, 则随机生成一个可用端口
        if (!isServerPort){
            int port = ServerPortUtils.getAvailablePort();
            LOGGER.info("current server.port = {} " , port);
            System.setProperty("server.port", String.valueOf(port));
        }else {
            LOGGER.info("current server.port = {} ", serverPort.split("=")[1]);
            System.setProperty("server.port", serverPort.split("=")[1]);
        }
    }

}
