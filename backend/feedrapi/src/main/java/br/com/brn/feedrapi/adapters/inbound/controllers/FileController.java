package br.com.brn.feedrapi.adapters.inbound.controllers;

import br.com.brn.feedrapi.application.domain.models.GlobalFile;
import br.com.brn.feedrapi.application.ports.services.FileServicePort;
import br.com.brn.feedrapi.application.utils.MultipartFileSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@RestController
public class FileController extends AccessController {

    private final FileServicePort fileService;

    public FileController(FileServicePort fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json")
    private ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("No file sent", HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] bytes = file.getBytes();
            GlobalFile globalFile = new GlobalFile(file.getOriginalFilename(), getClientSchema(), bytes);
            globalFile = fileService.save(globalFile);
            return new ResponseEntity<>(globalFile, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/files/{id}", method = RequestMethod.GET)
    private void downloadFile(@PathVariable("id") String fileId, HttpServletRequest httpRequest,
                              HttpServletResponse response) throws Exception {
        GlobalFile globalFile = fileService.findById(fileId);
        if (!Objects.isNull(globalFile)) {
            MultipartFileSender.fromGlobalFile(globalFile).with(httpRequest).with(response).serveResource();
        }
    }

}
