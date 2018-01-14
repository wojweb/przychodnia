package sample.admin;

import javafx.beans.property.SimpleStringProperty;

public class TreatmentProperty {
    private SimpleStringProperty typeOfTreatment;
    private SimpleStringProperty price;
    private SimpleStringProperty refundable;

    TreatmentProperty(TreatmentView view){
        this.typeOfTreatment = new SimpleStringProperty(view.getTypeOfTreatment());
        this.price = new SimpleStringProperty(Integer.toString(view.getPrice()));
        if(view.isRefundable())
            refundable = new SimpleStringProperty("TAK");
        else
            refundable = new SimpleStringProperty("NIE");
    }

    TreatmentProperty(String typeOfTest, int price, boolean refundable){
        this.typeOfTreatment = new SimpleStringProperty(typeOfTest);
        this.price = new SimpleStringProperty(Integer.toString(price));
        if(refundable)
            this.refundable = new SimpleStringProperty("TAK");
        else
            this.refundable = new SimpleStringProperty("NIE");

    }

    public String getTypeOfTreatment() {
        return typeOfTreatment.get();
    }

    public SimpleStringProperty getTypeOfTreatmentProperty() {
        return typeOfTreatment;
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public String getRefundable() {
        return refundable.get();
    }

    public SimpleStringProperty refundableProperty() {
        return refundable;
    }
}
