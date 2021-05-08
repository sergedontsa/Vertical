package real.prop.vertical.Controllers.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import real.prop.vertical.Resources.Constant;
import real.prop.vertical.Service.BuildingDocumentStorageService;
import real.prop.vertical.Tuples.Employee.DocumentResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = Constant.BUILDING_DOCUMENT_CONTROLLER)
public class BuildingDocumentController {

    @Autowired
    private BuildingDocumentStorageService buildingDocumentStorageService;

    @CrossOrigin
    @PostMapping(value = Constant.BUILDING_DOCUMENT_SAVE_WITH_BUILDING_ID)
    public DocumentResponse uploadFile(@RequestParam("file")MultipartFile file, @PathVariable String buildingId, @PathVariable String docType){
        String fileName = this.buildingDocumentStorageService.storeFile(file, buildingId, docType);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return new DocumentResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @GetMapping(value = Constant.BUILDING_DOCUMENT_GET_WITH_BUILDING_ID)
    public ResponseEntity<Resource> downloadFile(@PathVariable String buildingId, @PathVariable String docType, HttpServletRequest request){
        String fileName = this.buildingDocumentStorageService.getDocumentName(buildingId, docType);
        Resource resource = null;
        if (fileName != null && !fileName.isEmpty()){
            try {
                resource = this.buildingDocumentStorageService.loadFileResources(fileName, buildingId);
            }catch (Exception exception){
                exception.printStackTrace();
            }
            String contentType = null;
            try {
                assert resource != null;
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            }catch (IOException exception){
                exception.printStackTrace();
            }
            if (contentType == null){
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename()+"\"").body(resource);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
