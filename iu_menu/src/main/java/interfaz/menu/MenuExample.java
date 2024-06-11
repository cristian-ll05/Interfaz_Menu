package interfaz.menu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MenuExample extends Application {

    private BorderPane borderPane;
    private TextArea textArea;

    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        primaryStage.setTitle("Menu Example");

        // Create a BorderPane as the main layout
        borderPane = new BorderPane();

        // Create a MenuBar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("Archivo");
        Menu editMenu = new Menu("Editar");
        Menu helpMenu = new Menu("Ayuda");

        // Create menu items
        MenuItem newItem = new MenuItem("Nuevo");
        MenuItem openItem = new MenuItem("Abrir");
        MenuItem saveItem = new MenuItem("Guardar");
        MenuItem exitItem = new MenuItem("Salir");
        MenuItem cutItem = new MenuItem("Cortar");
        MenuItem copyItem = new MenuItem("Copiar");
        MenuItem pasteItem = new MenuItem("Pegar");
        MenuItem aboutItem = new MenuItem("Acerca de...");

        // Add menu items to menus
        fileMenu.getItems().addAll(newItem, openItem, saveItem, new SeparatorMenuItem(), exitItem);
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);
        helpMenu.getItems().add(aboutItem);

        // Add menus to the MenuBar
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        // Set actions for menu items
        newItem.setOnAction(e -> createNewTextArea());
        openItem.setOnAction(e -> openFile());
        saveItem.setOnAction(e -> saveText());
        exitItem.setOnAction(e -> System.out.println("Salir seleccionado"));
        cutItem.setOnAction(e -> System.out.println("Cortar seleccionado"));
        copyItem.setOnAction(e -> System.out.println("Copiar seleccionado"));
        pasteItem.setOnAction(e -> System.out.println("Pegar seleccionado"));
        aboutItem.setOnAction(e -> System.out.println("Acerca de... seleccionado"));

        // Add the MenuBar to the BorderPane
        borderPane.setTop(menuBar);

        // Create a TextArea for writing
        textArea = new TextArea();
        textArea.setPrefSize(400, 300);
        borderPane.setCenter(textArea);

        // Create a scene and add it to the stage
        Scene scene = new Scene(borderPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createNewTextArea() {
        TextArea newTextArea = new TextArea();
        newTextArea.setPrefSize(400, 300);
        borderPane.setCenter(newTextArea);
        textArea = newTextArea;
    }

    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (FileReader reader = new FileReader(file)) {
                StringBuilder text = new StringBuilder();
                int character;
                while ((character = reader.read()) != -1) {
                    text.append((char) character);
                }
                textArea.setText(text.toString());
                System.out.println("Archivo abierto correctamente");
            } catch (IOException e) {
                System.out.println("Error al abrir el archivo: " + e.getMessage());
            }
        }
    }

    private void saveText() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("documento.txt");
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textArea.getText());
                System.out.println("Texto guardado correctamente");
            } catch (IOException e) {
                System.out.println("Error al guardar el texto: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}