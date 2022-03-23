import io.minio.*;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件上传
 *
 * @author xc.chen
 * @date 2021/10/21 10:31
 */
public class FileUploader {

    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://10.0.2.191:9000")
                            .credentials("minio", "minio123456")
                            .build();

            // Make 'test' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("test").build());
            if (!found) {
                // Make a new bucket called 'test'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("test").build());
            } else {
                System.out.println("Bucket 'test' already exists.");
            }

            // Upload 'E:/minio.exe' as object name 'test.txt' to bucket
            // 'test'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("test")
                            .object("test.txt")
                            .filename("E:/test.txt")
                            .build());
            System.out.println(
                    "'E:/test.txt' is successfully uploaded as "
                            + "object 'test.txt' to bucket 'test'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }

    }
}
