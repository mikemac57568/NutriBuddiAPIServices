package com.Temple.NutriBuddi.UserManagement.controller;

import com.Temple.NutriBuddi.UserManagement.UserManagementApplication;
import com.Temple.NutriBuddi.UserManagement.model.Food;
import com.Temple.NutriBuddi.UserManagement.model.User;
import com.Temple.NutriBuddi.UserManagement.repository.FoodRepository;
import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;
import com.sun.org.apache.bcel.internal.classfile.ExceptionTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = UserManagementApplication.class
)
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private UserRepository userRepository;

    private String authorization;
    private String testEmail1;
    private String testFood1;

    private Logger LOG = LoggerFactory.getLogger(ImageControllerTest.class);

    private File file;
    private String imageContent;
    private String filepath;

    @Before
    public void setUp() throws Exception {
        authorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        testEmail1 = "jUnitTester@tester.com";
        User user = new User(testEmail1, "qualitypasssword;","username1", "boo",
                "blah", 5, 123, 26, 1);
        userRepository.save(user);
        testFood1 = "quantumKumquat";
        Food quantumKumquat = new Food(testFood1, "mg", 150, 2, 3, 4,
                5, 6, 7, 21, 23, 99, 53, 15, 10, 10, 69, 12);
        foodRepository.save(quantumKumquat);



//        BufferedImage image = ImageIO.read(file);
//        WritableRaster raster = image.getRaster();
//        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
//        byte[] byteData = data.getData();




    }

    @Test
    public void addNewImage() throws Exception {
//        MockMultipartFile mockMultipartFile = new MockMultipartFile("ChihuahuaOrMuffin", "ChihuahuaOrMuffin.jpg", "image/jpg",
//                "resources\\pictures\\ChihuahuaOrMuffin.jpg".getBytes());
        FileInputStream readImage;
        byte imageData[];
        int num = -1;
        try{
            filepath = "src/test/resources/pictures/ChihuahuaOrMuffin.jpg";
            file = new File(filepath);
            readImage = new FileInputStream(file.getAbsolutePath());
            imageData = new byte[(int) file.length()];
            num = readImage.read(imageData);

            imageContent = Base64.getUrlEncoder().encodeToString(imageData);
            readImage.close();
            //imageContent = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
            //imageContent = new String(Base64.getEncoder().encode(Files.readAllBytes(file.toPath())), "UTF-8");
//        } catch (FileNotFoundException e){
//            LOG.debug(e.getMessage());
//            LOG.info(e.getMessage());
        } catch (Exception e){
            LOG.debug(e.getMessage());
            LOG.info(e.getMessage());
        }
        LOG.info("authorization: " + authorization);
        LOG.info("directory: " + file.getAbsolutePath());
        LOG.info("num" + num);
        LOG.info("imageContent: " + imageContent);
        mockMvc.perform(get("/imageClassifier/addNewImage")
                .header("Authorization", authorization)
                .param("email", testEmail1)
                .param("imageString", imageContent)
                .param("foodName", testFood1)
                .param("fileName", "ChihuahuaOrMuffin")
                .param("numServing", "100"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

}