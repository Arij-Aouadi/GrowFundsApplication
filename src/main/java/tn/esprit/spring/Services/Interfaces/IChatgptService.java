package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.openai.OutputDto;

import java.io.IOException;

public interface IChatgptService {

    String sendChatgptRequest(String body) throws IOException, InterruptedException;


    OutputDto sendPrompt(String prompt) throws Exception;
}
