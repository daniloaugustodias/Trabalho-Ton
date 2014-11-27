package br.com.endereco.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.controlsfx.dialog.Dialogs;

import br.com.endereco.Principal;
import br.com.endereco.model.Pessoa;
import br.com.endereco.util.DateUtil;

public class PersonOverviewController {

    @FXML
    private TableView<Pessoa> tblPessoa;
    @FXML
    private TableColumn<Pessoa, String> firstNameColumn;
    @FXML
    private TableColumn<Pessoa, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private Principal principal;

    public PersonOverviewController() {
    }

    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().nomeProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().sobrenomeProperty());

        showPersonDetails(null);
       
        tblPessoa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(Principal mainApp) {
        this.principal = mainApp;
       
        tblPessoa.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Pessoa person) {
        if (person != null) {
            firstNameLabel.setText(person.getNome());
            lastNameLabel.setText(person.getSobrenome());
            streetLabel.setText(person.getRua());
            postalCodeLabel.setText(Integer.toString(person.getCEP()));
            cityLabel.setText(person.getCidade());
            birthdayLabel.setText(DateUtil.format(person.getAniversario()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleDeletarPessoa() {
        int selectedIndex = tblPessoa.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tblPessoa.getItems().remove(selectedIndex);
        } else {
            Dialogs.create()
                    .owner(this)
                    .title("Sem Seleção!")
                    .masthead("Nenhuma pessoa selecionada!")
                    .message("Por favor, selecione uma pessoa na tabela.")
                    .showWarning();
        }
    }

    @FXML
    private void handleNovaPessoa() {
        Pessoa tempPerson = new Pessoa();
        boolean okClicked = principal.showPersonEditDialog(tempPerson);
        if (okClicked) {
            principal.getPersonData().add(tempPerson);
        }
    }

    @FXML
    private void handleEditarPessoa() {
        Pessoa selectedPerson = tblPessoa.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = principal.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
            Dialogs.create()
                    .owner(this)
                    .title("Sem Seleção!")
                    .masthead("Nenhuma pessoa selecionada!")
                    .message("Por favor, selecione um registro na tabela.")
                    .showWarning();
        }
    }
}
