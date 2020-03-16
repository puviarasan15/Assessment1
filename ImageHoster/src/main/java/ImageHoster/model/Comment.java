package ImageHoster.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@Table annotation provides more options to customize the mapping.
//Here the name of the table to be created in the database is explicitly mentioned as 'comment'. Hence the table named 'comment' will be created in the database with all the columns mapped to all the attributes in 'comment' class
@Table(name = "comment")
public class Comment {

    //@Id annotation specifies that the corresponding attribute is a primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column annotation specifies that the attribute will be mapped to the column in the database.
    //Here the column name is explicitly mentioned as 'id'
    @Column(name = "id")
    private Integer id;

    @Column(name = "text", length = 512)
    private String text ;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    //The 'comment' table is mapped to 'users' table with Many:One mapping
    //cascade = CascadeType.ALL specifies that if a record in 'users' table is deleted or updated, then all the records in 'comment' table associated to that particular record in 'users' table will be deleted or updated  first and then the record in the 'users' table will be deleted or updated
    //FetchType is EAGER
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comment' table referring the primary key in 'users' table will be 'userId'
    @JoinColumn(name = "userId")
    private User user;

    //The 'comment' table is mapped to 'images' table with Many:One mapping
    //cascade = CascadeType.ALL specifies that if a record in 'images' table is deleted or updated, then all the records in 'comment' table associated to that particular record in 'images' table will be deleted or updated  first and then the record in the 'images' table will be deleted or updated
    //FetchType is EAGER
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //Below annotation indicates that the name of the column in 'comment' table referring the primary key in 'images' table will be 'imageId'
    @JoinColumn(name = "imageId")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
