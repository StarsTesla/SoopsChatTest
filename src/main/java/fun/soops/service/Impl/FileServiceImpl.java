package fun.soops.service.Impl;

import fun.soops.dao.FileDAO;
import fun.soops.entity.File;
import fun.soops.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    @Qualifier("fileDAO")
    private FileDAO fileDAO;

    //TODO
    //实现添加avatar方法
    public File addAvatar(@Param("realName") String realName) {
        //生成uuid(唯一标识)
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //将realName以"\\\\"分切，放进temp[]
        String temp[] = realName.split("\\\\");
        //获取文件名称(前缀+后缀)
        String fileName = temp[temp.length - 1];
        //将文件前缀名称拼接uuid，生成唯一文件标识名称
        String fileId = uuid + fileName.replace(".", "");
        //获取文件类型（后缀）
        String type = FilenameUtils.getExtension(realName);
        //生成文件对象
        File file = new File(fileId, fileName, type);
        fileDAO.addAvatar(file);
        return file;
    }
    //TODO 文件的存删


}
