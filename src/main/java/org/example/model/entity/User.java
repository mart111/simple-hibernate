package org.example.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Cacheable
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int age;
    //    @Enumerated(EnumType.ORDINAL) Don't use @Enumerated explicitly with @Convert
    @Convert(converter = SexConverter.class)
    private Sex sex;

    @Converter
    static class SexConverter implements AttributeConverter<Sex, String> {

        @Override
        public String convertToDatabaseColumn(Sex attribute) {
            return attribute.getVal();
        }

        @Override
        public Sex convertToEntityAttribute(String dbData) {
            return Sex.from(dbData.toUpperCase());
        }
    }

}
