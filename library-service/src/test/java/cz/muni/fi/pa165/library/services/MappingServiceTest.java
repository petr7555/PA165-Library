package cz.muni.fi.pa165.library.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Petr Janik 485122
 * @since 25.04.2020
 */
@SpringBootTest
class MappingServiceTest {

    @Autowired
    private MappingService mappingService;

    @Test
    void mapCollectionOfObjectsInner() {
        Person person1 = getPerson("John Doe", 35, "dog", "brown");
        PersonDTO personDTO1 = getPersonDTO("John Doe", "dog");
        Person person2 = getPerson("Anne Smith", 25, "cat", "white");
        PersonDTO personDTO2 = getPersonDTO("Anne Smith", "cat");

        assertThat(mappingService.mapTo(List.of(person1, person2), PersonDTO.class), containsInAnyOrder(personDTO1, personDTO2));
    }

    @Test
    void mapSingleObjectInner() {
        Person person = getPerson("John Doe", 35, "dog", "brown");
        PersonDTO personDTO = getPersonDTO("John Doe", "dog");
        assertEquals(mappingService.mapTo(person, PersonDTO.class), personDTO);
    }

    private PersonDTO getPersonDTO(String nameOfPerson, String kindOfPet) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(nameOfPerson);
        PersonDTO.PetDTO petDTO = new PersonDTO.PetDTO();
        petDTO.setKind(kindOfPet);
        personDTO.setPet(petDTO);
        return personDTO;
    }

    private Person getPerson(String nameOfPerson, int ageOfPerson, String kindOfPet, String colorOfPet) {
        Person person = new Person();
        person.setName(nameOfPerson);
        person.setAge(ageOfPerson);
        Person.Pet pet = new Person.Pet();
        pet.setKind(kindOfPet);
        pet.setColor(colorOfPet);
        person.setPet(pet);
        return person;
    }

    public static class Person {
        private String name;
        private int age;
        private Pet pet;

        public static class Pet {
            private String kind;
            private String color;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }
    }

    public static class PersonDTO {
        private String name;
        private PetDTO pet;

        public static class PetDTO {
            private String kind;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PetDTO petDTO = (PetDTO) o;
                return Objects.equals(kind, petDTO.kind);
            }

            @Override
            public int hashCode() {
                return Objects.hash(kind);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public PetDTO getPet() {
            return pet;
        }

        public void setPet(PetDTO pet) {
            this.pet = pet;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonDTO personDTO = (PersonDTO) o;
            return Objects.equals(name, personDTO.name) &&
                    Objects.equals(pet, personDTO.pet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, pet);
        }
    }
}
