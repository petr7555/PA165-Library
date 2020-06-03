package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.BookRepository;
import cz.muni.fi.pa165.library.repositories.LoanRepository;
import cz.muni.fi.pa165.library.repositories.RoleRepository;
import cz.muni.fi.pa165.library.repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 12.04.2020
 * <p>
 * Initializes in-memory database with initial data. The data is reset after each restart of the application.
 */
@Configuration
@Profile("prod")
public class DataConfiguration {

    /**
     * Populate DB with sample data.
     *
     * @param roleRepository
     * @param userRepository
     * @param bookRepository
     * @param encoder
     * @return
     */
    @Bean
    public ApplicationRunner dataInitializer(RoleRepository roleRepository, UserRepository userRepository,
                                             BookRepository bookRepository, LoanRepository loanRepository,
                                             PasswordEncoder encoder) {
        Role roleUser = new Role(Role.RoleType.USER);
        Role roleAdmin = new Role(Role.RoleType.ADMIN);

        Book animalFarm = new Book("Animal Farm", "George Orwell");
        Book nineteenEightyFour = new Book("Nineteen Eighty-Four", "George Orwell");
        Book theJungleBook = new Book("The Jungle Book", "Rudyard Kipling");
        Book romeoAndJuliet = new Book("Romeo and Juliet", "William Shakespeare");

        User john = new User("John", "Smith", "john.smith@gmail.com",
                encoder.encode("john1234"), Collections.singletonList(roleUser));
        User peter = new User("Peter", "Griffin", "peter.griffin@gmail.com",
                encoder.encode("password"), List.of(roleUser, roleAdmin));
        User user = new User("Ad", "Min", "admin",
                encoder.encode("admin"), List.of(roleUser, roleAdmin));
        User admin = new User("Us", "Er", "user",
                encoder.encode("user"), Collections.singletonList(roleUser));

        LocalDateTime lastWeek = LocalDateTime.now().minusDays(7);

        SingleLoan animalFarmReturnedYesterday = new SingleLoan();
        animalFarmReturnedYesterday.setBook(animalFarm);
        animalFarmReturnedYesterday.setUser(john);
        animalFarmReturnedYesterday.setBorrowedAt(lastWeek);
        animalFarmReturnedYesterday.setReturnedAt(lastWeek.plusDays(3));
        animalFarmReturnedYesterday.setReturnCondition("Minor scratches");

        SingleLoan theJungleBookNotReturnedYet = new SingleLoan();
        theJungleBookNotReturnedYet.setBook(theJungleBook);
        theJungleBookNotReturnedYet.setUser(john);
        theJungleBookNotReturnedYet.setBorrowedAt(lastWeek);
        theJungleBookNotReturnedYet.setReturnedAt(null);
        theJungleBookNotReturnedYet.setReturnCondition(null);

        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);

        SingleLoan romeoAndJulietReturned = new SingleLoan();
        romeoAndJulietReturned.setBook(romeoAndJuliet);
        romeoAndJulietReturned.setUser(john);
        romeoAndJulietReturned.setBorrowedAt(lastMonth);
        romeoAndJulietReturned.setReturnedAt(lastMonth.plusDays(7));
        romeoAndJulietReturned.setReturnCondition("Perfect condition");

        SingleLoan romeoAndJulietNotReturnedYet = new SingleLoan();
        romeoAndJulietNotReturnedYet.setBook(romeoAndJuliet);
        romeoAndJulietNotReturnedYet.setUser(peter);
        romeoAndJulietNotReturnedYet.setBorrowedAt(lastWeek);
        romeoAndJulietNotReturnedYet.setReturnedAt(null);
        romeoAndJulietNotReturnedYet.setReturnCondition(null);

        Loan johnsLoan = new Loan();
        johnsLoan.setSingleLoans(List.of(animalFarmReturnedYesterday, theJungleBookNotReturnedYet));

        Loan johnsSecondLoan = new Loan();
        johnsSecondLoan.setSingleLoans(List.of(romeoAndJulietReturned));

        Loan petersLoan = new Loan();
        petersLoan.setSingleLoans(List.of(romeoAndJulietNotReturnedYet));

        return args -> {
            roleRepository.saveAll(List.of(roleUser, roleAdmin));
            userRepository.saveAll(List.of(john, peter, user, admin));
            bookRepository.saveAll(List.of(animalFarm, nineteenEightyFour, theJungleBook, romeoAndJuliet));
            loanRepository.saveAll(List.of(johnsLoan, johnsSecondLoan, petersLoan));
        };
    }
}
