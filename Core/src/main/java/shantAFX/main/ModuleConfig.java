package shantAFX.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static java.util.stream.Collectors.toList;
import java.util.List;
import shantAFX.common.services.*;
import java.util.ServiceLoader;

@Configuration
public class ModuleConfig {

    @Bean
    public App app() {
        return new App(gamePluginServices(), entityProcessingServiceList(), postEntityProcessingServices());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
