package br.com.brn.feedrapi.application.services;

import br.com.brn.feedrapi.application.domain.models.GlobalFile;
import br.com.brn.feedrapi.application.ports.repositories.FileRepositoryPort;
import br.com.brn.feedrapi.application.ports.services.FileServicePort;
import br.com.brn.feedrapi.application.utils.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FileService implements FileServicePort {

    private Logger logger = LoggerFactory.getLogger(FileService.class);

    private final FileRepositoryPort fileRepository;

    public FileService(FileRepositoryPort fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public GlobalFile save(GlobalFile globalFile) {
        generateFile(globalFile);
        globalFile = fileRepository.save(globalFile);
        String url = "http://localhost:8080/feedrapi/".concat("files/").concat(globalFile.getId());
        globalFile.setUrl(url);
        return globalFile;
    }

    @Override
    public GlobalFile findById(String id) {
        GlobalFile globalFile = fileRepository.findById(id);
        if (Objects.isNull(globalFile)) {
            return null;
        }
        try {
            File file = new File(globalFile.getPath());
            if (file.exists()) {
                globalFile.setContent(FileCopyUtils.copyToByteArray(file));
            }
            return globalFile;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void generateFile(GlobalFile globalFile) {
        try {
            StringBuilder fileRootPath = new StringBuilder();
            fileRootPath.append(SystemConstants.DRIVE_DIR);
            fileRootPath.append(File.separator);
            fileRootPath.append(globalFile.getClient().toUpperCase());
            fileRootPath.append(File.separator);

            StringBuilder filePath = new StringBuilder();
            filePath.append(fileRootPath).append(globalFile.getId()).append(globalFile.getExtension());

            File file = new File(fileRootPath.toString());
            if (!file.exists()) {
                file.mkdirs();
            }

            file = new File(filePath.toString());

            file.createNewFile();
            FileOutputStream in = new FileOutputStream(file);
            in.write(globalFile.getContent());
            in.close();
            globalFile.setPath(filePath.toString());
            globalFile.setSize((int) file.length());
        } catch (IOException ex) {
            logger.warn("Impossible to create file ".concat(globalFile.getPath()), ex);
        }
    }

}
