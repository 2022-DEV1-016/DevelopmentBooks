package com.digitalstork.devbookskata.service;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.exception.BookNotFoundException;
import com.digitalstork.devbookskata.exception.InvalidBookQuantityException;
import com.digitalstork.devbookskata.exception.NoAvailableBooksException;
import com.digitalstork.devbookskata.mapper.DevelopmentBookDevelopmentBookDtoMapper;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import com.digitalstork.devbookskata.repository.DevelopmentBookRepository;
import com.digitalstork.devbookskata.utils.BookUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DevelopmentBookServiceImpl implements DevelopmentBookService {

    private final DevelopmentBookRepository developmentBookRepository;

    private final DevelopmentBookDevelopmentBookDtoMapper mapper;

    @Override
    public List<DevelopmentBookListDto> getAllDevelopmentBooks() {
        List<DevelopmentBook> developmentBooks = developmentBookRepository.findAll();
        if (developmentBooks.isEmpty()) {
            throw new NoAvailableBooksException();
        }
        return mapper.developmentBooksToDevelopmentBookListDtos(developmentBooks);
    }

    @Override
    public DevelopmentBook updateBookQuantity(Long id, Integer soldQuantity) {
        if (soldQuantity <0) throw new InvalidBookQuantityException(
                String.format("Invalid quantity parameter : {%d}", soldQuantity)
        );
        DevelopmentBook book = developmentBookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book with Id {%d} does not exist", id)));
        book.setNbAvailableCopies(book.getNbAvailableCopies() - soldQuantity);
        return developmentBookRepository.save(book);
    }

    @Override
    public Double purchaseBooks(List<DevelopmentBookPurchaseDto> purchaseDtos) {

        AtomicReference<DevelopmentBook> book = new AtomicReference<>();
        purchaseDtos.stream().forEach( bookdto -> {
            book.set(developmentBookRepository.findById(bookdto.getBookId())
                    .orElseThrow(() -> new BookNotFoundException(String.format("Book with Id {%d} does not exist", bookdto.getBookId()))));
            if (book.get().getNbAvailableCopies() < bookdto.getQuantity()) {
                throw new NoAvailableBooksException(String.format("Book with Id {%d} is out of stock", bookdto.getBookId()));
            }
        });

        // A pack is a set of books Ids
        Set<Long> pack;
        List<Set<Long>> packlist = new ArrayList<>();

        List<Integer> quantities = purchaseDtos.stream().map(bookBuy -> bookBuy.getQuantity()).collect(Collectors.toList());
        int maxPacks = Collections.max(quantities);

        for (int i=0; i<maxPacks; i++) {
            pack = new HashSet<>();
            for (int j = 0; j < purchaseDtos.size(); j++) {
                if (purchaseDtos.get(j).getQuantity()>0) {
                    pack.add(purchaseDtos.get(j).getBookId());
                    purchaseDtos.get(j).setQuantity(purchaseDtos.get(j).getQuantity() - 1);
                }
            }
            packlist.add(pack);
        }
        return BookUtils.calculatePrice(packlist);
    }
}
