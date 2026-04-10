package uol.publicidade.url_shortener.domain.url.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Table(name = "urls")
@Entity(name = "Url")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDT_URL")
    private Long id;

    @Column(name = "DES_URL_ORIGINAL", nullable = false)
    private String originalUrl;

    @Column(name = "DES_KEY", nullable = false, unique = true)
    private String key;

    @Column(name = "FLG_ACTIVE", nullable = false)
    private Boolean active;

    @CreatedDate
    @Column(name = "DAT_CREATED", updatable = false)
    private Date created;

    @LastModifiedDate
    @Column(name = "DAT_MODIFIED")
    private Date modified;
}
