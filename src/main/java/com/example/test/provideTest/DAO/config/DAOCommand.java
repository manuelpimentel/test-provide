package com.example.test.provideTest.DAO.config;

import com.example.test.provideTest.DAO.dto.DAOPagination;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * DAO Command
 *
 * Class to implement the IDAOCommand interface and to store common methods.
 *
 * @author Manuel Pimentel
 * @param <T> Instance of object to be worked by the DAO Command.
 */
public abstract class DAOCommand<T> implements IDAOCommand {

    protected DAOPagination daoPagination;

    /**
     * Perform pagination.
     *
     * Handle de DAO Pagination object to paginate the retrieved News entity list.
     *
     * @param list List of objects to paginate
     * @return List of paginated objects
     */
    protected T performPagination(T list)
    {
        if (Objects.isNull(daoPagination)) return list;
        if (Objects.nonNull(daoPagination.getSize()) && Objects.nonNull(daoPagination.getPage())) {
            list = (T) ((List<T>) list).stream()
                    .skip(daoPagination.getPage() * daoPagination.getSize())
                    .limit(daoPagination.getSize())
                    .collect(Collectors.toList());
        } else if (Objects.nonNull(daoPagination.getSize())) {
            list = (T) ((List<T>) list).stream()
                    .limit(daoPagination.getSize())
                    .collect(Collectors.toList());
        }

        return list;
    }

}