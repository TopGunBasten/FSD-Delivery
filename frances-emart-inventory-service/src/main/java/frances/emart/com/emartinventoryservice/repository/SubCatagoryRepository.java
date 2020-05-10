package frances.emart.com.emartinventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartinventoryservice.model.Catagory;
import frances.emart.com.emartinventoryservice.model.SubCatagory;

@Repository
public interface SubCatagoryRepository extends JpaRepository<SubCatagory, String> {
    List<SubCatagory> findByCatagory(Catagory  catagory);
}