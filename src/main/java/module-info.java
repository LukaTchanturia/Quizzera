module com.example.quizzera {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires jdk.jconsole;
    requires okhttp3;
    requires org.json;


    opens com.example.quizzera to javafx.fxml;
    exports com.example.quizzera;
}