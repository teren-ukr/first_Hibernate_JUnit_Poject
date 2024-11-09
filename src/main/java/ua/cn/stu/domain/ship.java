package ua.cn.stu.domain;
import javax.persistence.*;


@Entity
@Table(name = "shiptable")
public class ship {

    //------------------------------------------------------ constructors
    public ship(){

    }

    public ship(String cabin, String passenger){
        this.cabin = cabin;
        this.passenger = passenger;
    }

    //------------------------------------------------------ columns
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String cabin;

    @Column
    private String passenger;


    //------------------------------------------------------ methods (getters setters)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }
}
