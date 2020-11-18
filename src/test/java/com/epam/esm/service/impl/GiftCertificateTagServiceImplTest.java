package com.epam.esm.service.impl;

import com.epam.esm.exception.EntityNotAddedException;
import com.epam.esm.repository.tagcertificate.GiftCertificateRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class GiftCertificateTagServiceImplTest {
    @InjectMocks
    private GIftCertificateTagServiceImpl service;
    @Mock
    private GiftCertificateRepositoryImpl repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddRowTrue() {
        long giftId = 1;
        long tagId = 1;
        long expectedResult = 1;
        Mockito.when(repository.add(Mockito.anyLong(), Mockito.anyLong())).thenReturn(expectedResult);
        long actualResult = service.add(giftId, tagId);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldRemoveRowTrue() {
        long giftId = 1;
        long tagId = 1;
        long expectedResult = 1;
        Mockito.when(repository.delete(Mockito.anyLong(), Mockito.anyLong())).thenReturn(expectedResult);
        long actualResult = service.remove(giftId, tagId);
        Assert.assertEquals(expectedResult, actualResult);
    }


    @org.junit.Test(expected = EntityNotAddedException.class)
    void shouldNotAddRowException() throws EntityNotAddedException {
        Throwable expectedThrown = new EntityNotAddedException("Not added");
        long giftId = 1;
        long tagId = 1;
        Mockito.when(repository.add(Mockito.anyLong(), Mockito.anyLong())).thenThrow(expectedThrown);
        service.add(giftId, tagId);
    }
}