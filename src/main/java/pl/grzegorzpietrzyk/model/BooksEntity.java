package pl.grzegorzpietrzyk.model;

import lombok.*;

import javax.persistence.*;

 ////////////////////
// Lombok         //
//
@AllArgsConstructor
@RequiredArgsConstructor
@Data
//////////////////

@Entity
@Table(name = "BOOKS", schema = "PUBLIC", catalog = "DB")
public class BooksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookID;

    @Basic
    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Basic
    @Column(name = "AUTHOR", nullable = false, length = 50)
    private String author;

    @Basic
    @Column(name = "PUBLISHERNAME", nullable = false, length = 50)
    private String publishername;

 }
