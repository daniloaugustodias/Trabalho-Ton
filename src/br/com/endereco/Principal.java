package br.com.endereco;

import br.com.endereco.model.Pessoa;
import br.com.endereco.view.PersonEditDialogController;
import br.com.endereco.view.PersonOverviewController;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Principal extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Pessoa> passoaList = FXCollections.observableArrayList();

    
    public Principal() {        
        passoaList.add(new Pessoa("Joselito", "Carvalho", "Das Pedras", 87040010, "Sarandi", LocalDate.of(1999, 2, 21)));                
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cadastro de Pessoa");

        initRootLayout();

        showPersonOverview();
    }
    
    public void initRootLayout() {
        try {            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showPersonOverview() {
        try {            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            rootLayout.setCenter(personOverview);
          
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog(Pessoa person) {
        try {            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    public ObservableList<Pessoa> getPersonData() {
        return passoaList;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
