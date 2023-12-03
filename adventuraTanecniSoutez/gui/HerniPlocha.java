package gui;

import javafx.scene.control.Menu;
import logika.HerniPlan;
import util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class HerniPlocha implements Observer {

    private AnchorPane anchorPane = new AnchorPane();

    private Image hrac = new Image(HerniPlocha.class.getClassLoader().getResourceAsStream("hrac.png"), 50, 50, false, false);
    ImageView hracImageView = new ImageView(hrac);
    private Image postava = new Image(HerniPlocha.class.getClassLoader().getResourceAsStream("partnerka.png"), 50, 50, false, false);
    ImageView postavaImageView = new ImageView(postava);

    private HerniPlan herniPlan;

    private ImageView imageView;
    private Menu tajnyItem;

    public HerniPlocha(HerniPlan plan, Menu tajnyItem) {
        this.herniPlan = plan;
        this.tajnyItem = tajnyItem;

        Image image = new Image(HerniPlocha.class.getClassLoader().getResourceAsStream("herniPlan.png"), 650  , 400,
                false, false);
        imageView = new ImageView(image);


        nastavPoziciHrace();
        postavaImageView.setVisible(false);

        anchorPane.getChildren().addAll(imageView, hracImageView, postavaImageView);
        //TODO Nastavit podle polohy byt_partnerky
        AnchorPane.setLeftAnchor(postavaImageView, 340.0);
        AnchorPane.setTopAnchor(postavaImageView,265.0 );
        herniPlan.registerObserver(this);
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    @Override
    public void update() {

        nastavPoziciHrace();
        if (herniPlan.getAktualniProstor().getNazev().equals("byt_partnerky")) {
            postavaImageView.setVisible(true);
        } else {
            postavaImageView.setVisible(false);
        }
        if(herniPlan.getAktualniProstor().getNazev().equals("soutěž")) {
            //TODO nastavit pos podle polohy soutěže
            postavaImageView.setVisible(true);
            AnchorPane.setLeftAnchor(postavaImageView, 430.0);
            AnchorPane.setTopAnchor(postavaImageView, 205.0);
        }
        if(herniPlan.isSecretMenu()) {
            tajnyItem.setVisible(true);
        } else {
            tajnyItem.setVisible(false);
        }

    }

    private void nastavPoziciHrace() {
        AnchorPane.setLeftAnchor(hracImageView, herniPlan.getAktualniProstor().getPosLeft());
        AnchorPane.setTopAnchor(hracImageView, herniPlan.getAktualniProstor().getPosTop());
    }
}

