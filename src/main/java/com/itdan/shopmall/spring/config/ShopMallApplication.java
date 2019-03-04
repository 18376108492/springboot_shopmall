package com.itdan.shopmall.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


/**
 * spring应用入口
 */

@Configuration
@PropertySource(value = {"classpath:jdbc.properties",
                         "classpath:resource.properties",
                         "classpath:log4j.properties",
                         "classpath:client.conf"},
                         ignoreResourceNotFound = true
)
//扫描配置注解
@ComponentScan(basePackages = "com.itdan")
@SpringBootApplication
public class ShopMallApplication {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 数据库驱动
        druidDataSource.setDriverClassName(jdbcDriverClassName);
        // 相应驱动的jdbcUrl
        druidDataSource.setUrl(jdbcUrl);
        // 数据库的用户名
        druidDataSource.setUsername(jdbcUsername);
        // 数据库的密码
        druidDataSource.setPassword(jdbcUsername);
        // 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
        druidDataSource.setMaxWait(60);
        // 每个分区最大的连接数
        druidDataSource.setMaxActive(100);
        // 每个分区最小的连接数
        druidDataSource.setMinIdle(5);
        return druidDataSource;
    }


    public static void main(String[] args) {

    }
}
