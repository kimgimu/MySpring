package com.kimu.crudboard.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Autowired
    private ApplicationContext context;

    //빈(객체)로 등록
    //@Configuration이 선언된 클래스 내에서만 쓸 수있다
    @Bean
    //실제 properties파일에 spring.datasource로 시작하는 설정을 모두 읽어 들여 해당 메서드에 매핑(바인딩)해준다
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }

    //공장을 만들어주는 객체 SqlSessionFactoryBean
    //공장 객체 SqlSessionFactory
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        //데이터 소스를 참조하며, XML Mapper(SQL쿼리 작성파일)의 경로와 설정파일 경로 등의 정보를 갖는 객체
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        //데이터베이스 참조
        factoryBean.setDataSource(dataSource());
        //mapper.xml 경로 설정
        //src/main/Resources까지 prefix된다
        //ant pattern 사용
        factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        //하단에 만들어놓은 mybatis 설정 메서드 불러온다
        factoryBean.setConfiguration(mybatisConfig());
        //SqlSessionFactory를 반환해준다
        return factoryBean.getObject();
    }

    //SqlSession을 관리해주는 객체 SqlSessionTemplate
    //실제로는 SqlSessionFactory에서 sqlSession을 만들어주지만
    //SqlSessionTemplate으로 관리를 하는방법을 쓴다 (트랜잭션,예외처리,빈관리,간단한사용 등등 장점이 많기에..)
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        //SqlSessionTemplate를 생성하기 위해서는 SqlSessionFactory객체가 필요하다
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    //properties 파일에 있는 mybatis 설정을 빈으로 등록해준다
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        return new org.apache.ibatis.session.Configuration();
    }


}