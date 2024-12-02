package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.controller.payload.LoginReq;
import com.untucapital.usuite.utg.controller.payload.LoginResp;
import com.untucapital.usuite.utg.controller.payload.SignUpRequest;
import com.untucapital.usuite.utg.controller.payload.UsuiteApiResp;
import com.untucapital.usuite.utg.exception.ResourceNotFoundException;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.UserRepository;
import com.untucapital.usuite.utg.service.SmsService;
import com.untucapital.usuite.utg.service.UserService;
import com.untucapital.usuite.utg.utils.RandomNumUtils;
import net.bytebuddy.utility.RandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Optional;

/**
 * @author Chirinda Nyasha Dell 22/11/2021
 */

@RestController
@RequestMapping("auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    private final SmsService smsService;
    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${untu.reset-token.link}")
    private String resetPassUrl;

    @Value("${untu.reset-email.link}")
    private String resetEmailUrl;

    @Autowired
    public AuthController(UserService userService, SmsService smsService, UserRepository userRepository) {
        this.userService = userService;
        this.smsService = smsService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq) {

        Optional<LoginResp> loginRespOptional = userService.authenticateUser(loginReq);

        if (loginRespOptional.isPresent()) {
            return ResponseEntity.ok(loginRespOptional.get());
        } else return ResponseEntity.badRequest().body(new UsuiteApiResp("Invalid Credentials"));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsuiteApiResp> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        Optional<String> createdUserIdOptional = userService.registerUser(signUpRequest);

        if (createdUserIdOptional.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/users/getUser/{id}")
                    .buildAndExpand(createdUserIdOptional.get()).toUri();

            return ResponseEntity.created(location).body(new UsuiteApiResp("User registered successfully"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Failed to register user"));
    }

    @PostMapping("/confirm_account")
    public ResponseEntity<UsuiteApiResp> confirm(@RequestParam("username") String username,
                                                 @RequestParam("code") String code) {

        if (userService.confirmUserAccount(username, code)) {
            return ResponseEntity.ok(new UsuiteApiResp("User confirmed successfully"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Failed to confirm user account"));
    }

    @GetMapping("/check_email")
    public ResponseEntity<UsuiteApiResp> checkEmail(@RequestParam("email") String email) {

        if (userService.checkUserEmail(email)) {
            return ResponseEntity.ok(new UsuiteApiResp("User email exists"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Email provided does not exist"));
    }

    @GetMapping("/check_mobile")
    public ResponseEntity<UsuiteApiResp> checkMobile(@RequestParam("mobile") long mobile) {

        if (userService.checkUserMobile(mobile)) {
            return ResponseEntity.ok(new UsuiteApiResp("Mobile number exists"));
        } else
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Mobile number provided does not exist"));
    }


    @PostMapping("/forgot_password")
    public ResponseEntity<UsuiteApiResp> processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            userService.updateResetPasswordToken(token, email);
//            String resetPasswordLink = EmailSender.getSiteURL(request) + "/auth/reset_password?token=" + token;
            String resetPasswordLink = resetPassUrl + "?token=" + token;
            sendEmail(email, resetPasswordLink);
//            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
            return ResponseEntity.ok(new UsuiteApiResp("We have sent a reset password link to your email. Please check."));

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Failed to sent a reset password link"));
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Error while sending email"));
        }

//        return "We have sent a reset password link to your email. Please check.";

    }

    @PostMapping("/forgot_password_mobile")
    public ResponseEntity<UsuiteApiResp> processForgotPasswordMobile(HttpServletRequest request, Model model) {
        String mobile = request.getParameter("mobile");
        String token = RandomNumUtils.generateCode(4);

        try {
            userService.updateResetPasswordTokenMobile(String.valueOf(token), Long.parseLong(mobile));
            String resetPin = "Your One-Time-Pasword (OTP) is: " + token;
//            sendEmail(email, resetPasswordLink);
            smsService.sendSingle(mobile, resetPin);
            return ResponseEntity.ok(new UsuiteApiResp("We have sent an OTP Code to your mobile number. Please check."));

        } catch (ResourceNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Failed to sent a reset password link"));
        }
    }

    public void sendEmail(String recipientEmail, String resetPasswordLink)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("credit.application@untucapital.co.zw", "Credit Application");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password")
    public Object showResetPasswordForm(@Param(value = "token") String token, Model model) {
        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (user == null) {
            //model.addAttribute("message", "Invalid Token");
//            return resetEmailUrl + '?token=' + token;
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Invalid Token.."));
//            return "Invalid Token";
        }

//        return resetPassUrl + "?token=" + token;
        return ResponseEntity.ok(new UsuiteApiResp("Token Successful"));
    }


    @PostMapping("/reset_password")
    public ResponseEntity<UsuiteApiResp> processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
            return ResponseEntity.badRequest().body(new UsuiteApiResp("Token used has expired"));
//            return "message";
        } else {
            userService.updatePassword(user, password);

            return ResponseEntity.ok(new UsuiteApiResp("You have successfully changed your password. You can now login"));
//            model.addAttribute("message", "You have successfully changed your password.");
        }

    }

    @PostMapping("/reset_mobile_password")
    public ResponseEntity<UsuiteApiResp> processResetMobilePassword(HttpServletRequest request, Model model) {
        String userId = request.getParameter("userid");
        String password = request.getParameter("password");

        User user = userRepository.getUserById(userId);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
//            model.addAttribute("message", "Invalid Token");
            return ResponseEntity.badRequest().body(new UsuiteApiResp("UserId not valid"));
//            return "message";
        } else {
            userService.updatePassword(user, password);

            return ResponseEntity.ok(new UsuiteApiResp("You have successfully changed your password. You can now login"));
//            model.addAttribute("message", "You have successfully changed your password.");
        }

    }
}
