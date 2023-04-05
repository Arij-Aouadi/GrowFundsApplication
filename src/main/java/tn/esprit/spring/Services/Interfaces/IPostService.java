package tn.esprit.spring.Services.Interfaces;

import io.swagger.v3.core.util.Json;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.PostModel;

public interface IPostService {
    public PostModel getPredictionByCreditId(int idCredit);
}
