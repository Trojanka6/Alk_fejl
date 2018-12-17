package hu.elte.neptun.repositories;

import hu.elte.neptun.model.Tantargy;
import hu.elte.neptun.model.Kurzus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TantargyRepository extends CrudRepository<Tantargy, Integer>{
  // @OneToMany
    //public List<Tantargy> findAllByTanszekId(Integer i);
}