package real.prop.vertical.Repository.Building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import real.prop.vertical.Tuples.Building.BuildingDocument;

@Repository
public interface BuildingDocumentRepository extends JpaRepository<BuildingDocument, Integer>
{
    @Query(value = "Select a from BuildingDocument a where a.buildingId = ?1 and a.documentType = ?2")
    BuildingDocument checkDocumentByBuildingId(String buildingId, String docType);

    @Query(value = "Select a.fileName from BuildingDocument a where a.buildingId= ?1 and a.documentType = ?2")
    String getUploadedDocument(String buildingId, String docType);

    @Query(value = "SELECT a.fileName from BuildingDocument  a WHERE a.documentId=?1")
    BuildingDocument getUploadDocumentById(int documentId);
}
