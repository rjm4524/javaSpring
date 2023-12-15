package com.java.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class AppConfig {
	@Bean
	public SqlSessionFactory SqlSessionFactory(DataSource dataSource) throws Exception{
		
		
		//mybatis session연결 - application.properties에서 정보를 가져와 db의 dataSource를 가져옴.
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean(); 
		sessionFactory.setDataSource(dataSource);
		
		//query문이 담긴 mapper파일을 모두 가져옴.
		Resource[] res = new PathMatchingResourcePatternResolver().getResources(
				"classpath:/mapper/**/*.xml"
				);
		sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();//mybatis db+mapper 정보가들어가있는 객체
	}
	//객체 1개를 불러와서 리턴하는것.
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		
		return new SqlSessionTemplate(sqlSessionFactory); //mybatis에 사용할 1개 객체를 가져옴.
	}
	
	
}
