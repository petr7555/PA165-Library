package cz.muni.fi.pa165.library;

import cz.muni.fi.pa165.library.dto.BookDTO;
import cz.muni.fi.pa165.library.dto.LoanDTO;
import cz.muni.fi.pa165.library.dto.SingleLoanDTO;
import cz.muni.fi.pa165.library.dto.UserDTO;
import cz.muni.fi.pa165.library.entities.Book;
import cz.muni.fi.pa165.library.entities.Loan;
import cz.muni.fi.pa165.library.entities.Role;
import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * An utility class for creating sample entities.
 */
public class Utils {

    public static User createTestUser(String firstName, String lastName) {
        Role roleUser = new Role(Role.RoleType.USER);
        User john = new User();
        john.setFirstName(firstName);
        john.setLastName(lastName);
        john.setEmail(firstName + "@" + lastName + ".com");
        john.setPassword(lastName + "1234");
        john.setRoles(Collections.singletonList(roleUser));
        return john;
    }

    public static User createTestUser(String firstName, String lastName, Role role) {
        User john = new User();
        john.setFirstName(firstName);
        john.setLastName(lastName);
        john.setEmail(firstName + "@" + lastName + ".com");
        john.setPassword(lastName + "1234");
        john.setRoles(Collections.singletonList(role));
        return john;
    }

    public static Book createTestBookAnimalFarm() {
        return createTestBook("Animal farm", "George Orwell");
    }

    public static Book createTestBook1984() {
        return createTestBook("1984", "George Orwell");
    }

    public static Book createTestBookGatsby() {
        return createTestBook("The Great Gatsby", "Francis Scott Fitzgerald");
    }

    private static Book createTestBook(String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }

    public static SingleLoan createSingleLoan(Book book, User user) {
        SingleLoan singleLoan = new SingleLoan();
        singleLoan.setBook(book);
        singleLoan.setUser(user);
        singleLoan.setBorrowedAt(LocalDateTime.of(2020, 1, 1, 12, 0));
        return singleLoan;
    }

    public static Loan createLoanOfSingleLoans(List<SingleLoan> singleLoans) {
        Loan loan = new Loan();
        loan.setSingleLoans(singleLoans);
        return loan;
    }

    public static LoanDTO dtoCopyOfLoan(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        List<SingleLoanDTO> singleLoanDTOs = new ArrayList<>();
        for (SingleLoan singleLoan : loan.getSingleLoans()) {
            singleLoanDTOs.add(dtoCopyOfSingleLoan(singleLoan));
        }
        loanDTO.setSingleLoans(singleLoanDTOs);
        loanDTO.setId(loan.getId());
        return loanDTO;
    }

    public static SingleLoanDTO dtoCopyOfSingleLoan(SingleLoan singleLoan) {
        SingleLoanDTO singleLoanDTO = new SingleLoanDTO();
        singleLoanDTO.setBook(dtoCopyOfBook(singleLoan.getBook()));
        singleLoanDTO.setUser(dtoCopyOfUser(singleLoan.getUser()));
        singleLoanDTO.setBorrowedAt(singleLoan.getBorrowedAt());
        singleLoanDTO.setReturnedAt(singleLoan.getReturnedAt());
        singleLoanDTO.setId(singleLoan.getId());
        return singleLoanDTO;
    }

    public static BookDTO dtoCopyOfBook(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setId(book.getId());
        return bookDTO;
    }

    public static UserDTO dtoCopyOfUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        return userDTO;
    }
}
