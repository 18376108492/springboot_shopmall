package com.itdan.shopmall;

import com.itdan.shopmall.spring.config.ShopMallApplication;
import org.springframework.boot.SpringApplication;

/**
 * 加载其子包下的配置文件
 */

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(ShopMallApplication.class,args);
    }

}
