package com.digitalstork.devbookskata.service;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.exception.BookNotFoundException;
import com.digitalstork.devbookskata.exception.InvalidBookQuantityException;
import com.digitalstork.devbookskata.exception.NoAvailableBooksException;
import com.digitalstork.devbookskata.mapper.DevelopmentBookDevelopmentBookDtoMapperImpl;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import com.digitalstork.devbookskata.repository.DevelopmentBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DevelopmentBookServiceImplTest {

    private final Double singleBookPrice = 50d;

    @InjectMocks
    private DevelopmentBookServiceImpl developmentBookService;

    @Mock
    private DevelopmentBookRepository developmentBookRepository;

    @Mock
    private DevelopmentBookDevelopmentBookDtoMapperImpl mapper;

    @Test
    void shouldReturnTheFiveDevelopmentBooksDtos() {
        //given
        DevelopmentBook developmentBook1 =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 5);
        DevelopmentBook developmentBook2 =
                new DevelopmentBook(2L, "The Clean Coder", "Robert Martin", 2011, 5);
        DevelopmentBook developmentBook3 =
                new DevelopmentBook(3L, "Clean Architecture", "Robert Martin", 2017, 5);
        DevelopmentBook developmentBook4 =
                new DevelopmentBook(4L, "Test Driven Development by Example", "Kent Beck", 2003, 5);
        DevelopmentBook developmentBook5=
                new DevelopmentBook(5L, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 5);

        List<DevelopmentBook> developmentBooks = new ArrayList<>();
        developmentBooks.add(developmentBook1);
        developmentBooks.add(developmentBook2);
        developmentBooks.add(developmentBook3);
        developmentBooks.add(developmentBook4);
        developmentBooks.add(developmentBook5);

        List<DevelopmentBookListDto> mappedDtos = new ArrayList<>();
        mappedDtos.add(new DevelopmentBookListDto(1L, "Clean Code", "Robert Martin, 2008", 5));
        mappedDtos.add(new DevelopmentBookListDto(2L, "The Clean Coder", "Robert Martin, 2011", 5));
        mappedDtos.add(new DevelopmentBookListDto(3L,"Clean Architecture", "Robert Martin, 2017", 5));
        mappedDtos.add(new DevelopmentBookListDto(4L, "Test Driven Development by Example", "Kent Beck, 2003", 5));
        mappedDtos.add(new DevelopmentBookListDto(5L, "Working Effectively With Legacy Code", "Michael C. Feathers, 2004", 5));

        //when
        Mockito.when(developmentBookRepository.findAll()).thenReturn(developmentBooks);
        Mockito.when(mapper.developmentBooksToDevelopmentBookListDtos(developmentBooks))
                .thenReturn(mappedDtos);

        List<DevelopmentBookListDto> developmentBookListDtos = developmentBookService.getAllDevelopmentBooks();

        //Assert
        assertNotNull(developmentBookListDtos);
        assertTrue(developmentBooks.size() == developmentBookListDtos.size());
        assertEquals(developmentBooks.get(0).getTitle(), developmentBookListDtos.get(0).getTitle());
        assertEquals(developmentBooks.get(1).getTitle(), developmentBookListDtos.get(1).getTitle());
        assertEquals(developmentBooks.get(2).getTitle(), developmentBookListDtos.get(2).getTitle());
        assertEquals(developmentBooks.get(3).getTitle(), developmentBookListDtos.get(3).getTitle());
        assertEquals(developmentBooks.get(4).getTitle(), developmentBookListDtos.get(4).getTitle());

    }

    @Test
    void shouldThrowNoAvailableBooksExceptionWhenNoData() {

        //When
        Mockito.when(developmentBookRepository.findAll()).thenReturn(new ArrayList<>());
        //Mockito.when(mapper.developmentBooksToDevelopmentBookListDtos(Mockito.anyList())).thenReturn(new ArrayList<>());
        //Assert
        NoAvailableBooksException exception = assertThrows(NoAvailableBooksException.class, () -> {
            developmentBookService.getAllDevelopmentBooks();
        });

        assertTrue("There is no available books in stock".equals(exception.getMessage()));
    }

    @Test
    void shouldPurchaseThreeBooks_withTwoCopiesEach_successfully(){
        //Given
        DevelopmentBook book =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 5);
        DevelopmentBookPurchaseDto purchaseDto1 = new DevelopmentBookPurchaseDto(1L, 2);
        DevelopmentBookPurchaseDto purchaseDto2 = new DevelopmentBookPurchaseDto(2L, 2);
        DevelopmentBookPurchaseDto purchaseDto3 = new DevelopmentBookPurchaseDto(3L, 2);
        List<DevelopmentBookPurchaseDto> purchaseDtos = new ArrayList<>();
        purchaseDtos.add(purchaseDto1);
        purchaseDtos.add(purchaseDto2);
        purchaseDtos.add(purchaseDto3);

        Double expectedPrice = (3 * singleBookPrice - 3 * singleBookPrice * 10 / 100 ) * 2;

        //When
        Mockito.when(developmentBookRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(book));
        Double resultPrice = developmentBookService.purchaseBooks(purchaseDtos);

        //Assert
        assertNotNull(resultPrice);
        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void shouldThrowNoAvailableBooksExceptionWhenNoQuantityInStock() {

        //Given
        DevelopmentBook book =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 0);
        DevelopmentBookPurchaseDto purchaseDto = new DevelopmentBookPurchaseDto(1L, 2);
        List<DevelopmentBookPurchaseDto> purchaseDtos = new ArrayList<>(Arrays.asList(purchaseDto));

        //When
        Mockito.when(developmentBookRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(book));

        //Assert
        NoAvailableBooksException exception = assertThrows(NoAvailableBooksException.class, () -> {
            developmentBookService.purchaseBooks(purchaseDtos);
        });

        assertTrue("Book with Id {1} is out of stock".equals(exception.getMessage()));
    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenBookBookNotExists() {

        //Given
        DevelopmentBookPurchaseDto purchaseDto = new DevelopmentBookPurchaseDto(7L, 5);
        List<DevelopmentBookPurchaseDto> purchaseDtos = new ArrayList<>(Arrays.asList(purchaseDto));

        //Assert
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            developmentBookService.purchaseBooks(purchaseDtos);
        });
        assertTrue("Book with Id {7} does not exist".equals(exception.getMessage()));
    }

    @Test
    void shouldUpdateBookQuantityInDB() {
        //Given
        Integer soldQuantity = 2;
        DevelopmentBook book =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 5);
        DevelopmentBook updatedBook =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 3);

        //When
        Mockito.when(developmentBookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));
        Mockito.when(developmentBookRepository.save(Mockito.any())).thenReturn(updatedBook);
        DevelopmentBook result = developmentBookService.updateBookQuantity(book.getId(), soldQuantity);

        //Then
        assertNotNull(result);
        assertEquals(updatedBook.getId(), result.getId());
        assertEquals(updatedBook.getNbAvailableCopies(), result.getNbAvailableCopies());

    }

    @Test
    void shouldThrowBookNotFoundExceptionWhenBookBookIdNotExists() {

        //Given
        Long invalidBookId = 7L;
        //Assert
        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
            developmentBookService.updateBookQuantity(invalidBookId, 3);
        });
        assertTrue("Book with Id {7} does not exist".equals(exception.getMessage()));
    }

    @Test
    void shouldThrowInvalidBookQuantityExceptionWhenQuantityIsNegative() {
        //Given
        DevelopmentBook book =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 5);
        Integer invalidQuantity = -2;
        //Assert
        InvalidBookQuantityException exception = assertThrows(InvalidBookQuantityException.class, () -> {
            developmentBookService.updateBookQuantity(book.getId(), invalidQuantity);
        });
        assertTrue("Invalid quantity parameter : {-2}".equals(exception.getMessage()));
    }

    @Test
    void shouldThrowInvalidBookQuantityExceptionWhenRemainingQuantityIsNegative() {
        //Given
        DevelopmentBook book =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 2);
        Integer invalidQuantity = 4;

        //When
        Mockito.when(developmentBookRepository.findById(Mockito.any())).thenReturn(Optional.of(book));

        //Assert
        InvalidBookQuantityException exception = assertThrows(InvalidBookQuantityException.class, () -> {
            developmentBookService.updateBookQuantity(book.getId(), invalidQuantity);
        });
        assertTrue("Available copies must not be negative".equals(exception.getMessage()));
    }
}