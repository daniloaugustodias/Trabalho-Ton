package br.com.endereco.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import br.com.endereco.model.Pessoa;
import br.com.endereco.util.DateUtil;

public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Pessoa person;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Pessoa person) {
        this.person = person;

        firstNameField.setText(person.getNome());
        lastNameField.setText(person.getSobrenome());
        streetField.setText(person.getRua());
        postalCodeField.setText(Integer.toString(person.getCEP()));
        cityField.setText(person.getCidade());
        birthdayField.setText(DateUtil.format(person.getAniversario()));
        birthdayField.setPromptText("dd/mm/yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setNome(firstNameField.getText());
            person.setSobrenome(lastNameField.getText());
            person.setRua(streetField.getText());
            person.setCEP(Integer.parseInt(postalCodeField.getText()));
            person.setCidade(cityField.getText());
            person.setAniversario(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Preencha o Nome!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Preencha o sobrenome!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Preencha a rua!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "Preencha o CEP!\n";
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Formato inválido só permitido números!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Peencha a cidade!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Preencha a Data de Aniversário!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Formato inválido! siga esse formato dd/mm/yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
           Dialogs.create()
		        .title("Campos Inválidos!")
		        .masthead("Corrija campos inválidos.")
		        .message(errorMessage)
		        .showError();            
            return false;
        }
    }
}
