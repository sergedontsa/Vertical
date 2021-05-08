package real.prop.vertical.Tuples.Apartment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import real.prop.vertical.AuditModel.AuditModel;
import real.prop.vertical.Tuples.Building.Building;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "apartment")
public class Apartment extends AuditModel implements Serializable, Comparable<Apartment> {

    @Id
    @Column(name = "apartmentid", nullable = false, length = 50)
    @Basic
    private String apartmentID;
    @Basic
    @Column(name = "buildingid", insertable = false, updatable = false)
    private String buildingid;
    @Basic
    @Column(name = "dimensionid", insertable = false, updatable = false)
    private String dimensionid;
    @Basic
    @Column(name = "feeid", insertable = false, updatable = false)
    private String feeid;
    @Basic
    @Column(name = "apartment_number", length = 50, nullable = true)
    private String apartment_number;
    @Basic
    @Column(name = "numbedroom")
    private int numBedRoom;
    @Basic
    @Column(name = "numlivingroom")
    private int numLivingRoom;
    @Basic
    @Column(name = "numbathroom")
    private int numBathRoom;
    @Basic
    @Column(name = "numkitchen")
    private int numKitchen;
    @Basic
    @Column(name = "numcloset")
    private int numCloset;
    @Basic
    @Column(name = "numwindows")
    private int numWindows;
    @Basic
    @Column(name = "iswithbath", nullable = false)
    private boolean isWithBath;
    @Basic
    @Column(name = "iswithwaterboiler", nullable = false)
    private boolean isWithWaterBoiler;
    @Basic
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildingid", nullable = false)
    @JsonBackReference("apartment")
    private Building building;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dimensionid")
    private ApartmentDimension apartmentDimension;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "feeid")
    private ApartmentFee apartmentFee;

    @JsonManagedReference("apartment_expense")
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(nullable = true)
    private List<ApartmentExpense> apartmentExpenses;

    @JsonManagedReference("apartment_document")
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ApartmentDocument> apartmentDocuments;

    public List<ApartmentDocument> getApartmentDocuments() {
        return apartmentDocuments;
    }

    public void setApartmentDocuments(List<ApartmentDocument> apartmentDocuments) {
        this.apartmentDocuments = apartmentDocuments;
    }

    //-----------------------------------------------------------------------------------------

    public String getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(String apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
    }

    public String getDimensionid() {
        return dimensionid;
    }

    public void setDimensionid(String dimensionid) {
        this.dimensionid = dimensionid;
    }

    public String getFeeid() {
        return feeid;
    }

    public void setFeeid(String feeid) {
        this.feeid = feeid;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        this.apartment_number = apartment_number;
    }

    public int getNumBedRoom() {
        return numBedRoom;
    }

    public void setNumBedRoom(int numBedRoom) {
        this.numBedRoom = numBedRoom;
    }

    public int getNumLivingRoom() {
        return numLivingRoom;
    }

    public void setNumLivingRoom(int numLivingRoom) {
        this.numLivingRoom = numLivingRoom;
    }

    public int getNumBathRoom() {
        return numBathRoom;
    }

    public void setNumBathRoom(int numBathRoom) {
        this.numBathRoom = numBathRoom;
    }

    public int getNumKitchen() {
        return numKitchen;
    }

    public void setNumKitchen(int numKitchen) {
        this.numKitchen = numKitchen;
    }

    public int getNumCloset() {
        return numCloset;
    }

    public void setNumCloset(int numCloset) {
        this.numCloset = numCloset;
    }

    public int getNumWindows() {
        return numWindows;
    }

    public void setNumWindows(int numWindows) {
        this.numWindows = numWindows;
    }

    public boolean isWithBath() {
        return isWithBath;
    }

    public void setWithBath(boolean withBath) {
        isWithBath = withBath;
    }

    public boolean isWithWaterBoiler() {
        return isWithWaterBoiler;
    }

    public void setWithWaterBoiler(boolean withWaterBoiler) {
        isWithWaterBoiler = withWaterBoiler;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ApartmentDimension getApartmentDimension() {
        return apartmentDimension;
    }

    public void setApartmentDimension(ApartmentDimension apartmentDimension) {
        this.apartmentDimension = apartmentDimension;
    }

    public ApartmentFee getApartmentFee() {
        return apartmentFee;
    }

    public void setApartmentFee(ApartmentFee apartmentFee) {
        this.apartmentFee = apartmentFee;
    }

    public List<ApartmentExpense> getApartmentExpenses() {
        return apartmentExpenses;
    }

    public void setApartmentExpenses(List<ApartmentExpense> apartmentExpenses) {
        this.apartmentExpenses = apartmentExpenses;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "apartmentID='" + apartmentID + '\'' +
                ", buildingid='" + buildingid + '\'' +
                ", dimensionid='" + dimensionid + '\'' +
                ", feeid='" + feeid + '\'' +
                ", apartment_number='" + apartment_number + '\'' +
                ", numBedRoom=" + numBedRoom +
                ", numLivingRoom=" + numLivingRoom +
                ", numBathRoom=" + numBathRoom +
                ", numKitchen=" + numKitchen +
                ", numCloset=" + numCloset +
                ", numWindows=" + numWindows +
                ", isWithBath=" + isWithBath +
                ", isWithWaterBoiler=" + isWithWaterBoiler +
                ", status='" + status + '\'' +
                ", building=" + building +
                ", apartmentDimension=" + apartmentDimension +
                ", apartmentFee=" + apartmentFee +
                ", apartmentExpenses=" + apartmentExpenses +
                ", apartmentDocuments=" + apartmentDocuments +
                '}';
    }



    @Override
    public int compareTo(@NotNull Apartment o) {

        return 0;
    }
}
