package hu.elte.neptun.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Hallgato {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @NotNull
    private int felev;

    @Column
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at; 

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public enum Role {
        ROLE_TANAR, ROLE_HALLGATO, ROLE_GUEST //ha esetleg kelleni fog
    }
    
    @ManyToMany(mappedBy = "hallgatok")
    @JsonIgnore
    private List<Kurzus> kurzus;  // itt es a Tantargy mapje ua. legyen

    /*@ManyToMany(mappedBy = "hallgatok2")
    @JsonIgnore
    private List<Uzenet> uzenetek;  // itt es a Tantargy mapje ua. legyen*/

    /*@Column
    @NotNull
    private String nev;*/
}