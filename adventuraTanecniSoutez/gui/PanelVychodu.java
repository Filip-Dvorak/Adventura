package gui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import logika.HerniPlan;
import logika.Hra;
import logika.IHra;
import logika.Prostor;
import util.Observer;


public class PanelVychodu implements Observer {

    final HerniPlan herniPlan;
    private IHra hra;
    ListView<String> listView = new ListView<>();
    ObservableList<String> vychody = FXCollections.observableArrayList();

    public PanelVychodu(IHra hra, TextArea konzole) {
        this.herniPlan = hra.getHerniPlan();
        this.hra = hra;
        herniPlan.registerObserver(this);
        nactiVychodyAktualnihoProstoru();

        listView.setItems(vychody);
        listView.setPrefWidth(100);
        listView.setOnMouseClicked(event -> {
            String nazev = listView.getSelectionModel().getSelectedItem();
            String odpoved=hra.zpracujPrikaz("jdi " + nazev);
            konzole.appendText("\n" + odpoved + "\n");
        });

        konzole.setEditable(false);
    }

    public ListView<String> getListView() {
        return listView;
    }

    @Override
    public void update() {
        nactiVychodyAktualnihoProstoru();
    }

    private void nactiVychodyAktualnihoProstoru() {
        vychody.clear();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        for (Prostor prostor : aktualniProstor.getVychody()) {
            vychody.add(prostor.getNazev());
        }
    }
}
