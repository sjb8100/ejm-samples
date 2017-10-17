package ejm.chapter2.admin.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Ken Finnigan
 */
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "SELECT c from Category c")
})
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            allocationSize = 1,
            initialValue = 1020
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    private String header;

    private Boolean visible;

    @Column(name = "image_path", length = 120)
    private String imagePath;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parent_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category parent;

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime updated;

    @Version
    private Integer version = 0;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name) &&
                Objects.equals(header, category.header) &&
                Objects.equals(visible, category.visible) &&
                Objects.equals(imagePath, category.imagePath) &&
                Objects.equals(parent, category.parent) &&
                Objects.equals(created, category.created) &&
                Objects.equals(updated, category.updated) &&
                Objects.equals(version, category.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, visible, imagePath, parent, created, updated, version);
    }
}