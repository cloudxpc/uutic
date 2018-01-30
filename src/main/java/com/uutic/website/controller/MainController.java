package com.uutic.website.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.uutic.website.model.User;
import com.uutic.website.util.CaptchaCodeUtil;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

@RestController
public class MainController {
    @RequestMapping("/captchacode")
    public void verificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaCodeUtil.CaptchaCodeModel captchaCodeModel = CaptchaCodeUtil.getCode();
        request.getSession().setAttribute("captcha_code", captchaCodeModel.getCaptchaCode());
        // Disable caching
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ServletOutputStream servletOutputStream=response.getOutputStream();
        ImageIO.write(captchaCodeModel.getCaptchaImage(), "jpeg", servletOutputStream);
        servletOutputStream.close();
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public String captchaCode(HttpServletRequest request) throws IOException {
        CaptchaCodeUtil.CaptchaCodeModel captchaCodeModel = CaptchaCodeUtil.getCode();
        request.getSession().setAttribute("captcha_code", captchaCodeModel.getCaptchaCode());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(captchaCodeModel.getCaptchaImage(), "gif", os);
        String base64 = Base64.getEncoder().encodeToString(os.toByteArray());
        return "data:image/gif;base64," + base64;
    }

    @RequestMapping(value = "/login")
    public String login() {
        String secret = "secret";
        Calendar issueAt = Calendar.getInstance();
        Calendar expireAt = Calendar.getInstance();
        expireAt.add(Calendar.MINUTE, 1);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("uutic")
                    .withIssuedAt(issueAt.getTime())
//                    .withExpiresAt(expireAt.getTime())
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException | JWTCreationException e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/api/hello")
    public List<User> hello() throws InterruptedException, IOException {
        LoggerFactory.getLogger(MainController.class).info("hello called");
        Thread.sleep(5000);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        TypeFactory typeFactory = mapper.getTypeFactory();
        return mapper.readValue("[\n" +
                "          {\n" +
                "            id: 1,\n" +
                "            name: 'Shawna Dubbin',\n" +
                "            email: 'sdubbin0@geocities.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Assistant Media Planner'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 2,\n" +
                "            name: 'Odette Demageard',\n" +
                "            email: 'odemageard1@spotify.com',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Account Coordinator'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 3,\n" +
                "            name: 'Vera Taleworth',\n" +
                "            email: 'vtaleworth2@google.ca',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Community Outreach Specialist'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 4,\n" +
                "            name: 'Lonnie Izkovitz',\n" +
                "            email: 'lizkovitz3@youtu.be',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Operator'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 5,\n" +
                "            name: 'Thatcher Stave',\n" +
                "            email: 'tstave4@reference.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Software Test Engineer III'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 6,\n" +
                "            name: 'Karim Chipping',\n" +
                "            email: 'kchipping5@scribd.com',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Safety Technician II'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 7,\n" +
                "            name: 'Helge Holyard',\n" +
                "            email: 'hholyard6@howstuffworks.com',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Internal Auditor'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 8,\n" +
                "            name: 'Rod Titterton',\n" +
                "            email: 'rtitterton7@nydailynews.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Technical Writer'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 9,\n" +
                "            name: 'Gawen Applewhite',\n" +
                "            email: 'gapplewhite8@reverbnation.com',\n" +
                "            gender: 'Female',\n" +
                "            title: 'GIS Technical Architect'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 10,\n" +
                "            name: 'Nero Mulgrew',\n" +
                "            email: 'nmulgrew9@plala.or.jp',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Staff Scientist'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 11,\n" +
                "            name: 'Cybill Rimington',\n" +
                "            email: 'crimingtona@usnews.com',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Assistant Professor'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 12,\n" +
                "            name: 'Maureene Eggleson',\n" +
                "            email: 'megglesonb@elpais.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Recruiting Manager'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 13,\n" +
                "            name: 'Cortney Caulket',\n" +
                "            email: 'ccaulketc@cbsnews.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Safety Technician IV'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 14,\n" +
                "            name: 'Selig Swynfen',\n" +
                "            email: 'sswynfend@cpanel.net',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Environmental Specialist'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 15,\n" +
                "            name: 'Ingar Raggles',\n" +
                "            email: 'iragglese@cbc.ca',\n" +
                "            gender: 'Female',\n" +
                "            title: 'VP Sales'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 16,\n" +
                "            name: 'Karmen Mines',\n" +
                "            email: 'kminesf@topsy.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Administrative Officer'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 17,\n" +
                "            name: 'Salome Judron',\n" +
                "            email: 'sjudrong@jigsy.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Staff Scientist'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 18,\n" +
                "            name: 'Clarinda Marieton',\n" +
                "            email: 'cmarietonh@theatlantic.com',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Paralegal'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 19,\n" +
                "            name: 'Paxon Lotterington',\n" +
                "            email: 'plotteringtoni@netvibes.com',\n" +
                "            gender: 'Female',\n" +
                "            title: 'Marketing Assistant'\n" +
                "          },\n" +
                "          {\n" +
                "            id: 20,\n" +
                "            name: 'Maura Thoms',\n" +
                "            email: 'mthomsj@webeden.co.uk',\n" +
                "            gender: 'Male',\n" +
                "            title: 'Actuary'\n" +
                "          }\n" +
                "        ]", typeFactory.constructCollectionType(List.class, User.class));
    }
}
