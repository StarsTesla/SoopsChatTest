package fun.soops.service;

import fun.soops.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    //TODO
    File addAvatar(String realName);


    String saveFile(String base64) throws IOException;

}
