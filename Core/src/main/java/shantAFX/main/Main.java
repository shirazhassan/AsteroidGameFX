package shantAFX.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);
        ctx.registerShutdownHook();

        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
    }

        App app = ctx.getBean(App.class);
        app.start(stage);
    }
}
