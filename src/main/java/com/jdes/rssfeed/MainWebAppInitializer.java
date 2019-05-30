// package com.jdes.rssfeed;
//
// import org.springframework.web.bind.annotation.Override;
//
// public class MainWebAppInitializer implements WebApplicationInitializer {
//   @Override
//   public void onStartup(final ServletContext sc) throws ServletException {
//
//     AnnotationConfigWebApplicationContext root =
//     new AnnotationConfigWebApplicationContext();
//
//     root.scan("com.jdes.rssfeed");
//     sc.addListener(new ContextLoaderListener(root));
//
//     ServletRegistration.Dynamic appServlet =
//       sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
//     appServlet.setLoadOnStartup(1);
//     appServlet.addMapping("/");
//
//   }
// }
