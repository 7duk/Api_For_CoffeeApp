import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UploadingImage {
    public static void main(String[] args) throws IOException {
//        Dotenv dotenv = Dotenv.load();
//        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
//        cloudinary.config.secure = true;
//        System.out.println(cloudinary.config.cloudName);/
//        String[] arrayPath= {
//                "F:\\ServerForAndroid\\image-for-coffee_app\\nuoc-chanh.jpg",
//                "F:\\ServerForAndroid\\image-for-coffee_app\\nuocscam-620x620.jpg",
//                "F:\\ServerForAndroid\\image-for-coffee_app\\trabuoi.jpg"
//        };
        Map config = new HashMap();
        config.put("cloud_name", "dte2ps5qs");
        config.put("api_key", "791388445627371");
        config.put("api_secret", "rNQYUi2PS8ZdL8wKYExiUyZN9_4");
        Cloudinary cloudinary = new Cloudinary(config);
//*************************************************
//        Part filePart = request.getPart("file"); // get the uploaded file
//        String publicId = "my_image_public_id"; // the public ID of the image in Cloudinary
//        Map<String, Object> params = ObjectUtils.asMap("public_id", publicId);
//        cloudinary.uploader().upload(filePart.getInputStream(), params);
//**************************************************
//        for(int i =0;i<arrayPath.length;i++){
//            File file = new File(arrayPath[i]);
//            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//        }
//         String url = cloudinary.downloadBackedupAsset(String asset_id, String version_id,ObjectUtils.emptyMap());

    }
}
