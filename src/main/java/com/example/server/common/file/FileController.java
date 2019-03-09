package com.example.server.common.file;

import com.example.server.entity.Paper;
import com.example.server.entity.User;
import com.example.server.entity.repository.PaperRepository;
import com.example.server.service.ConferenceService;
import com.example.server.service.PaperService;
import com.example.server.service.UserService;
import org.apache.tomcat.jni.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private PaperService paperService;

    @Autowired
     private UserService userService;
//    String floder="D:\\20152100172\\demosecurity\\src\\main\\java\\com\\example\\demosecurity\\api\\controller\\";
    String floder="C:\\Users\\Administrator\\Desktop\\server\\";
//    String floder="/home/ubuntu/file/";

    @GetMapping("/download/{conferenceId}/{id}")
    public  void downLoad(@PathVariable String id, @PathVariable String conferenceId, HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException {
        Paper paper=paperService.findOneByPaperId(id);

       if( userService.createOrNot(authentication.getName(),conferenceId)) {
           try (InputStream inputStream = new FileInputStream(new File(floder, id + paper.getPaperFileName()));
                OutputStream outputStream = response.getOutputStream();
           ) {
               response.setContentType("application/x-download");
               System.out.println(paper.getPaperFileName());
               response.addHeader("Content-Disposition", "attach;filename=" + new String(paper.getPaperFileName().getBytes("gb2312"), "ISO8859-1"));
               IOUtils.copy(inputStream, outputStream);
               outputStream.flush();
           }
       }
    }
    //    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
//        File convertFile =new File(floder+file.getOriginalFilename());
//        convertFile.createNewFile();
//        FileOutputStream fileOutputStream=new FileOutputStream(convertFile);
//        fileOutputStream.write(file.getBytes());
//        fileOutputStream.close();
//        return new ResponseEntity<>("file is upload sucessfully",HttpStatus.OK);
//
//    }
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("paperTitle")String paperTitle,
                                             @RequestParam("conferenceId")String conferenceId, Authentication authentication) throws IOException {
//        Paper paper=new Paper(file.getOriginalFilename());
            Paper paper=new Paper(paperTitle,conferenceId,authentication.getName());
            paper.setPaperFileName(file.getOriginalFilename());
            if(paperService.create(paper)) {
                System.out.println(file.getOriginalFilename());
//            paper.setPaperFileName(file.getOriginalFilename());
                File convertFile = new File(floder +paper.getPaperId()+ file.getOriginalFilename());
                convertFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.close();
                return new ResponseEntity<>("file is upload sucessfully", HttpStatus.OK);
            }
        return new ResponseEntity<>("file is upload sucessfully", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}