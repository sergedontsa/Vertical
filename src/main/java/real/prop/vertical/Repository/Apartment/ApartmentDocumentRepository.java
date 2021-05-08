package real.prop.vertical.Repository.Apartment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Apartment.ApartmentDocument;

import java.util.List;

@Repository

public interface ApartmentDocumentRepository extends JpaRepository<ApartmentDocument, Integer> {
    @Query(value= "SELECT a FROM ApartmentDocument a WHERE a.apartmentId = ?1")
    List<ApartmentDocument> getAllByApartmentId(String employeeId);

    @Query("Select a from ApartmentDocument a where a.apartmentId = ?1 and a.documentType = ?2")
    ApartmentDocument checkDocumentByApartmentId(String apartmentId, String docType);

    @Query("Select a.fileName from ApartmentDocument a where a.apartmentId= ?1 and a.documentType = ?2")
    String getUploadedDocument(String apartmentId, String docType);
}
