package ncu.im3069.demo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/api/upload.do")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2 MB
	    maxFileSize = 1024 * 1024 * 10,         // 10 MB
	    maxRequestSize = 1024 * 1024 * 50       // 50 MB
	)

public class UploadController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multifiles = sf.parseRequest(request);
            System.out.println(request);

            //String cla = this.getClass().getClassLoader().getResource(".").getPath();
            //String img_path = cla.replace("/WEB-INF/classes/", "/img/");
            //System.out.println(cla);

            for(FileItem item:multifiles) {

                item.write(new File("112_SA_project_G2/img/"+item.getName()));
                System.out.println(item.getName());
            }
            System.out.println("Upload Success!");
            }catch(Exception ex){
                System.out.println(ex);
                System.out.println("Upload fail!");
            }

        }

    
}
