package hussarexchange.javafx.main;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import hussarexchange.results.GameResultDao;
import util.guice.PersistenceModule;

import javax.inject.Inject;
import java.util.List;

public class MyApplication extends Application {
    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("hussar-exchange"));
                    bind(GameResultDao.class);
                }
            }
    ));

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/LaunchScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("css/style.css");
        stage.setTitle("Huszárcsere játék");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}