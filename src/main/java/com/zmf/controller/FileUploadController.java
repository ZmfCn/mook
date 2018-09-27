package com.zmf.controller;

import com.zmf.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileUploadController {
    private Logger logger = Logger.getLogger(UserController.class);


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String editItemsSubmit(Model model) throws Exception {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "testImage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(User user, @RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request) throws Exception {
        //基本表单
        logger.info(user.toString());
        logger.info(file.isEmpty());

        //获得物理路径webapp所在路径
//        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String pathRoot = "/home/zmf/Downloads/";
        String path = "";
        if (!file.isEmpty()) {
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            logger.info(contentType);
            //获得文件后缀名称
            String imageName = contentType.substring(contentType.indexOf("/") + 1);
            path = uuid + "." + imageName;
            file.transferTo(new File(pathRoot + path));
        }
        return "show";
    }


//    public void sql(){
//        String picPath = "";
//        final Map map = new HashMap<String, Integer>();
//        if(uploadRequest.getSize("file") > 0 ){
//            String name = uploadRequest.getFileName("file");
//            final String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+name;
//            InputStream is = uploadRequest.getFileAsStream("file");
//            final  byte[] img = FileCopyUtils.copyToByteArray(is);
//            String sqlforInsert = "insert into TOOL_IM_FILE values (?,?,?)";
//            final LobHandler lobHandler = new DefaultLobHandler();
//            jdbcTemplate.execute(sqlforInsert, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
//                @Override
//                protected void setValues(PreparedStatement ps, LobCreator lobCreator)
//                        throws SQLException, DataAccessException {
//
//                    String sqlforMaxId = "select max(id) id from TOOL_IM_FILE";
//                    int maxid  = 0;
//                    try {
//                        maxid   = jdbcTemplate.queryForObject(sqlforMaxId, Integer.class);
//                        maxid+=1;
//                    } catch (Exception e) {
//                        maxid = 1;
//                    }
//                    map.put("maxid", maxid);
//                    ps.setInt(1,maxid);
//                    ps.setString(2, fileName);
//                    lobCreator.setBlobAsBytes(ps, 3, img);
//                }
//            });
//            //2.读出来返回存到服务器,直接用is流，获取地址
//            String path = resourceRequest.getContextPath()+"/data/layimtemp/";
//            File pathFile = new File(path);
//            if(!pathFile.exists()){//判断路径是否存在
//                pathFile.mkdirs();
//            }
//            File file = new File(path+fileName);
//            if (!file.exists())
//            {
//                file.createNewFile(); // 如果文件不存在，则创建
//            }
//
//            //从数据库读取流刷到文件中
//            String sqlforimage = "select  FILES from TOOL_IM_FILE where  id =" + map.get("maxid");
//            final OutputStream os=new FileOutputStream(file);
//            jdbcTemplate.query(sqlforimage,  new AbstractLobStreamingResultSetExtractor() {
//
//                @Override
//                protected void handleNoRowFound() throws DataAccessException {
//                }
//
//                @Override
//                protected void streamData(ResultSet rs) throws SQLException,
//                        IOException, DataAccessException {
//                    InputStream is = lobHandler.getBlobAsBinaryStream(rs, 1);
//                    if (is != null) {
//                        FileCopyUtils.copy(is, os);
//                    }
//                }
//            } );
//
//        }
}
