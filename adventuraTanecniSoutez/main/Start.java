/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package main;



import gui.HerniPlocha;
import gui.PanelBatohu;
import gui.PanelVychodu;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import logika.*;
import uiText.TextoveRozhrani;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import util.Observer;

import java.util.HashSet;
import java.util.List;
import java.util.Random;


/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public class Start extends Application  {

    private final IHra hra = new Hra();


    /***************************************************************************
     * Metoda, jejímž prostřednictvím se spouští celá aplikace.
     *
     * @param args parametry příkazového řádku
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-gui")) {
            Application.launch();
        } else {
            IHra hra = new Hra();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = new MenuBar();

        Menu soubor = new Menu("Soubor");
        Menu napoveda = new Menu("Napoveda");
        Menu tajne = new Menu("Tajné");
        tajne.setVisible(false);



        MenuItem novaHraMenuItem = new MenuItem("Nová hra");
        novaHraMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem ukonciHruMenuItem = new MenuItem("Ukončit hru");
        MenuItem teleportMenuItem = new MenuItem("Teleport");
        MenuItem napovedaMenuItem = new MenuItem("Napoveda");

        novaHraMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Platform.runLater(() -> {
                   Stage newStage = new Stage();
                   try {
                       Start start = new Start();
                       start.start(newStage);
                       primaryStage.close();
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               });
            }
        });
        ukonciHruMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        teleportMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashSet<Prostor> vsechnyProstory =hra.getHerniPlan().getVsechnyProstory();
                int nahoda = new Random().nextInt(vsechnyProstory.size());
                Prostor[] prostory = vsechnyProstory.toArray(new Prostor[vsechnyProstory.size()]);
                hra.getHerniPlan().setAktualniProstor(prostory[nahoda]);
            }
        });

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        Stage webStage = new Stage();
        Scene webviewScene = new Scene(webView,600,600);
        napovedaMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                webEngine.load(getClass().getResource("/napoveda.html").toString());
                webStage.setScene(webviewScene);
                webStage.setTitle("O hře");
                webStage.show();

            }
        });

        soubor.getItems().addAll(novaHraMenuItem, ukonciHruMenuItem, teleportMenuItem);
        napoveda.getItems().addAll(napovedaMenuItem);
        menuBar.getMenus().addAll(soubor, napoveda, tajne);

        HerniPlocha herniPlocha = new HerniPlocha(hra.getHerniPlan(),tajne);

        VBox herniPlochaAndMenuBar = new VBox();
        herniPlochaAndMenuBar.getChildren().addAll(menuBar,herniPlocha.getAnchorPane());
        borderPane.setTop(herniPlochaAndMenuBar);

        TextArea konzole = new TextArea();
        borderPane.setCenter(konzole);
        konzole.setText(hra.vratUvitani());
        konzole.setEditable(false);

        PanelVychodu panelVychodu = new PanelVychodu(hra,konzole);
        ListView<String> listView = panelVychodu.getListView();
        borderPane.setRight(listView);

        Label popisek = new Label("Zadej příkaz: ");

        TextField uzivatelskyVstup = new TextField();
        HBox spodniBox = new HBox(popisek, uzivatelskyVstup);

        spodniBox.setAlignment(Pos.CENTER);


        borderPane.setBottom(spodniBox);

        PanelBatohu panelBatohu = new PanelBatohu(hra.getHerniPlan().getBatoh());
        borderPane.setLeft(panelBatohu.getPanel());
        uzivatelskyVstup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String prikaz = uzivatelskyVstup.getText();
                String odpoved = hra.zpracujPrikaz(prikaz);
                konzole.appendText("\n" + odpoved + "\n");

                uzivatelskyVstup.clear();
            }
        });



        Scene scene = new Scene(borderPane, 750, 750);
        uzivatelskyVstup.requestFocus();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

