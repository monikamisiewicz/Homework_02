package pl.coderslab;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import pl.coderslab.configuration.AppConfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//klasa startowa aplikacji
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        //Tworzymy kontekst aplikacji.
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(AppConfig.class); //Rejestrujemy klasę konfiguracji.
        ctx.setServletContext(servletContext); //Ustawiamy kontekst servletów.

        //Tworzymy i ustawiamy DispatcherServlet .
        //DispatcherServlet - W zakresie jego odpowiedzialności jest odbieranie żądań generowanych np. przez
        // przeglądarkę, a następnie przekierowanie ich do odpowiedniego kontrolera,
        // który zajmie się dalszą jego obsługą.
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/"); //Ustawiamy adres przechwytywania dla naszej aplikacji.

        //definicja filtra odpowiedzialnego za ustawienie prawidłowego kodowania
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");

    }
}
