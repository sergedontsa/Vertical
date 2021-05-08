package real.prop.vertical.Tuples.Billing;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import real.prop.vertical.AuditModel.AuditModel;
import real.prop.vertical.Tuples.Building.Building;
import real.prop.vertical.Tuples.Tenant.Tenant;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "billing")
public class Billing extends AuditModel implements Serializable {


    @Id
    @Column(name = "billingid", nullable = false, length = 50)
    private String billingid;

    @Basic
    @Column(name = "tenantid", insertable = false, updatable = false)
    private String tenantid;
    @Basic
    @Column(name = "buildingid", insertable = false, updatable = false)
    private String buildingid;
    @Basic
    @Column(name = "apartmentid", nullable = false, length = 50)
    private String apartmentid;

    @Basic
    @Column(name = "start", nullable = false, length = 50)
    private String start;

    @Basic
    @Column(name = "end", nullable = false, length = 50)
    private String end;
    @Basic
    @Column(name = "period", nullable = false, length = 50)
    private String period;
    @Basic
    @Column(name = "duedate", nullable = false, length = 50)
    private String duedate;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tenantid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityReference(alwaysAsId = true)
    private Tenant tenant;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "buildingid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityReference(alwaysAsId = true)
    private Building building;

    public String getBuildingId() {
        return buildingid;
    }
    //----------------------------------------------------

    public String getBillingid() {
        return billingid;
    }

    public void setBillingid(String billingid) {
        this.billingid = billingid;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {



        this.buildingid = buildingid;
    }

    public String getApartmentid() {
        return apartmentid;
    }

    public void setApartmentid(String apartmentid) {
        this.apartmentid = apartmentid;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
