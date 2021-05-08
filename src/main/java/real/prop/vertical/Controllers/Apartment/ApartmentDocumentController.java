package real.prop.vertical.Controllers.Apartment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Service.ApartmentDocumentStorageService;
import real.prop.vertical.Tuples.Employee.DocumentResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * This Controller handle the file for an apartment
 * such as picture, document, inspection...
 */

@RestController
@RequestMapping(value = Constant.APARTMENT_DOCUMENT_CONTROLLER)
public class ApartmentDocumentController {
    @Autowired
    private ApartmentDocumentStorageService apartmentDocumentStorageService;

    /**
     * Upload file
     * @param file file
     * @param apartmentId apartment id
     * @param docType doc type
     * @return document response
     */
    @CrossOrigin
    @PostMapping(value = Constant.APARTMENT_DOCUMENT_SAVE_WITH_APARTMENT_ID)
    public DocumentResponse uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String apartmentId, @PathVariable String docType){
        String fileName = this.apartmentDocumentStorageService.storeFile(file, apartmentId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new DocumentResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    /**
     * Download file
     * @param apartmentId apartment id
     * @param docType doc type
     * @param request request (HttpServlet)
     * @return return response entity
     */

    @GetMapping(value = Constant.APARTMENT_DOCUMENT_GET_WITH_APARTMENT_ID)
    public ResponseEntity<Resource> downloadFile(@PathVariable String apartmentId, @PathVariable String docType, HttpServletRequest request){
        String fileName = this.apartmentDocumentStorageService.getDocumentName(apartmentId, docType);
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()){
            try {
                resource = this.apartmentDocumentStorageService.loadFileAsResources(fileName, apartmentId);
            }catch (Exception exception){
                exception.printStackTrace();
            }
            String contentType  = null;
            try {
                assert resource != null;
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }catch (IOException ex){
                ex.printStackTrace();
            }
            if (contentType == null){
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,  "attachment; filename=\""+ resource.getFilename()+"\"").body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
