package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import cz.muni.fi.pa165.library.entities.User;
import cz.muni.fi.pa165.library.repositories.SingleLoanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** @author Martin Páleník 359817
 *  based on http://zetcode.com/springboot/crudrepository/
 *
 *  This represents the business logic of the
 *  loans of book or books by a member.
 */

@Service
public class SingleLoanService {

    private SingleLoanRepository singleLoanRepository;

    /**
     * Constructor.
     * injects repository class
     * @param singleLoanRepository (required)
     */
    public SingleLoanService(SingleLoanRepository singleLoanRepository) {
        this.singleLoanRepository = singleLoanRepository;
    }

    /**
     * Returns all loans, including the ones
     * where the book has been already returned.
     *
     * @return list of all loans
     */
    public List<SingleLoan> findAll() {
        return singleLoanRepository.findAll();
    }

    /**
     * Returns number of loans (including past loans)
     * that has ever been entered in the system.
     *
     * @return number of all loans
     */
    public Long count() {
        return singleLoanRepository.count();
    }

    /**
     * Deletes a specific SingleLoan with the given "id"
     * @param userId is ID of SingleLoan we are looking for
     * @throws IllegalArgumentException if "id" is less than 0
     */
    public void deleteById(Long userId) {
        singleLoanRepository.deleteById(userId);
    }

    /**
     * For the given user return all his loans (current or past).
     * @return list of loans for the given user
     */
    public List<SingleLoan> getLoansForUser(User user) {

        List<SingleLoan> usersLoans = new ArrayList<>();

        for (SingleLoan singleLoan : singleLoanRepository.findAll()){
            if (singleLoan.getUser().equals(user)) {
                usersLoans.add(singleLoan);
            }
        }

        return usersLoans;
    }

}