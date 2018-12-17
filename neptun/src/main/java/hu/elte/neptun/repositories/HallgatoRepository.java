package hu.elte.neptun.repositories;

import hu.elte.neptun.model.Hallgato;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallgatoRepository extends CrudRepository<Hallgato, Integer>{
    Optional<Hallgato> findByUsername(String username);
    public List<Hallgato> findAllByKurzusId(int id);
    
}