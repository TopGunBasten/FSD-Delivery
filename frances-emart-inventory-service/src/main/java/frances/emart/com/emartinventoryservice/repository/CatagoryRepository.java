package frances.emart.com.emartinventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import frances.emart.com.emartinventoryservice.model.Catagory;

@Repository
public interface CatagoryRepository extends JpaRepository<Catagory, String> {
}