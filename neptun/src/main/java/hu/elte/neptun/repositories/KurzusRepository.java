package hu.elte.neptun.repositories;

import hu.elte.neptun.model.Kurzus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KurzusRepository extends CrudRepository<Kurzus, Integer> {
    //public List<Kurzus> findAllByIsEnabled(Boolean b);
}