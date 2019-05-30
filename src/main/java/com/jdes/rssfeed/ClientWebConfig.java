// package com.jdes.rssfeed;
//
//
// import org.springframework.stereotype.Configuration;
// import org.springframework.stereotype.EnableWebMvc;
// import org.springframework.web.bind.annotation.Override;
// import org.springframework.web.bind.annotation.Bean;
//
// @EnableWebMvc // get support!
// @Configuration // set the class as MVC object?
//
//
// // WebMvcConfigurer is an interface!1
// public class ClientWebConfig implements WebMvcConfigurer {
//
//   @Override // override the interface method
//   public void addViewControllers(ViewControllerRegistry registry) {
//     registry.addViewController("/index");
//   }
//
// // the below Bean will return .jsp view from the /WEB-INF/view directory
//   @Bean
//   public ViewResolver viewResolver() {
//     InternalResourceViewResolver bean = new InternalResourceViewResolver();
//
//     bean.setViewClass(JstlView.class);
//     bean.setPrefix("/WEB-INF/view/");
//     bean.setSuffix(".jsp");
// 
//     return bean;
//   }
// }
