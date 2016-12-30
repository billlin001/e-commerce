package tw.bill.conf;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import java.io.IOException;

/**
 * Created by bill33 on 2016/9/11.
 */
@Configuration
public class CommonConfig {
    @Bean
    public MultipartResolver multipartResover() throws IOException{
        return new StandardServletMultipartResolver();
    }

    @Bean
    public StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
