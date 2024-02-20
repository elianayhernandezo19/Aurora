package shop.develop.aurorashop;
import javafx.scene.layout.Pane;
import shop.develop.aurorashop.model.*;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.beans.property.SimpleStringProperty;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import shop.develop.aurorashop.model.DetalleVenta;
import shop.develop.aurorashop.model.Perecedero;

public class ShopBackend {
        @FXML public Pane paneAdd, modalPaneOfVenta;
        @FXML public AnchorPane pageHome, ventasContainer, productosContainer, clientesContainer, clientesDataPage, clientesRegisterPage, productosDataPage, productosRegisterPage, ventasDataPage, ventasRegisterPage;
        @FXML public HBox formEmail, formFechaNacimiento, boxCodigo, boxTemperatura, boxDate, boxPais;
        @FXML public ChoiceBox<String> typeClienteSelect;
        @FXML public ChoiceBox<Pais> boxPaisSelect;
        @FXML public RadioButton selectPerece, selectRefri, selectEnla;
        @FXML private Label idLabel,codLabel,tempLabel, dateLabelProducto;
        @FXML public TextField amountAdd, searchProductosVenta, searchClienteVenta, valueProductoField, tempField, stockField, idInput, nombreInput, apellidoInput, direccionInput, telefonoInput, emailInput, searchTextField, idProducto, codigoTextField, productoField, searchFieldProductos;
        @FXML public DatePicker fechaNacimientoInput, datePick;
        @FXML public TextArea detailField;
        @FXML public TableView<Cliente> tableClientes;
        @FXML private TableColumn<Cliente, String> columnId, columnNombre, columnApellido, columnTelefono, columnDireccion;
        @FXML public TableView<Producto> tableViewProductos;
        @FXML private TableColumn<Producto, String> codigoCol, productoCol, typeCol;
        @FXML private TableColumn<Producto, Integer> stockCol;
        @FXML private TableColumn<Producto, Double> valueCol;
        @FXML public TableView<DetalleVenta> tableVentas;
        @FXML public TableColumn<DetalleVenta, Integer> columnAmount;
        @FXML public TableColumn<DetalleVenta, String> columnDescripcion;
        @FXML public TableColumn<DetalleVenta, Double> columnPrice, columnAccumulated;
        @FXML public TableView<Producto> tableAddProductosVenta;
        @FXML public TableColumn<Producto, String> idProductosVenta, titleVenta;
        @FXML public TableColumn<Producto, Double> priceProductosVenta;
        @FXML private TableColumn<Producto, Integer> stockProductosVenta;
        @FXML public Button btnProductoSearch, closeModalVenta, closeModal, backButtonData, backButtonRegister, updateButtonCliente, deleteButonCliente, addCliente,
                updateButtonVentas, updateButtonProducto, deleteButonProducto, addProducto, closeModalProductos;
        @FXML public Label labelVentaNombre, labelVentaId, labelVentaDireccion, labelVentaTelefono, labelVentaDate, labelSerie, labelSubTotal,
                labelIva, labelTotal, titleAdd, idAdd, stockAdd;
        //Productos, clientes y venta List

        public ObservableList<Venta> listVenta = FXCollections.observableArrayList();
        public ObservableList<DetalleVenta> listDetalle = FXCollections.observableArrayList();
        public ObservableList<Cliente> listCliente = FXCollections.observableArrayList();
        public ObservableList<Producto> listProducto = FXCollections.observableArrayList();
        public ObservableList<Pais> listPaises = FXCollections.observableArrayList();

        //Set values at the component BoxChoice with fx:id = 'typeClientSelect'
        public void initialize() {
            resetVisibleApp();//Seteo por defecto de la App

            getClienteList();
            tableClientes.setItems(listCliente);

            getProductoList();
            tableViewProductos.setItems(listProducto);

            getConfigurationProductosVenta();
            tableAddProductosVenta.setItems(listProducto);

            getConfigurationDetalleVenta();
            tableVentas.setItems(listDetalle);

            typeClienteSelect.getItems().addAll("Natural", "Juridica");
            typeClienteSelect.setValue("Natural");
            typeClienteSelect.setOnAction( event -> dynamicForm());

            boxPaisSelect.getItems().addAll(Pais.values());
            boxPaisSelect.setValue(Pais.ARGENTINA);

            searchTextField.setOnKeyReleased(event -> {
                String searchQuery = searchTextField.getText();
                filterAndShowMatches(searchQuery);
            });

            searchFieldProductos.setOnKeyReleased(event -> {
                String searchQuery = searchFieldProductos.getText();
                filterAndShowMatchesProducto(searchQuery);
            });

            tableClientes.setRowFactory(tv -> selectItemRowCliente());
            tableViewProductos.setRowFactory(tv -> selectItemRowProducto());
            tableAddProductosVenta.setRowFactory(tv -> selectItemRowProductoVenta());

            ToggleGroup toggleGroup = new ToggleGroup();
            selectPerece.setToggleGroup(toggleGroup);
            selectRefri.setToggleGroup(toggleGroup);
            selectEnla.setToggleGroup(toggleGroup);
            selectPerece.setOnAction(event -> dynamicFormProductos());
            selectRefri.setOnAction(event -> dynamicFormProductos());
            selectEnla.setOnAction(event -> dynamicFormProductos());
        }

        public void resetVisibleApp(){
            pageHome.setVisible(true);

            ventasContainer.setVisible(false);
            ventasDataPage.setVisible(true);
            ventasRegisterPage.setVisible(false);

            productosContainer.setVisible(false);
            productosDataPage.setVisible(true);
            productosRegisterPage.setVisible(false);

            clientesContainer.setVisible(false);
            clientesDataPage.setVisible(true);
            clientesRegisterPage.setVisible(false);
        }

        public void resetFormClienteRegister(){
            closeModal.setVisible(false);
            updateButtonCliente.setVisible(false);
            deleteButonCliente.setVisible(false);
            addCliente.setVisible(true);

            typeClienteSelect.setDisable(false);
            idInput.setDisable(false);
            nombreInput.setDisable(false);
            apellidoInput.setDisable(false);

            typeClienteSelect.setValue("Natural");
            idInput.setText("");
            nombreInput.setText("");
            apellidoInput.setText("");
            direccionInput.setText("");
            telefonoInput.setText("");
            emailInput.setText("");
            fechaNacimientoInput.setValue(null);
        }
        public void resetFormClienteUpdate(Cliente cliente){
            closeModal.setVisible(true);
            updateButtonCliente.setVisible(true);
            deleteButonCliente.setVisible(true);
            addCliente.setVisible(false);

            backButtonRegister.setDisable(true);
            backButtonData.setDisable(true);
            typeClienteSelect.setDisable(true);
            idInput.setDisable(true);
            nombreInput.setDisable(true);
            apellidoInput.setDisable(true);

            if(cliente instanceof Natural){
                typeClienteSelect.setValue("Natural");
                emailInput.setText(((Natural) cliente).getEmail());
                fechaNacimientoInput.setValue(((Natural) cliente).getFechaNacimiento());
            }else {
                typeClienteSelect.setValue("Juridica");
            }

            idInput.setText(cliente.getId());
            nombreInput.setText(cliente.getNombre());
            apellidoInput.setText(cliente.getApellido());
            direccionInput.setText(cliente.getDireccion());
            telefonoInput.setText(cliente.getTelefono());
        }

        //Form alerts and validation
        public void dynamicForm(){
            if ("Natural".equals(typeClienteSelect.getValue())) {
                idLabel.setText("Cedula:");
                idInput.setPromptText("Numero de documento del cliente");
                formEmail.setVisible(true);
                formFechaNacimiento.setVisible(true);
            } else {
                idLabel.setText("NIT:");
                idInput.setPromptText("Nit del client");
                formEmail.setVisible(false);
                formFechaNacimiento.setVisible(false);
            }
        }
        public static boolean isValidEmail(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        public void alertFromCliente(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Formulario de validacion");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public void alertNomatch(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Alerta: Sin coincidencia");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        public void alertUpdateDelete(String message) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alerta: Sin coincidencia");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        //Create, Read, Search, Delete and Update Client Data
        public void addClienteList() {
            if ("Natural".equals(typeClienteSelect.getValue())) {
                Natural newCliente = new Natural(idInput.getText(), nombreInput.getText(), apellidoInput.getText(),
                        direccionInput.getText(), telefonoInput.getText(), emailInput.getText(), fechaNacimientoInput.getValue());
                listCliente.add(newCliente);
            } else {
                Juridica newCliente = new Juridica(idInput.getText(), nombreInput.getText(), apellidoInput.getText(),
                        direccionInput.getText(), telefonoInput.getText());
                listCliente.add(newCliente);
            }
        }

        public void getClienteList(){
            columnId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
            columnNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
            columnApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
            columnTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
            columnDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        }

        private void filterAndShowMatches(String searchQuery) {
            if (searchQuery.isEmpty()){
                tableClientes.setItems(listCliente);
            } else {
                ObservableList<Cliente> filteredClientes = FXCollections.observableArrayList();

                for (Cliente cliente : listCliente) {
                    if (cliente.getId().startsWith(searchQuery)) {
                        filteredClientes.add(cliente);
                    }
                }
                tableClientes.setItems(filteredClientes);
            }
        }

        public TableRow<Cliente> selectItemRowCliente(){
            TableRow<Cliente> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Cliente clickedCliente = row.getItem();

                    for (Cliente cliente:listCliente) {
                        if (clickedCliente.getId().equals(cliente.getId())){
                            clientesRegisterPage.setVisible(true);
                            resetFormClienteUpdate(cliente);
                            break;
                        }
                    }
                }

            });
            return row;
        }

        public void updateCliente(){
            listCliente.forEach(cliente -> {
                if (cliente instanceof Natural && idInput.getText().equals(cliente.getId())) {
                    Natural natural = (Natural) cliente;
                    Natural update = new Natural(idInput.getText(), nombreInput.getText(), apellidoInput.getText(),
                            telefonoInput.getText(), direccionInput.getText(), emailInput.getText(), fechaNacimientoInput.getValue());
                    listCliente.set(listCliente.indexOf(natural), update);

                } else if (cliente instanceof Juridica && idInput.getText().equals(cliente.getId())) {
                    Juridica juridica = (Juridica) cliente;
                    Juridica update = new Juridica(idInput.getText(), nombreInput.getText(), apellidoInput.getText(),
                            telefonoInput.getText(), direccionInput.getText());
                    listCliente.set(listCliente.indexOf(juridica), update);
                }
            });
        }

        public void deleteCliente(String id){
            listCliente.removeIf(cliente -> id.equals(cliente.getId()));
            alertUpdateDelete("Se a eliminado con exitosamente");
        }

        ///////////////CRUD VENTAS///////////////
        public void getConfigurationProductosVenta(){
            idProductosVenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProducto()));
            titleVenta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
            priceProductosVenta.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));
            stockProductosVenta.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStock()));
        }
        public void getConfigurationDetalleVenta() {
            columnAmount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAmount()));
            columnDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItem().getDescripcion()));
            columnPrice.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getItem().getValue()));
            columnAccumulated.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getSubTotal()));
        }
        public void resetFormVentasRegister(){
            labelVentaId.setText("");
            labelVentaNombre.setText("");
            labelVentaTelefono.setText("");
            labelVentaDireccion.setText("");
            labelVentaDate.setText("");
            labelSerie.setText("");

            labelSubTotal.setText("");
            labelIva.setText("");
            labelTotal.setText("");
        }

        public static Cliente filterCliente(ObservableList<Cliente> clienteList, String id) {
            return clienteList.stream()
                    .filter(cliente -> cliente.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        public Double calculateAmount(double priceItem, int stock){return priceItem * stock;}

        public Double calculateSubTotal(double total){return total/1.19;}

        public void addProductoVenta(Producto producto, int amount){
            DetalleVenta detalleVenta = new DetalleVenta(amount, calculateAmount(producto.getValue(), amount), producto);
            listDetalle.add(detalleVenta);
        }

        public TableRow<Producto> selectItemRowProductoVenta(){
            TableRow<Producto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Producto clickedProducto = row.getItem();
                    for (Producto producto:listProducto) {
                        if (clickedProducto.getIdProducto().equals(producto.getIdProducto())){
                            paneAdd.setVisible(true);
                            titleAdd.setText(producto.getTitle());
                            idAdd.setText(producto.getIdProducto());
                            stockAdd.setText(String.valueOf(producto.getStock()));
                            break;
                        }
                    }
                }

            });
            return row;
        }

        public static <Type> Boolean existIntoArray(String idItem, ObservableList<Type> list, Function<Type, String> idList){
            return list.stream().anyMatch(idExist -> idList.apply(idExist).equals(idItem));
        }


        ///////////////CRUD PRODUCTOS///////////////

        public void dynamicFormProductos(){
            if (selectPerece.isSelected()) {
                boxCodigo.setVisible(false);
                boxTemperatura.setVisible(false);
                dateLabelProducto.setText("Vencimiento:");
                boxPais.setVisible(false);
                boxDate.setVisible(true);
            }else if(selectRefri.isSelected()) {
                boxCodigo.setVisible(true);
                boxTemperatura.setVisible(true);
                boxDate.setVisible(false);
                boxCodigo.setVisible(true);
                codLabel.setText("Cod. Aprovacion:");
                codigoTextField.setPromptText("Cod. Aprovacion");
                boxPais.setVisible(false);
            }else if(selectEnla.isSelected()){
                dateLabelProducto.setText("Envasado:");
                boxCodigo.setVisible(true);
                codLabel.setText("Peso:");
                boxDate.setVisible(true);
                codigoTextField.setPromptText("Peso del producto");
                boxPais.setVisible(true);
                boxTemperatura.setVisible(false);
            }
        }

        public void resetFormProductoRegister(){idProducto.setDisable(false);
            productoField.setDisable(false);
            detailField.setDisable(false);
            addProducto.setVisible(true);
            selectPerece.setDisable(false);
            selectRefri.setDisable(false);
            selectEnla.setDisable(false);
            idProducto.setText("");
            productoField.setText("");
            detailField.setText("");
            valueProductoField.setText("");
            stockField.setText("");
            codigoTextField.setText("");
            tempField.setText("");
            datePick.setValue(null);
            selectPerece.setSelected(true);
            boxDate.setVisible(true);
            boxTemperatura.setVisible(false);
            boxCodigo.setVisible(false);
            boxPais.setVisible(false);
            boxPaisSelect.setValue(Pais.ARGENTINA);
            dateLabelProducto.setText("Vencimiento:");
            updateButtonProducto.setVisible(false);
            deleteButonProducto.setVisible(false);
        }

        public void resetFormProductoUpdate(Producto producto){
            backButtonData.setDisable(true);
            updateButtonProducto.setVisible(true);
            deleteButonProducto.setVisible(true);
            addProducto.setVisible(false);
            selectPerece.setDisable(true);
            selectRefri.setDisable(true);
            selectEnla.setDisable(true);

            idProducto.setText(producto.getIdProducto());
            idProducto.setDisable(true);
            productoField.setText(producto.getTitle());
            productoField.setDisable(true);
            detailField.setText(producto.getDescripcion());
            detailField.setDisable(true);
            stockField.setText(String.valueOf(producto.getStock()));
            valueProductoField.setText(String.valueOf(producto.getValue()));
            idInput.setDisable(true);
            nombreInput.setDisable(true);
            apellidoInput.setDisable(true);
            if(producto instanceof Perecedero){
                selectPerece.setSelected(true);
                dynamicFormProductos();
                datePick.setValue(((Perecedero) producto).getFechaVencimiento());
                datePick.setDisable(true);
            }else if(producto instanceof Refrigerado) {
                selectRefri.setSelected(true);
                dynamicFormProductos();
                tempField.setText(String.valueOf(((Refrigerado) producto).getTemperatura()));
                codigoTextField.setText(((Refrigerado) producto).getCodigoAprovacion());
            }else if(producto instanceof Enlatado) {
                selectEnla.setSelected(true);
                dynamicFormProductos();
                datePick.setValue(((Enlatado) producto).getFechaEnvasado());
                codigoTextField.setText(String.valueOf(((Enlatado) producto).getPeso()));
            }
        }

        public TableRow<Producto> selectItemRowProducto(){
            TableRow<Producto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Producto clickedProducto = row.getItem();
                    for (Producto producto:listProducto) {
                        if (clickedProducto.getIdProducto().equals(producto.getIdProducto())){
                            productosRegisterPage.setVisible(true);
                            resetFormProductoUpdate(producto);
                            break;
                        }
                    }
                }

            });
            return row;
        }
        public void addProductoList(){
            if (selectPerece.isSelected()) {
                Perecedero newProducto = new Perecedero(idProducto.getText(), productoField.getText(), "Perecedero",
                        Integer.valueOf(stockField.getText()), Double.valueOf(valueProductoField.getText()), datePick.getValue());
                listProducto.add(newProducto);
                System.out.println("agregado perecedero");
            } else if(selectRefri.isSelected()){
                Refrigerado newProducto = new Refrigerado(idProducto.getText(), productoField.getText(), "Refrigerado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductoField.getText()), codigoTextField.getText(), Double.valueOf(tempField.getText()));
                listProducto.add(newProducto);
                System.out.println("agregado refrigerado");
            } else if(selectEnla.isSelected()){
                Enlatado newProducto = new Enlatado(idProducto.getText(), productoField.getText(), "Envasado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductoField.getText()), datePick.getValue(), Double.valueOf(codigoTextField.getText()), boxPaisSelect.getValue());
                listProducto.add(newProducto);
                System.out.println("agregado enlatado");
            }
        }

        public void deleteProducto(String id){
            listProducto.removeIf(producto -> id.equals(producto.getIdProducto()));
            productosRegisterPage.setVisible(false);
            alertUpdateDelete("Se ha eliminado con exitosamente");
        }

        public void updateProducto(){
            listProducto.forEach(producto -> {
                if (producto instanceof Perecedero && idProducto.getText().equals(producto.getIdProducto())) {
                    Perecedero perecedero = (Perecedero) producto;
                    Perecedero update = new Perecedero(idProducto.getText(), productoField.getText(), "Perecedero",
                            Integer.valueOf(stockField.getText()), Double.valueOf(valueProductoField.getText()), datePick.getValue());
                    listProducto.set(listProducto.indexOf(perecedero), update);
                } else if (producto instanceof Refrigerado && idProducto.getText().equals(producto.getIdProducto())){
                    Refrigerado refrigerado = (Refrigerado) producto;
                    Refrigerado update = new Refrigerado(idProducto.getText(), productoField.getText(), "Refrigerado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductoField.getText()), codigoTextField.getText(), Double.valueOf(tempField.getText()));
                    listProducto.set(listProducto.indexOf(refrigerado), update);
                }else if (producto instanceof Refrigerado && idProducto.getText().equals(producto.getIdProducto())){
                    Enlatado refrigerado = (Enlatado) producto;
                    Enlatado update = new Enlatado (idProducto.getText(), productoField.getText(), "Envasado", Integer.valueOf(stockField.getText()), Double.valueOf(valueProductoField.getText()), datePick.getValue(), Double.valueOf(codigoTextField.getText()), boxPaisSelect.getValue());
                    listProducto.set(listProducto.indexOf(refrigerado), update);
                }
            });
            productosRegisterPage.setVisible(false);
        }

        public void getProductoList(){
            codigoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdProducto()));
            productoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
            typeCol.setCellValueFactory(cellData -> new SimpleStringProperty(getTypeProducto(cellData.getValue())));
            stockCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStock()));
            valueCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getValue()));
        }

        public String getTypeProducto(Producto producto){
            String nombre = "";
            if(producto instanceof Perecedero){
                return "Perecedero";
            }else if(producto instanceof Refrigerado){
                return "Refrigerado";
            }else if(producto instanceof Enlatado){
                return "Envasado";
            }
            return nombre;
        }

        public void filterAndShowMatchesProducto(String searchQuery) {
            if (searchQuery.isEmpty()){
                tableViewProductos.setItems(listProducto);
            } else {
                ObservableList<Producto> filteredProducto = FXCollections.observableArrayList();

                for (Producto producto : listProducto) {
                    if (producto.getIdProducto().startsWith(searchQuery)) {
                        filteredProducto.add(producto);
                    }
                }
                tableViewProductos.setItems(filteredProducto);
            }
        }

        public static Producto filterProducto(ObservableList<Producto> listProducto, String id) {
            return listProducto.stream()
                    .filter(producto -> producto.getIdProducto().equals(id))
                    .findFirst()
                    .orElse(null);
        }

        public static <Type> Boolean existIntoArrayProducto(String idItem, ObservableList<Type> list, Function<Type, String> idList){
            return list.stream().anyMatch(idExist -> idList.apply(idExist).equals(idItem));
        }
}