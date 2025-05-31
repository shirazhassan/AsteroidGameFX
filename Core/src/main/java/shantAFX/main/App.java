package shantAFX.main;

import shantAFX.common.data.*;
import shantAFX.common.services.*;

import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.util.*;

public class App {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<String, Node> entityViews = new HashMap<>();
    private final Set<KeyCode> activeKeys = new HashSet<>();

    private Pane gamePane;
    private Scene scene;
    private Label collisionModeLabel;

    private final List<IGamePluginService> plugins;
    private final List<IEntityProcessingService> processors;
    private final List<IPostEntityProcessingService> postProcessors;


    public App(List<IGamePluginService> plugins, List<IEntityProcessingService> processors, List<IPostEntityProcessingService> postProcessors) {
        this.plugins = plugins;
        this.processors = processors ;
        this.postProcessors = postProcessors;
    }

    public void start(Stage primaryStage){
        setupGameWindow(primaryStage);
        setupKeyHandling();
        setupLabels();
        loadServices();

        new AnimationTimer() {
            private long lastUpdate = -1;

            @Override
            public void handle(long now) {
                if (lastUpdate < 0) {
                    lastUpdate = now;
                    return;
                }
                float deltaTime = (now - lastUpdate) / 1_000_000_000f;
                lastUpdate = now;

                gameData.setDeltaTime(deltaTime);
                updateKeys();
                processEntities();
                renderEntities();
            }
        }.start();
    }

    private void setupGameWindow(Stage stage) {
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: black;");
        scene = new Scene(gamePane, gameData.getDisplayWidth(), gameData.getDisplayHeight());

        stage.setScene(scene);
        stage.setTitle("Asteroids");
        stage.show();

        // This Handling window size
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            gameData.setDisplayWidth(newVal.intValue());
            gamePane.setPrefWidth(newVal.doubleValue());
        });

        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            gameData.setDisplayHeight(newVal.intValue());
            gamePane.setPrefHeight(newVal.doubleValue());
        });
    }

    private void setupKeyHandling() {
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            activeKeys.add(code);

            // This Handles special keys
            if (code == KeyCode.F11) {
                Stage stage = (Stage) scene.getWindow();
                stage.setFullScreen(!stage.isFullScreen());
            } else if (code == KeyCode.TAB) {
                toggleCollisionMode();
            }
        });

        scene.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));
    }

    private void toggleCollisionMode() {
        CollisionMode[] modes = CollisionMode.values();
        CollisionMode current = gameData.getCollisionMode();
        CollisionMode next = modes[(current.ordinal() + 1) % modes.length];
        gameData.setCollisionMode(next);
        collisionModeLabel.setText("Collision: " + next);
    }

    private void setupLabels() {
        collisionModeLabel = new Label("Collision: " + gameData.getCollisionMode());
        collisionModeLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.5);");
        collisionModeLabel.setTranslateX(10);
        collisionModeLabel.setTranslateY(10);
        gamePane.getChildren().add(collisionModeLabel);
    }

    private void loadServices() {
        // Here all plugins Loads
        ServiceLoader.load(IGamePluginService.class).forEach(plugin -> {
            plugins.add(plugin);
            plugin.start(gameData, world);
        });

        // Here all processors loads
        ServiceLoader.load(IEntityProcessingService.class).forEach(processors::add);
        ServiceLoader.load(IPostEntityProcessingService.class).forEach(postProcessors::add);
    }

    private void updateKeys() {
        GameKeys keys = gameData.getKeys();
        keys.setKey(GameKeys.LEFT, activeKeys.contains(KeyCode.LEFT) || activeKeys.contains(KeyCode.A));
        keys.setKey(GameKeys.RIGHT, activeKeys.contains(KeyCode.RIGHT) || activeKeys.contains(KeyCode.D));
        keys.setKey(GameKeys.UP, activeKeys.contains(KeyCode.UP) || activeKeys.contains(KeyCode.W));
        keys.setKey(GameKeys.SPACE, activeKeys.contains(KeyCode.SPACE));
        keys.update();
    }

    private void processEntities() {
        processors.forEach(processor -> processor.process(gameData, world));
        postProcessors.forEach(postProcessor -> postProcessor.process(gameData, world));
    }

    private void renderEntities() {
        // This removes views after deleting entities form world
        entityViews.keySet().removeIf(id -> {
            if (world.getEntity(id) == null) {
                gamePane.getChildren().remove(entityViews.get(id));
                return true;
            }
            return false;
        });

        // Here existing entities updates and then create new ones
        for (Entity entity : world.getEntities()) {
            Node view = entityViews.get(entity.getID());

            if (view == null) {
                view = createEntityView(entity);
                entityViews.put(entity.getID(), view);
                gamePane.getChildren().add(view);
            }
            updateView(view, entity);
        }
    }

    private Node createEntityView(Entity entity) {
        Polygon polygon = new Polygon();
        for (double coord : entity.getPolygonCoordinates()){
            polygon.getPoints().add(coord);
        }
        java.awt.Color awt = entity.getColor();
        Color fxColor = Color.rgb(awt.getRed(), awt.getGreen(), awt.getAlpha());
        polygon.setFill(Color.DARKGRAY.interpolate(fxColor, 0.2));
        polygon.setStroke(fxColor);
        polygon.setStrokeWidth(1.2);

        if ("Bullet".equals(entity.getType())) {
            Glow glow = new Glow(0.7);
            glow.setInput(new Bloom(0.5));
            polygon.setEffect(glow);
        }

        return polygon;
    }

    private void updateView(Node view, Entity entity) {
        view.setTranslateX(entity.getX());
        view.setTranslateY(entity.getY());
        view.setRotate(entity.getRotation());
    }
}