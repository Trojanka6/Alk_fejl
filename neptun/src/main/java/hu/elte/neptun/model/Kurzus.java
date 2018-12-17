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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
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
public class Kurzus {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private int kurzuskod;

    @Column
    @NotNull
    private int maxhallgato;

    @Column
    @NotNull
    private String idopont; 

    @Column
    @CreationTimestamp
    private LocalDateTime created_at;

    @Column
    @UpdateTimestamp
    private LocalDateTime updated_at; 

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Tantargy tantargy; // itt es a Tantargy mapje ua. legyen

    @ManyToMany
    @JoinTable(name = "kuha")
    private List<Hallgato> hallgatok; // itt es a Tantargy mapje ua. legyen

    /*@Column
    @NotNull
    private String tipus;*/

    /*@Column
    @NotNull
    private int oraszam;*/

    /*@Column
    @NotNull
    private boolean isEnabled; */
}