package tn.esprit.spring.RestControllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Services.Classes.EmailSenderService;
import tn.esprit.spring.Services.Classes.UserService;

import javax.mail.MessagingException;

@RestController

public class ForgetPasswordController {
UserService userService;
	@PostMapping("/testsendattachementemailtoresetPassword")
	@ResponseBody
	public int sendAttachmentEmaill(String receiverEmail, String resetUrl) throws MessagingException
	{
		int code = userService.sendAttachmentEmaill( receiverEmail,  resetUrl) ;
		return code ;
	}
}

