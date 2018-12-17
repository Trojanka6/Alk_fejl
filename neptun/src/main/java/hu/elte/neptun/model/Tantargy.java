package hu.elte.neptun.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Tantargy {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nev;

    @Column
    @NotNull
    private int kredit;

    @Column
    @NotNull
    private boolean indul;

    @Column
    @NotNull
    private int felev;

    /*@Column
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at; */

    @OneToMany(mappedBy = "tantargy")
    @JsonIgnore
    private List<Kurzus> kurzus;  

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Tanszek tanszek;// itt es a Tantargy mapje ua. legyen

}