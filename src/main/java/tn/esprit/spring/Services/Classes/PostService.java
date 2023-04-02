package tn.esprit.spring.Services.Classes;

import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.AbstractJsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.PostModel;
import tn.esprit.spring.DAO.Repositories.ChatbotRepository;
import tn.esprit.spring.DAO.Repositories.CreditsRepository;
import tn.esprit.spring.Services.Interfaces.IPostService;

import javax.validation.constraints.Null;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService implements IPostService {
    @Autowired
    RestTemplate restTemplate;


    CreditsRepository creditsRepository;
    @Override
    public PostModel getPredictionByCreditId(PostModel postModel) {

        PostModel modelPrediction= null;
        Credits credit=creditsRepository.findCreditsByIdCredit(postModel.getIdCredit());
        restTemplate.getMessageConverters().add(new AbstractJsonHttpMessageConverter() {
            @Override
            protected Object readInternal(Type resolvedType, Reader reader) throws Exception {
                return null;
            }

            @Override
            protected void writeInternal(Object object, Type type, Writer writer) throws Exception {

            }
        });

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PostModel> httpEntity= new HttpEntity<>(postModel, headers);
        ResponseEntity<PostModel> result= restTemplate.postForEntity("http://arij123.pythonanywhere.com", httpEntity,PostModel.class);
        credit.setJudgment(result.toString());
       // if (result.getStatusCode() == HttpStatus.CREATED) {
           // modelPrediction = result.getBody();
       // }

        return result.getBody();
    }
}
