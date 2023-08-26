module com.example.compilerinterface {
    requires javafx.controls;
    requires javafx.fxml;


    opens compiler to javafx.fxml;
    exports compiler;
}