package cz.muni.fi.pa165.library.repositories;

import cz.muni.fi.pa165.library.entities.SingleLoan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Petr Janik 485122
 * @since 21.04.2020
 * <p>
 * A repository for SingleLoan entity.
 */
public interface SingleLoanRepository extends CrudRepository<SingleLoan, Long> {
    List<SingleLoan> findAll();
}
