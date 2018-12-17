package hu.elte.neptun.controller;

import hu.elte.neptun.model.Hallgato;
import hu.elte.neptun.model.Kurzus;
import hu.elte.neptun.repositories.HallgatoRepository;
import hu.elte.neptun.repositories.KurzusRepository;
import hu.elte.neptun.security.AuthenticatedUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/hallgatok")
public class HallgatoController {

    @Autowired
    private HallgatoRepository hallgatoRepository;
    @Autowired
    private KurzusRepository kurzusRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired 
    private AuthenticatedUser authenticatedHallgato;
     
   @GetMapping("")
    public ResponseEntity<Iterable<Hallgato>> getAll() {
        Hallgato authUser = authenticatedHallgato.getHallgato();
        if (authUser.getRole() == Hallgato.Role.ROLE_HALLGATO || authUser.getRole() == Hallgato.Role.ROLE_TANAR) {
            return ResponseEntity.ok(hallgatoRepository.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    
    @GetMapping("/sajat")
    public ResponseEntity<Hallgato> login() {
        return ResponseEntity.ok(hallgatoRepository.findById(authenticatedHallgato.getHallgato().getId()).get());
    }

    @PostMapping("")
    public ResponseEntity<Hallgato> register(@RequestBody Hallgato user) {
        Optional<Hallgato> oUser = hallgatoRepository.findByUsername(user.getUsername());
        if(oUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setEnabled(true);
        user.setRole(Hallgato.Role.ROLE_HALLGATO);
        return ResponseEntity.ok(hallgatoRepository.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hallgato> get(@PathVariable Integer id) {
        Optional<Hallgato> hallgato = hallgatoRepository.findById(id);
        if (hallgato.isPresent()) {
            return ResponseEntity.ok(hallgato.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/oraim")
        public ResponseEntity<Iterable<Kurzus>> getActiveKurzusok(@PathVariable Integer id) {
        Optional<Hallgato> user = hallgatoRepository.findById(id);
        Hallgato authUser = authenticatedHallgato.getHallgato();
        if (user.isPresent()) {
            if(authUser.getId().equals(user.get().getId())){
                return ResponseEntity.ok(user.get().getKurzus());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/oraim")
    public ResponseEntity<Hallgato> addActiveKurzus(@PathVariable Integer id, @RequestBody Kurzus kurzus) {
        Optional<Hallgato> oUser = hallgatoRepository.findById(id);
        if(oUser.isPresent()) {
            Hallgato authUser = authenticatedHallgato.getHallgato();
            Hallgato user = oUser.get();
            //if(authUser.getId().equals(user.getId())){
                Optional<Kurzus> oLesson = kurzusRepository.findById(kurzus.getId());
                if(oLesson.isPresent()) {
                    kurzus = oLesson.get();
                    if(!kurzus.getHallgatok().contains(user)) {
                        kurzus.getHallgatok().add(user);
                        kurzusRepository.save(kurzus);
                    }
                    return ResponseEntity.ok(user);
                } else {
                    return ResponseEntity.notFound().build();
                }/*
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }*/
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee user) {
        Optional<Employee> oUser = employeeRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Employee.Role.ROLE_EMPLOYEE);
        return ResponseEntity.ok(employeeRepository.save(user));
    }
     
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Employee user) {
        return ResponseEntity.ok().build();
    }*/

    /*@PostMapping("")
    public ResponseEntity<Employee> post(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee employee) {
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()) {
            employee.setId(id);
            return ResponseEntity.ok(employeeRepository.save(employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> delete(@PathVariable Integer id) {
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/purchases")
    public ResponseEntity<Iterable<Purchase>> getPurchases() {
        return ResponseEntity.ok(purchaseRepository.findAll());
    }
        
    @GetMapping("/purchases/delivered")
    public ResponseEntity<Iterable<Purchase>> getDeliveredPurchases() {
        List<Purchase> deliveredPurchases = new ArrayList<>();
        deliveredPurchases = purchaseRepository.findAllByIsDelivered(true);
        if(deliveredPurchases.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveredPurchases);
    }
    
    
    @GetMapping("/purchases/notDelivered")
    public ResponseEntity<Iterable<Purchase>> getNotDeliveredPurchases() {
        List<Purchase> deliveredPurchases = new ArrayList<>();
        deliveredPurchases = purchaseRepository.findAllByIsDelivered(false);
        if(deliveredPurchases.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveredPurchases);
    }*/
}