package com.example.server.common.file;

import com.example.server.common.entity.ResultInfo;
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
import java.net.URLEncoder;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private PaperService paperService;

    @Autowired
     private UserService userService;
//    String floder="D:\\20152100172\\demosecurity\\src\\main\\java\\com\\example\\demosecurity\\api\\controller\\";
//    String floder="C:\\Users\\Administrator\\Desktop\\server\\";
//    String floder="/home/ubuntu/file/";
    String floder="D:\\test\\";

    @GetMapping("/download/{id}")
    public  void downLoad(@PathVariable String id, @RequestParam(name = "conferenceId",required = false) String conferenceId,Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Paper paper=paperService.findOneByPaperId(id);
       response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
//       response.addHeader("Content-Disposition",new String(paper.getPaperFileName().getBytes("GBK"),"ISO-8859-1"));

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(paper.getPaperFileName(),"UTF-8")+";filename*=UTF-8;");
//        response.setContentType("application/octet-stream");
//        response.setCharacterEncoding("UTF-8");

//
        if(conferenceId==null&&paper.getUserName().equals(authentication.getName())){
            try (InputStream inputStream = new FileInputStream(new File(floder, id + paper.getPaperFileName()));
                 OutputStream outputStream = response.getOutputStream();
            ) {
                response.setContentType("application/x-download");
                System.out.println(paper.getPaperFileName());
//                response.addHeader("Content-Disposition", "attach;filename=" + new String(paper.getPaperFileName().getBytes("gb2312"), "ISO8859-1"));
                IOUtils.copy(inputStream, outputStream);
                outputStream.flush();
            }

        }
        else
       if( userService.createOrNot(authentication.getName(),conferenceId)&&paper.getConferenceId().equals(conferenceId)) {
           try (InputStream inputStream = new FileInputStream(new File(floder, id + paper.getPaperFileName()));
                OutputStream outputStream = response.getOutputStream();
           ) {
               response.setContentType("application/x-download");
               System.out.println(paper.getPaperFileName());
//               response.addHeader("Content-Disposition", "attach;filename=" + new String(paper.getPaperFileName().getBytes("gb2312"), "ISO8859-1"));
               IOUtils.copy(inputStream, outputStream);
               outputStream.flush();
           }
       }

//        return new ResultInfo(HttpStatus.OK,"download success",paper.getPaperFileName());
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
    public ResultInfo uploadFile(@RequestParam("file")MultipartFile file, @RequestParam("paperTitle")String paperTitle,
                                 @RequestParam("conferenceId")String conferenceId,@RequestParam("conferenceName")String conferenceName, Authentication authentication) throws IOException {
//        Paper paper=new Paper(file.getOriginalFilename());
            if(userService.attendOrNot(authentication.getName(),conferenceId)) {
                if (paperService.exitByConferenceAndUserName(conferenceId,authentication.getName())){
                    Paper paper=paperService.findOneByConferenceIdAndUserName(conferenceId,authentication.getName());
                    paper.setPaperTilte(paperTitle);
                    paper.setConferenceName(conferenceName);
                    File oldFile = new File(floder+paper.getPaperId()+paper.getPaperFileName());
                    File convertFile = new File(floder +paper.getPaperId()+ file.getOriginalFilename());

                    if(oldFile.exists()) {
                        oldFile.delete();
                    }

                    convertFile.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();
                    paper.setPaperFileName(file.getOriginalFilename());
                    paperService.save(paper);
                }
                else{
                    Paper paper=new Paper(paperTitle,conferenceId,authentication.getName(),conferenceName);
                    paperService.create(paper);
//                    System.out.println(paper.getPaperId());
                    paper.setPaperFileName(file.getOriginalFilename());
                    File convertFile = new File(floder +paper.getPaperId()+ file.getOriginalFilename());
//                    System.out.println(paper.getPaperId());
                    convertFile.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();

//                    System.out.println(paper.getPaperId());
                    paperService.save(paper);
//                    System.out.println(paper.getPaperId());
                }
                return new ResultInfo(HttpStatus.OK,"file is upload sucessfully", true);

            }

        return new ResultInfo(HttpStatus.INTERNAL_SERVER_ERROR,"file is upload failure", false);
    }


}