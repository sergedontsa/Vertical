package real.prop.vertical.Tuples.Apartment;

import real.prop.vertical.AuditModel.AuditModel;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "apartment_fee", schema = "housingauthority", catalog = "")
public class ApartmentFee extends AuditModel implements Serializable {

    @Id
    @Basic
    @Column(name = "apartmentid", nullable = false)
    private String apartmentId;
    @Basic
    @Column(name = "fee", nullable = false, length = 50)
    private String fee;
    @Basic
    @Column(name = "currency", nullable = false, length = 50)
    private String currency;

    //-----------------


    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
