package real.prop.vertical.Tuples.Tenant;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.boot.context.properties.ConfigurationProperties;
import real.prop.vertical.AuditModel.AuditModel;

import javax.persistence.*;


@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "tenant_document", schema = "vertical", catalog = "")
public class TenantDocument extends AuditModel {
    @Id
    @Column(name = "documentid", nullable = false)
    private int documentId;

    @Basic
    @Column(name = "tenantid", insertable = false, updatable = false)
    private String tenantId;

    @Basic
    @Column(name = "filename", nullable = false, length = 100)
    private String fileName;
    @Basic
    @Column(name = "documenttype", nullable = false, length = 500)
    private String documentType;
    @Basic
    @Column(name = "documentformat", nullable = false, length = 500)
    private String documentFormat;
    @Basic
    @Column(name = "uploaddir", nullable = true, length = 500)
    private String uploadDir;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenantid")
    @JsonBackReference("tenant_documnet")
    private Tenant tenant;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
