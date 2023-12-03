package logika;

public class PrikazIdkfa implements IPrikaz {
    private static final String NAZEV = "idkfa";
    private final Hra hra;

    public PrikazIdkfa(Hra hra) {
        this.hra = hra;

    }
    @Override
    public String provedPrikaz(String... parametry) {
        for (Prostor prostor:hra.getHerniPlan().getVsechnyProstory()){
            for(Vec vec: prostor.getVsechnyVeci()){
                hra.getHerniPlan().getBatoh().vlozDoBatohu(vec);
            }
        }
        return "Byly sebrány všechny věci ve hře";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
