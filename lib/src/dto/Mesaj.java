package dto;

import java.io.Serializable;
import java.time.Instant;

public class Mesaj implements Serializable {

    private static final long serialVersionUID = 1L;

    private String continut;

    private String expeditor;

    private Instant timestamp;

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public String getExpeditor() {
        return expeditor;
    }

    public void setExpeditor(String expeditor) {
        this.expeditor = expeditor;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return getExpeditor()+": "+getContinut()+" ("+getTimestamp()+")";
    }

    public static Mesaj of(String expeditor, String continut){
        Mesaj mesaj = new Mesaj();
        mesaj.setContinut(continut);
        mesaj.setExpeditor(expeditor);
        mesaj.setTimestamp(Instant.now());
        return mesaj;
    }
}
