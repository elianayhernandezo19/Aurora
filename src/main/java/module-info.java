module shop.develop.aurorashop {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.jfoenix;

    opens shop.develop.aurorashop to javafx.fxml;
    exports shop.develop.aurorashop;
}