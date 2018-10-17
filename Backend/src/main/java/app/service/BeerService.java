package app.service;

import app.entity.Beer;
import app.model.BeerModel;
import app.repository.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerService {

    @Autowired
    BeerRepository _repository;

    public void Create(BeerModel beer){

        try
        {
            //validaciones

            app.entity.Beer newBeer = new Beer();
            newBeer.setName("Beer 1");
            newBeer.setColor("Roja");
            newBeer.setDensity(1);
            newBeer.setGraduation(2);
            newBeer.setGranos("Sudafricanos");
            newBeer.setIbu(3);
            newBeer.setPricePerLitre(321);
            newBeer.setQuantity(10);
            newBeer.setVisible(true);

            _repository.save(newBeer);

            return;
        }
        catch (Exception ex)
        {
            throw ex;
            //excepcion a ser tomada por el controller
        }
    }

}
