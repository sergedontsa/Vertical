package real.prop.vertical.AuditModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"registerdate", "lastupdate"}, allowGetters = true)
@Component
public abstract class AuditModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registerdate", nullable = false, updatable = false)
    @CreatedDate
    private Date registerdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupdate", nullable = false)
    @LastModifiedDate
    private Date lastupdate;



    public Date getRegisterdate() {
        return registerdate;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Override
    public String toString() {
        return "AuditModel{" +
                "registerdate=" + registerdate +
                ", lastupdate=" + lastupdate +
                '}';
    }
}
