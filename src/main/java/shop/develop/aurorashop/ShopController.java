package shop.develop.aurorashop;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import shop.develop.aurorashop.model.Cliente;
import shop.develop.aurorashop.model.Producto;

import java.time.LocalDate;

public class ShopController extends ShopBackend {

    //Function for close the application
    public void clickCloseButton(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void clickBackButton(ActionEvent event){
        if(ventasContainer.isVisible()){
            if(ventasDataPage.isVisible()){
                ventasContainer.setVisible(false);
                pageHome.setVisible(true);
            }
            if(ventasRegisterPage.isVisible()){
                ventasRegisterPage.setVisible(false);
                ventasDataPage.setVisible(true);
            }
        }
        if(productosContainer.isVisible()){
            if(productosDataPage.isVisible()){
                productosContainer.setVisible(false);
                pageHome.setVisible(true);
            }
            if(productosRegisterPage.isVisible()){
                productosRegisterPage.setVisible(false);
                productosDataPage.setVisible(true);
            }
        }
        if(clientesContainer.isVisible()){
            if(clientesDataPage.isVisible()){
                clientesContainer.setVisible(false);
                pageHome.setVisible(true);
            }
            if(clientesRegisterPage.isVisible()){
                clientesRegisterPage.setVisible(false);
                clientesDataPage.setVisible(true);
            }
        }
    }

    public void addProductoModal(ActionEvent event){
        modalPaneOfVenta.setVisible(true);
    }
    public void closeProductoModal(ActionEvent event){
        if (clientesRegisterPage.isVisible()){
            clientesRegisterPage.setVisible(false);
            backButtonData.setDisable(false);
            backButtonRegister.setDisable(false);
        }else {
            modalPaneOfVenta.setVisible(false);
        }
    }
    public void closeAdd(ActionEvent event){
        paneAdd.setVisible(false);
    }


    //Routes Buttons: Ventas, Clientes y Productos
    public void clickVentasButton(ActionEvent event){
        pageHome.setVisible(false);
        ventasContainer.setVisible(true);
    }
    public void clickProductosButton(ActionEvent event){
        pageHome.setVisible(false);
        productosContainer.setVisible(true);
    }
    public void clickClientesButton (ActionEvent event){
        pageHome.setVisible(false);
        clientesContainer.setVisible(true);
    }

    //Register Buttons: Sales, Clients and Products
    public void clickRegisterVenta(ActionEvent event){
        resetFormVentasRegister();
        ventasDataPage.setVisible(false);
        ventasRegisterPage.setVisible(true);
    }
    public void clickNewClienteVentas(ActionEvent event){
        ventasContainer.setVisible(false);
        ventasDataPage.setVisible(true);
        ventasRegisterPage.setVisible(false);
        clientesContainer.setVisible(true);
        clientesDataPage.setVisible(false);
        clientesRegisterPage.setVisible(true);
    }
    public void clickRegisterProducto(ActionEvent event){
        resetFormProductoRegister();
        productosDataPage.setVisible(false);
        productosRegisterPage.setVisible(true);

    }
    public void clickRegisterCliente(ActionEvent event){
        resetFormClienteRegister();
        clientesDataPage.setVisible(false);
        clientesRegisterPage.setVisible(true);
    }

    //Clients Button CRUD
    public void clickAddCliente(ActionEvent event){
        boolean isNaturalCliente = "Natural".equals(typeClienteSelect.getValue());

        if (idInput.getText().isEmpty()) {
            alertFromCliente("El " + (isNaturalCliente ? "documento" : "nit") + " del cliente es requerido.");
        } else if (nombreInput.getText().isEmpty()) {
            alertFromCliente("El campo de Nombre es requerido.");
        } else if (apellidoInput.getText().isEmpty()) {
            alertFromCliente("El campo de Apellido es requerido.");
        } else if (direccionInput.getText().isEmpty()) {
            alertFromCliente("La direccion es requerida.");
        } else if (telefonoInput.getText().isEmpty()) {
            alertFromCliente("El numero de contacto es requerido.");
        } else if (isNaturalCliente && (emailInput.getText().isEmpty() && formEmail.isVisible())) {
            alertFromCliente("El email es requerido.");
        } else if (isNaturalCliente && (fechaNacimientoInput.getValue() == null && formFechaNacimiento.isVisible())) {
            alertFromCliente("Debe seleccionar la fecha de nacimiento.");
        } else if (isNaturalCliente && (!isValidEmail(emailInput.getText()) && formEmail.isVisible())) {
            alertFromCliente("Debe ingresar una direccion de correo valida.");
        } else if (existIntoArray(idInput.getText(), listCliente, Cliente::getId)) {
            alertNomatch("El id: " + idInput.getText() + " Ya existe en el sistema");
        } else {
            alertFromCliente("Se ha registrado exitosamente!");
            addClienteList();
            resetFormClienteRegister();
        }
    }

    public void clickSearchCliente(ActionEvent event){
        boolean idExists = false;

        for (Cliente cliente : listCliente) {
            if (cliente.getId().equals(searchTextField.getText())) {
                idExists = true;
                break;
            }
        }

        if (!idExists) {
            alertNomatch("El cliente con el Id/Nit " + searchTextField.getText() + " no existe en el sistema.");
        }
        searchTextField.setText("");
        tableClientes.setItems(listCliente);
    }
    public void clickUpdateCliente(ActionEvent event){
        boolean isNaturalCliente = "Natural".equals(typeClienteSelect.getValue());

        if (direccionInput.getText().isEmpty()) {
            alertFromCliente("La direccion es requerida.");
        } else if (telefonoInput.getText().isEmpty()) {
            alertFromCliente("El numero de contacto es requerido.");
        } else if (isNaturalCliente && (emailInput.getText().isEmpty() && formEmail.isVisible())) {
            alertFromCliente("El email es requerido.");
        } else if (isNaturalCliente && (fechaNacimientoInput.getValue() == null && formFechaNacimiento.isVisible())) {
            alertFromCliente("Debe seleccionar la fecha de nacimiento.");
        } else if (isNaturalCliente && (!isValidEmail(emailInput.getText()) && formEmail.isVisible())) {
            alertFromCliente("Debe ingresar una direccion de correo valida.");
        } else {
            updateCliente();
            alertUpdateDelete("Se a actualizado exitosamente");
        }
    }

    public void clickDeleteCliente(ActionEvent event){
        deleteCliente(idInput.getText());
    }

    //Controls Sales
    public void clickSearchClienteVenta(ActionEvent event){
        int lastElement = 1;
        Cliente ventaCliente = filterCliente(listCliente, searchClienteVenta.getText());
        System.out.println("ventaCliente = " + ventaCliente);
        LocalDate createAt = LocalDate.now();

        if (!listVenta.isEmpty()) {
            lastElement = listVenta.size() + 1;
        }

        if(ventaCliente != null) {
            labelVentaId.setText(ventaCliente.getId());
            labelVentaNombre.setText(ventaCliente.getNombre() + " " + ventaCliente.getApellido());
            labelVentaTelefono.setText(ventaCliente.getTelefono());
            labelVentaDireccion.setText(ventaCliente.getDireccion());
            labelVentaDate.setText(createAt.toString());
            labelSerie.setText(String.format("%4d", lastElement));
        }else {
            alertNomatch("El cliente " + searchClienteVenta.getText() + " no se encuentra registrado en el sistema.");
            searchClienteVenta.setText("");
        }
    }

    public void clickAddProductoVenta(ActionEvent event){
        paneAdd.setVisible(false);
        modalPaneOfVenta.setVisible(false);
        for (Producto producto : listProducto) {
            if (producto.getIdProducto().equals(idAdd.getText())) {
                addProductoVenta(producto, Integer.parseInt(amountAdd.getText()));
            }
        }
    }

    public void clickAddProducto(ActionEvent event){
        addProductoList();
        resetFormProductoRegister();
    }

    public void clickUpdateProducto(ActionEvent event){
        updateProducto();
        alertUpdateDelete("Se ha actualizado exitosamente");
    }

    public void clickDeleteProducto(ActionEvent event){
        deleteProducto(idProducto.getText());
    }

    public void clickSearchProducto(ActionEvent event){
        boolean idExists = false;

        for (Producto producto : listProducto) {
            if (producto.getIdProducto().equals(searchFieldProductos.getText())) {
                idExists = true;
                break;
            }
        }

        if (!idExists) {
            alertNomatch("El producto con el Id " + searchFieldProductos.getText() + " no existe en el sistema.");
        }
        tableViewProductos.setItems(listProducto);
        searchFieldProductos.setText("");
    }
}
