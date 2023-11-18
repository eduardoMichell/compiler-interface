module com.example.compilerinterface {
    opens parser to javafx.base;

    requires javafx.controls;
    requires javafx.fxml;


    opens compiler to javafx.fxml;
    exports compiler;
}