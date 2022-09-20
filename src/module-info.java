module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    opens sample to javafx.graphics, javafx.fxml;
    opens util to javafx.graphics, javafx.fxml,javafx.base;
    opens RawCode to javafx.fxml, javafx.graphics;
    opens server to javafx.base, javafx.fxml, javafx.graphics;
}