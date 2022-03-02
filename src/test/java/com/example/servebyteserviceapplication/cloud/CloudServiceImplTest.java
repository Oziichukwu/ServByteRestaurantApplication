package com.example.servebyteserviceapplication.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.servebyteserviceapplication.service.cloud.CloudService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Slf4j
@SpringBootTest
class CloudServiceImplTest {

    @Autowired
    private CloudService cloudService;

    @Autowired
    private Cloudinary cloudinary;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("instantiate cloudinary object test")
    void cloudinaryInstanceTest(){
    assertThat(cloudinary).isNotNull();
    }

    @Test
    @DisplayName("file can be uploaded to cloudinary test")
    void uploadToCloudinaryTest() throws IOException {

        Path file = Paths.get("src/test/resources/ugbanija.jpg");
        assertThat(file.toFile().exists()).isTrue();
        Map<?,?> uploadResult = cloudService.upload(Files.readAllBytes(file), ObjectUtils.emptyMap());
        log.info("Upload result to cloud -> {}", uploadResult);
        assertThat(uploadResult.get("url")).isNotNull();
    }


}