package ma.moteur.oumast.oumast;

import java.io.Serializable;

/**
 * Created by poste05 on 02/03/2018.
 */

public class SmsObject implements Serializable{
    private String id;
    private String number;
    private String message;
    private int etat;

    public SmsObject() {
        this.etat=0;
    }

    public SmsObject(String id, String number, String message) {
        this.id = id;
        this.number = number;
        this.message = message;
        this.etat=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "SmsObject{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
