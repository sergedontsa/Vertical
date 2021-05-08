package real.prop.vertical.Repository.Tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import real.prop.vertical.Tuples.Tenant.TenantDocument;

public interface TenantDocumentRepository extends JpaRepository<TenantDocument, Integer>
{
    @Query("Select a from TenantDocument a where a.tenantId = ?1 and a.documentType = ?2")
    TenantDocument checkDocumentByTenantId(String tenantId, String docType);

    @Query("Select a.fileName from TenantDocument a where a.tenantId = ?1 and a.documentType = ?2")
    String getUploadDocumentPath(String tenantId, String docType);
}
