package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Contato;

public class FXMLPrincipalController implements Initializable {
  @FXML
  private Button buttonAdicionar;
  @FXML
  private Button buttonRemover;
  @FXML
  private Button buttonFechar;
  @FXML
  private Button buttonMinimizar;
  @FXML
  private TextField emailTextField;
  @FXML
  private TextField nomeTextField;
  @FXML
  private TextField telefoneTextField;
  @FXML
  private TextField pesquisaTextField;
  @FXML
  private RowConstraints buttonsRow;
  @FXML
  private TableColumn<Contato, String> tableColumnContatoEmail;
  @FXML
  private TableColumn<Contato, String> tableColumnContatoNome;
  @FXML
  private TableColumn<Contato, String> tableColumnContatoTelefone;
  @FXML
  private TableView<Contato> tableViewContatos;

  // Lista observavel de Contato
  private ObservableList<Contato> observableListContatos = FXCollections.observableArrayList();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    carregarTableViewContatos();
    carregarInfoBotoes();
    FilteredList<Contato> dadosFiltrados = new FilteredList<>(observableListContatos, b -> true);
    // adiciona um ouvidor ao campo de busca
    pesquisaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      dadosFiltrados.setPredicate(contato -> {
        // Se nao houver nenhum texto no campo de busca, eh mostrado todas as pessoas
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }
        // Compara o primeiro e o ultimo nome de cada pessoa com o filtro de texto

        String filtroMinusculas = newValue.toLowerCase();

        if (contato.getNome().toLowerCase().indexOf(filtroMinusculas) != -1) {
          return true; // Filtro eh igual ao primeiro nome
        } else if (contato.getTelefone().toLowerCase().indexOf(filtroMinusculas) != -1) {
          return true; // Filtro eh igual ao telefone
        } else if (contato.getEmail().toLowerCase().indexOf(filtroMinusculas) != -1)
          return true;
        else
          return false; // Nao eh igual
      });
    });

    // Coloca o FilteredList em um SortedList.
    SortedList<Contato> sortedData = new SortedList<>(dadosFiltrados);

    // Vincula o comparador SortedList ao comparador TableView.
    // Caso contrario, a classificacao do TableView nao teria efeito.
    sortedData.comparatorProperty().bind(tableViewContatos.comparatorProperty());

    // Adiciona os dados filtrados a tabela
    tableViewContatos.setItems(sortedData);
  }

  // metodo que insere os dados na tabela
  public void carregarTableViewContatos() {

    // informando as colunas contatoNome que o atributo a ser exibido sera nome. O
    // mesmo eh feito nas colunas restantes
    tableColumnContatoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    tableColumnContatoTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    tableColumnContatoEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    tableViewContatos.setItems(observableListContatos);
  }

  // Ao clicar no botao da lixeira o metodo abaixo remove um contato quando ele eh
  // selecionado com as teclas direcionais ou clicado no mouse.
  @FXML
  public void removerItemTableViewContatos() {
    // O objeto retornado pelo metodo getSelectedItem eh obtido por uma
    // observableList que eh passada como parametro no metodo setItems dentro do
    // initialize
    Contato contato = tableViewContatos.getSelectionModel().getSelectedItem();
    if (contato instanceof Contato) {
      observableListContatos.remove(contato);
    } else {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erro!");
      alert.setHeaderText("Verifique se não há um contato selecionado ou se todos já foram removidos!");
      alert.show();
    }
  }

  // metodo abaixo faz uma conversao (casting) para stage da janela obtida nos
  // metodos
  // getScene e getWindow e depois o fecha com o metodo close
  public void fecharButtonPress() {
    Stage stage = (Stage) buttonFechar.getScene().getWindow();
    stage.close();
  }

  // o metodo abaixo permite minimizar a aplicacao apos o botao minimizar ser
  // clicado. Os metodos getScene e getWindow obteem a janela atual para que o
  // metodo setIconified a minimize
  public void minimizarButtonPress() {
    buttonMinimizar.setOnAction(e -> {
      ((Stage) ((Button) e.getSource()).getScene().getWindow()).setIconified(true);
    });
  }

  // O metodo abaixo define uma dica a ser exibida sobre um botao assim que o
  // mouse passa por cima dele
  public void carregarInfoBotoes() {
    buttonFechar.setTooltip(new Tooltip("Fechar"));
    buttonAdicionar.setTooltip(new Tooltip("Adicionar contato"));
    buttonRemover.setTooltip(new Tooltip("Remover contato"));
    buttonMinimizar.setTooltip(new Tooltip("Minimizar"));
  }

  // O metodo abaixo adiciona um novo contato a observableList de contatos
  @FXML
  public void addContato() {
    String nome = nomeTextField.getText();
    String telefone = telefoneTextField.getText();
    String email = emailTextField.getText();
    // O if abaixo verifica se o usuario inseriu todos os dados necessarios para
    // adicionar um novo contato
    if (!nome.equals("") && !telefone.equals("") && !email.equals("")) {
      Contato contato = new Contato(nome, telefone, email);
      observableListContatos.add(contato);
      // Apos adicionar um novo usuario eh removido o texto inserido nos 3 TextFields
      nomeTextField.clear();
      telefoneTextField.clear();
      emailTextField.clear();
      // e o TextField de nome eh selecionado, ja que eh o primeiro
      nomeTextField.requestFocus();
    } else {
      // Caso o usuario nao preencha os 3 campos de texto, um alerta sera exibido
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Dados incompletos!");
      alert.setHeaderText("Insira todos os dados necessários!");
      alert.show();
    }
  }
}
