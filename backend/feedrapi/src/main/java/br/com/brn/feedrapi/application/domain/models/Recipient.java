package br.com.brn.feedrapi.application.domain.models;

public class Recipient {

    private Long value;

    private String label;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
