package sample.admin;

public class TreatmentView {
    private String typeOfTreatment;
    private int price;
    private boolean refundable;

    @Override
    public boolean equals(Object obj) {
        return typeOfTreatment.equals(((TreatmentView) obj).getTypeOfTreatment());
    }

    TreatmentView(TreatmentProperty priceListProperty){
        typeOfTreatment = priceListProperty.getTypeOfTreatment();
        price = Integer.parseInt(priceListProperty.getPrice());
        if(priceListProperty.getRefundable().equals("TAK"))
            refundable = true;
        else
            refundable = false;
    }

    public TreatmentView(String typeOfTreatment, int price, boolean refundable) {
        this.typeOfTreatment = typeOfTreatment;
        this.price = price;
        this.refundable = refundable;
    }

    public String getTypeOfTreatment() {
        return typeOfTreatment;
    }

    public int getPrice() {
        return price;
    }

    public boolean isRefundable() {
        return refundable;
    }
}
