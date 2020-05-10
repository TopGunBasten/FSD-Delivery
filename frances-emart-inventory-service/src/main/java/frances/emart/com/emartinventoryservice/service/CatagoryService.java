package frances.emart.com.emartinventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import frances.emart.com.emartinventoryservice.model.Catagory;
import frances.emart.com.emartinventoryservice.model.SubCatagory;
import frances.emart.com.emartinventoryservice.repository.CatagoryRepository;
import frances.emart.com.emartinventoryservice.repository.SubCatagoryRepository;

@Service
public class CatagoryService {

    @Autowired
    private CatagoryRepository catagoryRepository;

    @Autowired
    private SubCatagoryRepository subCatagoryRepository;

    public SubCatagory createSubCatagory(SubCatagory subCatagory) {
        return this.subCatagoryRepository.save(subCatagory);
    }


    public SubCatagory updateSubCatagory(SubCatagory subCatagory) {
        return this.subCatagoryRepository.save(subCatagory);
    }

    public List<SubCatagory> getSubCatagories(String catagoryId){
        return this.subCatagoryRepository.findByCatagory(new Catagory(catagoryId));
    }

    public void removeSubcatagory(SubCatagory catagory){
        this.subCatagoryRepository.delete(catagory);
    }

    public void removeCatagory(Catagory catagory){
        this.catagoryRepository.delete(catagory);
    }



    public Catagory createCatagory(Catagory catagory){
       return  this.catagoryRepository.save(catagory);
    }

    public List<Catagory> getCatagories() {
        return this.catagoryRepository.findAll();
    }

    public Catagory updateCatagory(Catagory catagory) {
        return this.catagoryRepository.save(catagory);
    }
    
}