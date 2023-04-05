package tn.esprit.spring.Services.Classes;

import tn.esprit.spring.Services.Interfaces.IChatgptService;
import tn.esprit.spring.openai.OutputDto;
import tn.esprit.spring.openai.Answer;
import tn.esprit.spring.openai.Call;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.apache.commons.lang3.ArrayUtils.toStringArray;


import java.io.IOException;


@Slf4j
@Service

public class ChatgptServiceImpl implements IChatgptService {
    @Value("${openai.apikey}")
    private String openaiApiKey;
    private ObjectMapper jsonMapper;
    @Value("${url}")
    private String URL;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.maxTokens}")
    private Integer max_tokens;
    @Value("${openai.temperature}")
    private Double temperature;
    private final HttpClient client = HttpClient.newHttpClient();
    public ChatgptServiceImpl(ObjectMapper jsonMapper){
        this.jsonMapper=jsonMapper;
    }

    @Override
    public String sendChatgptRequest(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }


    @Override
    public OutputDto sendPrompt(String prompt) throws Exception {
        Call call = new Call(model,prompt,max_tokens,temperature);
        String responseBody = sendChatgptRequest(jsonMapper.writeValueAsString(call));
        System.out.println(responseBody);
        Answer answer = jsonMapper.readValue(responseBody, Answer.class);

        String text= Optional.of(answer.getChoices().get(0).getText()).orElseThrow();
        OutputDto outputDto= new OutputDto(prompt,text);
        return outputDto;
    }



}
